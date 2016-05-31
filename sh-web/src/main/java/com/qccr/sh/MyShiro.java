package com.qccr.sh;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MyShiro extends AuthorizingRealm {
	/**
	 * 权限认证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		//权限先写死1-管理员
		// 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<String> nameList = new ArrayList<String>();
		nameList.add("管理员");
        // 用户的角色集合
		info.setRoles(new HashSet<String>(nameList));
		// 用户的角色对应的所有权限，如果只使用角色定义访问权限，下面的四行可以不要
		List<String> pers = new ArrayList<String>();
		pers.add("order:third");
		info.addStringPermissions(pers);

		return info;
	}

	/**
	 * 登录认证;
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		// UsernamePasswordToken对象用来存放提交的登录信息
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //这里的1对应LoginController.login里登录成功时存的1
		return new SimpleAuthenticationInfo(token.getUsername(), "1", getName());
	}

}