package com.christianscarselli.configurazion;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
	
	@Bean
	public Properties properties () {
		Properties properties = new Properties ();
		InputStream iStream = null;
	      try { 
	        iStream = new FileInputStream("./src/main/resources/application.properties");   
	        properties.load(iStream);
	      } catch (IOException e) {
	       // TODO Auto-generated catch block
	       e.printStackTrace();
	      }finally {
	        try {
	          if(iStream != null){
	            iStream.close();
	          }
	        } catch (IOException e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	        }
	      }
		return properties;
		
	}
}
