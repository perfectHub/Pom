package com.chen.datasource;

public class DataSourceContextHolder {

	public static final String DATA_SOURCE_A = "dataSource1";
	public static final String DATA_SOURCE_B = "dataSource2";
	
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	
	public static void setDataSource(String dataSource){
		contextHolder.set(dataSource);
	}
	
	public static String getDataSource(){
		return contextHolder.get();
	}
	
	public static void clearDataSource(){
		contextHolder.remove();
	}
}
