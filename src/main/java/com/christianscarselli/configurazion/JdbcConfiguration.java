package com.christianscarselli.configurazion;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.DriverManagerDataSourceFactory;

@Configuration
@EnableTransactionManagement
@ComponentScan("")
@PropertySource("classpath:application.properties")
public class JdbcConfiguration {
	
	@Autowired
	Environment environment;
	
	@Bean
	public JdbcTemplate jdbcTemplate (DataSource dataSource) {
		
		return new JdbcTemplate(dataSource);
	}
	
	public NamedParameterJdbcTemplate getJdbcTamplate (DataSource dataSource) {
		
		return new NamedParameterJdbcTemplate(dataSource);
		
	}
	
	@Bean(name = "dataSource")
	public DataSource dataSource()
	{
		try {
					return DriverManagerDataSourceFactory.create(environment.getRequiredProperty("jdbc.driverClassName"),
					environment.getRequiredProperty("jdbc.url"), environment.getRequiredProperty("jdbc.username"), environment.getRequiredProperty("jdbc.password"));
		} catch (IllegalStateException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager()
	{
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource());
		
		return transactionManager;
	}

}
