package com.lyyh.fertilizer.backup.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.lyyh.fertilizer.backup.dao.UserDao;
import com.lyyh.greenhouse.pojo.User;


/**
 * 自定义一个realm
 */
public class BosRealm extends AuthorizingRealm{
	@Autowired
	private UserDao userDao;
	//认证
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("认证方法。。。。");
		UsernamePasswordToken passwordToken = (UsernamePasswordToken)token;
		String username = passwordToken.getUsername();
		//根据username查询数据库中的密码
		User user = userDao.findByUsername(username);
		if(user == null){
			//没有查询到数据，提供的username不存在
			//如果这个方法返回null，框架会认为当前的username不存在，抛出异常
			return null;
		}
		//将查询到的密码返回给框架，由shiro框架进行比对，如果比对成功表示认证通过，比对不成功，抛出异常IncorrectCredentialsException
		AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		return info;
	}
	
	
	
	//授权方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	//授权方法
//	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//		//为当前用户授权
//		info.addStringPermission("courier");
//		//为当前用户授予角色
//		info.addRole("admin");
//		//info.addStringPermission("courier-delete");
//		//后期需要修改为查询数据库获得当前登录用户具有的权限，然后进行授权
//		
//		return info;
//	}
}
