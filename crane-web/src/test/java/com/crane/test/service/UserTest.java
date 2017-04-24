package com.crane.test.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.crane.exception.BusinessException;
import com.crane.po.model.User;
import com.crane.service.UserService;

/**
* @author  Crane:
* @version 5.0
* @time 2017年3月11日 下午5:30:20
* 
*/
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class UserTest extends AbstractTestNGSpringContextTests {
	
	private Logger logger = LoggerFactory.getLogger(UserTest.class);
	
	@Resource
	private UserService userService;
	
	/*@Test
	public void testRegister(){
		User user = new User();
		user.setUserName("crane");
		user.setEmail("409716474@qq.com");
		user.setPassword("36826637");
		try {
			this.userService.regist(user);
			logger.info("用户注册成功");
		} catch (BusinessException e) {
			logger.error(e.getMessage());
		}
	}*/
	/*@Test
	public void testEmail(){
		try{
			this.userService.sendCheckCode("409716474@qq.com");
		}catch(BusinessException e){
			logger.error(e.getMessage());
		}
	}*/
	@Test
	public void testUsername(){
		User user = this.userService.findUserByUserName("crane");
		logger.info(user.getUserName());
		System.out.println("666");
	}

	/*@Test
	public void testLogin(){
		String account = "crane";
		try{
			String password = "36826637";
			User user = userService.login(account, password);
			if(null!=user){
				System.out.println(666666);
				logger.info(user.getUserName());
				System.out.println(666666888);
			}
		}catch(BusinessException e){
			logger.info(e.getMessage());
			logger.error("登录失败:{}",account,e);
		}
	}*/
}
