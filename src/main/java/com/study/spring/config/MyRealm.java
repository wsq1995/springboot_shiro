package com.study.spring.config;

import com.study.spring.domain.User;
import com.study.spring.service.UserService;
import com.study.spring.service.impl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Tp on 2018/12/27.
 */
// 自定义的realm
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserServiceImpl userService;

    // 执行授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();
        User user = userService.findById(principal.getId());
        info.addStringPermission(user.getPerms());
        return info;

    }

    // 执行认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.findByName(token.getUsername());

        if (user == null) {
            return null;
        }
        return new SimpleAuthenticationInfo("user", user.getPassword(), "");
    }
}
