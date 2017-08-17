package com.chen.security;

import org.apache.shiro.authc.UsernamePasswordToken;

public class CaptchaUsernamePasswordToken extends UsernamePasswordToken{

	private String captcha;

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
	//重写UsernamePasswordToken构造方法
	public CaptchaUsernamePasswordToken(String username,String password,
			boolean rememberMe,String host,String captcha){
		super(username, password, rememberMe, host);
	}
}
