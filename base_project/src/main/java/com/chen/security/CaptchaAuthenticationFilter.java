package com.chen.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

public class CaptchaAuthenticationFilter extends FormAuthenticationFilter{

	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";
	
	private String captchaParam = DEFAULT_USERNAME_PARAM;

	public String getCaptchaParam() {
		return captchaParam;
	}

	public void setCaptchaParam(String captchaParam) {
		this.captchaParam = captchaParam;
	}

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}
	
	//创建Token
	protected CaptchaUsernamePasswordToken createToken(ServletRequest request,ServletResponse response){
		String username = getUsername(request);
		String password = getPassword(request);
		
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);
		String captcha = getCaptcha(request);
		
		return new CaptchaUsernamePasswordToken(username, password, rememberMe, host, captcha);
	}
	
	//验证校验
	protected void doCaptchaValidate(HttpServletRequest request,CaptchaUsernamePasswordToken token){
		
		String captcha = (String)request.getSession().getAttribute("validateCode");
		
		if(captcha != null && !captcha.equalsIgnoreCase(token.getCaptcha())){
			throw new IncorrectCapchaException("验证码错误！");
		}
	}
	
	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		CaptchaUsernamePasswordToken token = createToken(request, response);
		
		try {
			doCaptchaValidate((HttpServletRequest)request, token);
			
			Subject subject = getSubject(request, response);
			subject.login(token);
			
			return onLoginSuccess(token, subject, request, response);
		} catch (AuthenticationException e) {
			return onLoginFailure(token, e, request, response);
		}
	}
	
	
}
