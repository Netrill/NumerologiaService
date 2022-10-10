package com.christianscarselli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class NumerologiaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NumerologiaServiceApplication.class, args);
	}

}
