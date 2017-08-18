package com.chen.service;

import com.chen.annotation.DataSource;
import com.chen.entity.User;

@DataSource(value = "dataSource1")
public interface Annotation_UserService {

	public void insertUser_DefaultDataSource(User user);
	
	//使用dataSource1数据源
	@DataSource(value = "dataSource1")
	public void insertUser_FirstDataSource(User user);
	
	//使用dataSource2数据源
	@DataSource(value = "dataSource2")
	public void insertUser_SecondDataSource(User user);
}
