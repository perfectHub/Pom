package com.rest.util;

/**
 * MySQL方言
 */
public class MySqlDialect implements Dialect{

	/**
     * 将sql转换为分页SQL
     *
     * @param sql    SQL语句
     * @param offset 开始条数
     * @param limit  每页显示多少纪录条数
     * @return 分页查询的sql
     */
	@Override
	public String getLimitString(String sql, int offset, int limit) {
		StringBuilder builder = new StringBuilder(sql);
		builder.append(" limit ");
		if(offset > 0){
			builder.append(offset).append(",").append(limit);
		}else{
			builder.append(limit);
		}
		return builder.toString();
	}

	/**
     * 将sql转换为排序SQL
     *
     * @param sql
     * @param orderColumns 排序的列，多个由逗号隔开
     * @param orderType 排序类型
     * @return
     */
	@Override
	public String getOrderString(String sql, String orderColums,
			OrderType orderType) {
		return new StringBuilder(sql).append(" order by ").append(orderColums).append(" ")
				.append(orderType.toString()).toString();
	}

}
