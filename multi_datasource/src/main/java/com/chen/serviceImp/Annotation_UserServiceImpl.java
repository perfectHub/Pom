package com.chen.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chen.dao.UserDao;
import com.chen.entity.User;
import com.chen.service.Annotation_UserService;

@Service
public class Annotation_UserServiceImpl implements Annotation_UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public void insertUser_DefaultDataSource(User user) {
		userDao.insertUser(user);
	}

	@Override
	public void insertUser_FirstDataSource(User user) {
		userDao.insertUser(user);
	}

	@Override
	public void insertUser_SecondDataSource(User user) {
		userDao.insertUser(user);
	}

}
