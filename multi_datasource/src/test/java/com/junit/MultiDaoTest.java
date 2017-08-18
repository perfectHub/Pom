package com.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chen.dao.UserDao;
import com.chen.datasource.DataSourceContextHolder;
import com.chen.entity.User;

/**
 * 多数据源测试，只dao层进行测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class MultiDaoTest {
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void MultiSaveTest(){
		//第一个数据源
		DataSourceContextHolder.setDataSource("dataSource1");
		User user = new User();
		user.setUsername("li");
		user.setPassword("123");
		
		userDao.insertUser(user);
		//第二个数据源
		DataSourceContextHolder.setDataSource("dataSource2");
		
		userDao.insertUser(user);
	}
	
	public static void main(String[] args) {
		
	}
}
