package shiro.test;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

import shiro.realm.BaseTest;

public class HelloWorld extends BaseTest{

	public static void main(String[] args) {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
		
		try {
			subject.login(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertEquals(true, subject.isAuthenticated());
		
		//
		subject.logout();
	}
	
	@Test
	public void testHasRole(){
		login("classpath:shiro-role.ini", "zhang", "123");
		Assert.assertEquals(true, subject().hasRole("role1"));
		Assert.assertEquals(true, subject().hasAllRoles(Arrays.asList("role1")));
	}
	
	@Test
	public void testCheckRole(){
		login("classpath:shiro-role.ini","zhang","123");
		subject().checkRole("role1");
		subject().checkRoles("role1","role2");
	}
	
	@Test
	public void testIsPermission(){
		login("classpath:shiro-role.ini","zhang","123");
		Assert.assertTrue(subject().isPermitted("user:create"));
		
		Assert.assertTrue(subject().isPermittedAll("user:create","user:delete"));
		Assert.assertFalse(subject().isPermitted("user:view"));
	}
	
	@Test
	public void testCheckPermission(){
		login("classpath:shiro-role.ini","zhang","123");
		
		subject().checkPermission("user:create");
		subject().checkPermissions("user:create","user:update");
	}
}
