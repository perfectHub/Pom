package com.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chen.entity.User;
import com.chen.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class MultiServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void MultiSaveTest(){
		User user = new User();
		user.setUsername("ceshi");
		user.setPassword("123");
		//默认数据源
		userService.insertUser_DefaultDataSource(user);
		//第一个数据源
		userService.insertUser_FirstDataSource(user);
		//第二个数据源
		userService.insertUser_SecondDataSource(user);
	}
	
	public static void main(String[] args) {
		
	}
}
