package com.chen.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chen.dao.UserDao;
import com.chen.datasource.DataSourceContextHolder;
import com.chen.entity.User;
import com.chen.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public void insertUser_DefaultDataSource(User user) {
		userDao.insertUser(user);
	}

	@Override
	public void insertUser_FirstDataSource(User user) {
		//第一个数据源
		DataSourceContextHolder.setDataSource("dataSource1");
		userDao.insertUser(user);
	}

	@Override
	public void insertUser_SecondDataSource(User user) {
		//第二个数据源
		DataSourceContextHolder.setDataSource("dataSource2");
		userDao.insertUser(user);
	}

}
