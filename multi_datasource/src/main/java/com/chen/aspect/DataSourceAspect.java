package com.chen.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.chen.annotation.DataSource;
import com.chen.datasource.DataSourceContextHolder;

/**
 * 多数据源注解实现
 */
@Aspect
@Component
public class DataSourceAspect {
	
	@Pointcut("execution(* com.chen.service.*.*(..))")
	public void poinCut(){}
	
	/**
	 * 拦截目标方法，获取由@DataSource指定的数据源标识，设置到线程存储中以便切换数据源
	 * @param point
	 */
	@Before(value = "poinCut()")
	public void before(JoinPoint point){
		Class<?> target = point.getTarget().getClass();
		MethodSignature signature = (MethodSignature)point.getSignature();
		//获取目标实现类的第一个接口
		Class<?> interface0 = target.getInterfaces()[0];
		
		resolveDataSource(interface0,signature.getMethod());
	}
	
	/**
	 * 提取目标对象类注解和方法注解
	 * @param clazz 接口
	 * @param method 
	 */
	public void resolveDataSource(Class<?> clazz, Method method){
		try {
			//默认使用类型注解
			if(clazz.isAnnotationPresent(DataSource.class)){
				DataSource source = clazz.getAnnotation(DataSource.class);
				DataSourceContextHolder.setDataSource(source.value());
			}
			Class<?>[] types = method.getParameterTypes();
			//方法注解可以覆盖类型注解
			Method m = clazz.getMethod(method.getName(), types);
			if(m != null && m.isAnnotationPresent(DataSource.class)){
				DataSource source = m.getAnnotation(DataSource.class);
				DataSourceContextHolder.setDataSource(source.value());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
