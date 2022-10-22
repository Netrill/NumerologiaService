package com.christianscarselli.configuration;

import java.util.Properties;

import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static org.hibernate.cfg.Environment.*;

@Configuration
@EnableTransactionManagement
@ComponentScan ("com.christianscarselli.entities")
@PropertySource("classpath:application.properties")
public class HibernateConfig {
	
	@Autowired
	Environment env;
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
    public LocalSessionFactoryBean sessionFactory() {
	    LocalSessionFactoryBean factoryBean=new LocalSessionFactoryBean();
	    factoryBean.setDataSource(dataSource);
	    factoryBean.setPackagesToScan("com.christianscarselli.entities");
	    factoryBean.setHibernateProperties(additionalProperties());
	    return factoryBean;
    }

	private Properties additionalProperties() {
		Properties properties=new Properties();
		properties.put("hibernate.dialect", org.hibernate.dialect.SQLServerDialect.class);
		properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
		properties.put("use_sql_comments", env.getProperty("use_sql_comments"));
		properties.put("default_batch_fetch_size", env.getProperty("default_batch_fetch_size"));
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		//properties.put("hibernate.cache.use_second_level_cache", Boolean.TRUE);
		//properties.put("hibernate.cache.use_query_cache", Boolean.TRUE);
		//properties.put("hibernate.cache.region.factory_class",EhCacheRegionFactory.class);
		return properties;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory().getObject());
		return txManager;
	}

}
