package com.chen.realm;

public class MyRealm extends AuthorizingRealm{

	@Autowired
	private UserService userService;
	
	/**
	 * 验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken)authToken;
		String username = token.getUsername();
		String password = new String(token.getPassword());
		User user = userService.findUserByUsername(username);
		
		if(user != null){
			if("1".equals(user.getLocked())){
				throw new LockedAccountException();//账号锁定
			}
			if(!password.equals(user.getPassword())){
				throw new IncorrectCredentialsException();//密码不正确
			}
			SimpleAuthenticationInfo authenticationInfo = 
					new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),getName());
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id",user.getId());
			map.put("username", user.getUsername());
			//把用户信息放入session中
			SecurityUtils.getSubject().getSession().setAttribute("user", map);
			return authenticationInfo;
		}else{
			throw new UnknownAccountException();//未知账号
		}
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = 
				new SimpleAuthorizationInfo();
		return authorizationInfo;
	}

}
