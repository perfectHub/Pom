package com.rest.util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.util.ReflectionUtils;

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class PageInterceptor implements Interceptor,Serializable{
	
	private static final long serialVersionUID = 1L;

    protected Dialect DIALECT = new MySqlDialect();

    /**
     * 对ID做正则匹配，只对结尾为Page的方法进行处理
     */
    protected String _SQL_PATTERN = ".*Page$";

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		if (invocation.getTarget() instanceof RoutingStatementHandler) {
            RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
            BaseStatementHandler delegate =
                    (BaseStatementHandler) getField(RoutingStatementHandler.class, "delegate").get(statementHandler);
            MappedStatement mappedStatement =
                    (MappedStatement) getField(BaseStatementHandler.class, "mappedStatement").get(delegate);
            //重新需要分页的SQL
            if (mappedStatement.getId().matches(_SQL_PATTERN)) {
                BoundSql boundSql = delegate.getBoundSql();
                String originalSql = boundSql.getSql();
                if(StringUtils.isBlank(originalSql)){
                    return invocation.proceed();
                }

                Object parameterObject = boundSql.getParameterObject();
                //获取分页参数对象
                Pager pager = null;
                if(parameterObject != null){
                	if(parameterObject instanceof Pager){
                		pager = (Pager) parameterObject;
                	}
                }
                if (pager != null) {
                	
                	//获取总记录数
                	int count = SQLHelper.getCount(originalSql, null, mappedStatement);
                	pager.setTotalCount(count);
                	
                    //处理排序
                    originalSql = generateOrderSql(originalSql, pager, DIALECT);
                    //处理分页
                    String pageSql = generatePageSql(originalSql, pager, DIALECT);
                    //赋值，将新的SQL覆盖原SQL
                    setFieldValue(boundSql, "sql", pageSql);
                }
            }
        }
		//交给下一个拦截器
		return invocation.proceed();
	}

	private Field getField(Class<?> clazz, String name){
        Field field = ReflectionUtils.findField(clazz, name);
        field.setAccessible(true);
        return field;
    }

	/**
	 * 排序SQL
	 * @param sql 
	 * @param pager 排序参数对象
	 * @param dialect 方言
	 * @return
	 */
    private String generateOrderSql(String sql,Pager pager,Dialect dialect){
        if (StringUtils.isBlank(pager.getOrderColumns())){
            return sql;
        }
        return dialect.getOrderString(sql, pager.getOrderColumns(),pager.getOrderType());
    }

    /**
     * 分页SQL
     * @param sql
     * @param pager
     * @param dialect 方言
     * @return
     */
    private String generatePageSql(String sql,Pager pager,Dialect dialect){
        int pageSize = pager.getPageSize();
        int index = (pager.getCurrentPage() - 1) * pageSize;
        int start = index < 0 ? 0 : index;
        return dialect.getLimitString(sql, start, pageSize);
    }

    private void setFieldValue(Object object,String fieldName,Object value){
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object,value);
        } catch (Exception e) {
            //这里不可能抛出异常
            e.printStackTrace();
        }
    }
	
	@Override
	public Object plugin(Object target) {
		if (target instanceof StatementHandler) {
            //目标属于StatementHandler时才包装该类
            return Plugin.wrap(target, this);
        } else {
            //否则，直接返回目标类，减少代理次数
            return target;
        }
	}

	@Override
	public void setProperties(Properties arg0) {
		
	}

}
