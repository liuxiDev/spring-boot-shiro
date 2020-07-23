package com.liuxi.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.liuxi.entity.Permission;
import com.liuxi.entity.User;
import com.liuxi.service.PermissionService;
import com.liuxi.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class AuthRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    // 认证.登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

//        //查询数据库的用户信息
//        Wrapper wrapper = Condition.create().eq("name", username);
//        User user = userService.selectOne(wrapper);
		User user = null;
		try {
			UsernamePasswordToken utoken = (UsernamePasswordToken) token;// 获取用户输入的token
			String username = utoken.getUsername();
			user = userService.selectByName(username);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());// 放入shiro.调用CredentialsMatcher检验密码
    }

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        User user = (User) principal.fromRealm(this.getClass().getName()).iterator().next();// 获取session中的用户
        // 当前用户在系统上有什么权限，把拥有的权限设置到permissions
        List<String> permissions = new ArrayList<>();
//		查询当前用户的拥有的所有的权限内容
        List<Permission> list = null;
        try {
            list = permissionService.selectByUid(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Permission permission : list) {
//			不能存放空的权限到shiro
            String url = permission.getUrl();
			if (url != null && !"".equals(url)) {
                permissions.add(url);
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);// 将权限放入shiro中.
        return info;
    }

}