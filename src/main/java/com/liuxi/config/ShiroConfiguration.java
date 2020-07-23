package com.liuxi.config;

import java.util.LinkedHashMap;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

/**
 * - shiro的配置类
 */
@Configuration
public class ShiroConfiguration {

	//拦截的方法
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(manager);
		// 配置登录的url和登录成功的url
		bean.setLoginUrl("/toLogin");// 去登录页面的地址
		bean.setUnauthorizedUrl("/noPer");

		bean.setSuccessUrl("/index");// 登录成功之后跳转的地址

		// 配置访问权限，需要使用LinkedHashMap，因为它必须保证有序
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		//		anon:所有url都都可以匿名访问
		//		authc: 需要认证才能进行访问
		//		user:配置记住我或认证通过可以访问
		//		filterChainDefinitionMap.put("需要访问的url", "访问权限");
		filterChainDefinitionMap.put("/statics/*", "anon"); // 表示可以匿名访问
		filterChainDefinitionMap.put("/toLogin", "anon");
		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/**", "authc");// 表示需要认证才可以访问
		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return bean;
	}

	// 配置核心安全事务管理器
	@Bean(name = "securityManager")
	public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
		System.err.println("--------------shiro已经加载----------------");
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(authRealm);
		return manager;
	}

//  // 配置自定义的权限登录器
//  @Bean(name = "authRealm")
//  public AuthRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher) {
//  	AuthRealm authRealm = new AuthRealm();
//  	// 密码验证
//  	authRealm.setCredentialsMatcher(matcher);
//  	return authRealm;
//  }
//
//  // 配置自定义的密码比较器
//  @Bean(name = "credentialsMatcher")
//  public CredentialsMatcher credentialsMatcher() {
//  	return new CredentialsMatcher();
//  }

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
		creator.setProxyTargetClass(true);
		return creator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			@Qualifier("securityManager") SecurityManager manager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(manager);
		return advisor;
	}
}