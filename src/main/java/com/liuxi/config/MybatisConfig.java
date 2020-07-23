package com.liuxi.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 数据库连接池配置
 *
 * @author
 */
@Configuration
@PropertySource("classpath:druid.properties")
public class MybatisConfig {

	private Logger logger = LoggerFactory.getLogger(MybatisConfig.class);

	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;

	@Value("${spring.datasource.druid.initialSize}")
	private int initialSize;

	@Value("${spring.datasource.druid.minIdle}")
	private int minIdle;

	@Value("${spring.datasource.druid.maxActive}")
	private int maxActive;

	@Value("${spring.datasource.druid.maxWait}")
	private int maxWait;

	@Value("${spring.datasource.druid.timeBetweenEvictionRunsMillis}")
	private int timeBetweenEvictionRunsMillis;

	@Value("${spring.datasource.druid.minEvictableIdleTimeMillis}")
	private int minEvictableIdleTimeMillis;

	@Value("${spring.datasource.druid.validationQuery}")
	private String validationQuery;

	@Value("${spring.datasource.druid.testWhileIdle}")
	private boolean testWhileIdle;

	@Value("${spring.datasource.druid.testOnBorrow}")
	private boolean testOnBorrow;

	@Value("${spring.datasource.druid.testOnReturn}")
	private boolean testOnReturn;

	@Value("${spring.datasource.druid.filters}")
	private String filters;

	@Value("${spring.datasource.druid.logSlowSql}")
	private String logSlowSql;

	/**
	 * 数据库连接池
	 *
	 * @return
	 */
	@Bean("druidDataSource")
	public DataSource druidDataSource() {
		DruidDataSource datasource = new DruidDataSource();
		datasource.setUrl(dbUrl);
		datasource.setUsername(username);
		datasource.setPassword(password);
		datasource.setDriverClassName(driverClassName);
		datasource.setInitialSize(initialSize);
		datasource.setMinIdle(minIdle);
		datasource.setMaxActive(maxActive);
		datasource.setMaxWait(maxWait);
		datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		datasource.setValidationQuery(validationQuery);
		datasource.setTestWhileIdle(testWhileIdle);
		datasource.setTestOnBorrow(testOnBorrow);
		datasource.setTestOnReturn(testOnReturn);
		try {
			datasource.setFilters(filters);
		} catch (SQLException e) {
			logger.error("druid configuration initialization filter", e);
		}
		return datasource;
	}


	/**
	 * 访问druid监控信息，查看sql语句执行情况 需要servlet支持
	 *
	 * http://127.0.0.1:8080/spssm/druid/login.html
	 *
	 * @return
	 */
	@Bean
	public ServletRegistrationBean druidServletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		// 添加初始化参数：initParams

		// 白名单：
		registration.addInitParameter("allow", "127.0.0.1");
		// IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not
		// permitted to view this page.
		registration.addInitParameter("deny", "");
		// 登录查看信息的账号密码.
		registration.addInitParameter("loginUsername", "admin");
		registration.addInitParameter("loginPassword", "admin");
		// 是否能够重置数据.
		registration.addInitParameter("resetEnable", "false");
		return registration;
	}

	/**
	 * 过滤druid
	 *
	 * @return
	 */
	@Bean
	public FilterRegistrationBean druidStatFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());

		// 添加过滤规则.
		filterRegistrationBean.addUrlPatterns("/*");

		// 添加不需要忽略的格式信息.
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}
}