package com.christianscarselli.configurazion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan ("com.christianscarselli.repository")
public class HibernateConfig {
	
	@Autowired
	Environment env;

}
