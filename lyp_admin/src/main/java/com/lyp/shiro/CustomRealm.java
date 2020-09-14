package com.lyp.shiro;

import com.lyp.dao.UserDao;
import com.lyp.entity.User;
import com.lyp.entity.UserPermission;
import com.lyp.entity.UserRole;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CustomRealm extends AuthorizingRealm {
    @Autowired
    UserDao userDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        List<UserRole> userRoleList = ((User) principalCollection.getPrimaryPrincipal()).getUserRoleList();

        //添加角色Role
        List<String> roleNames = userRoleList.stream()
                .map(UserRole::getRoleName)
                .collect(Collectors.toList());
        simpleAuthorizationInfo.addRoles(roleNames);

        //添加权限Permission
        List<String> permissionNames = userRoleList.stream()
                .map(user -> user.getUserPermissionList().stream()
                        .map(UserPermission::getPermissionName)
                        .collect(Collectors.toList()))
                .reduce((f, s) -> {
                    f.addAll(s);
                    return f;
                })
                .orElse(new ArrayList<>())
                .stream()
                .distinct()//权限去重
                .collect(Collectors.toList());
        simpleAuthorizationInfo.addStringPermissions(permissionNames);

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = String.valueOf(token.getPrincipal());
        User user = userDao.findByUsernameEquals(username);
        if (user == null) {
            return null;
        }

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
        simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getUsername()));
        return simpleAuthenticationInfo;
    }
}
