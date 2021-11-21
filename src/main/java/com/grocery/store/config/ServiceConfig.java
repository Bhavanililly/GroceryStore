package com.grocery.store.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
public class ServiceConfig {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private DataSource datasource;
	
	@Autowired
	private SessionFactory sessionfactory;
	
	@Bean
	public LocalSessionFactoryBean localSessionFactoryBean() {
		
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		sessionFactory.setAnnotatedClasses(new Class[] {
				com.grocery.store.domain.Order.class,
				com.grocery.store.domain.Item.class
		});
		sessionFactory.setHibernateProperties(hibernateProperties());
		sessionFactory.setDataSource(datasource);
		return sessionFactory;
		
	}


	private Properties hibernateProperties() {
		// TODO Auto-generated method stub
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
		hibernateProperties.setProperty("hibernate.show.sql", env.getProperty("spring.jpa.properties.hibernate.show.sql"));
		hibernateProperties.setProperty("hibernate.default_schema",env.getProperty("spring.jpa.properties.hibernate.default_schema"));
		return hibernateProperties;
	}

}
