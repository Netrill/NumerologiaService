package com.christianscarselli.configuration;

import org.springframework.context.annotation.Bean;

import com.christianscarselli.util.PdfUtil;

@org.springframework.context.annotation.Configuration
public class Configuration {
	/*
	@Bean
	public Properties properties () {
		Properties properties = new Properties ();
		InputStream iStream = null;
	      try { 
	    	  iStream = this.getClass().getClassLoader().getResourceAsStream("application.properties")  ;
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
		
	}*/
	@Bean
	public PdfUtil pdfUtils () {
		
		return new PdfUtil ();
		
	}
}
