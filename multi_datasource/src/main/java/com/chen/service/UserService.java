package com.chen.service;

import com.chen.entity.User;

public interface UserService {

	public void insertUser_DefaultDataSource(User user);
	
	public void insertUser_FirstDataSource(User user);
	
	public void insertUser_SecondDataSource(User user);
}
