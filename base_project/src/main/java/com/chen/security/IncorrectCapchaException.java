package com.chen.security;

import org.apache.shiro.authc.AuthenticationException;

public class IncorrectCapchaException extends AuthenticationException{

	public IncorrectCapchaException(){
		super();
	}
	
	public IncorrectCapchaException(String message,Throwable cause){
		super(message, cause);
	}
	
	public IncorrectCapchaException(String message){
		super(message);
	}
	
	public IncorrectCapchaException(Throwable cause){
		super(cause);
	}
}
