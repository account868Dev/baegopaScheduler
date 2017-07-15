package com.high.baegopa.common.config;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@MapperScan(basePackages = { "com.high.baegopa.mapper" }, sqlSessionFactoryRef = "sqlSessionFactory")
@RequiredArgsConstructor
public class MyBatisConfig {

	@Autowired
	private DBProperties dBProperties;

	@Bean(name = "dataSource")
	public org.apache.tomcat.jdbc.pool.DataSource dataSource() {
		org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
		dataSource.setDriverClassName(dBProperties.getDriverClassName());
		dataSource.setUrl(dBProperties.getUrl());
		dataSource.setUsername(dBProperties.getUsername());
		dataSource.setPassword(dBProperties.getPassword());
		return dataSource;
	}

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") javax.sql.DataSource dataSource,
											   ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigurationProperties(mybatisProperties());
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources(dBProperties.getMapperLocation()));
		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	private Properties mybatisProperties() {
		Properties properties = new Properties();
		properties.put("lazyLoadingEnabled", "true");
		return properties;
	}
}