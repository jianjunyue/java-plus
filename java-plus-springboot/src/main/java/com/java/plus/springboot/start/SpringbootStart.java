package com.java.plus.springboot.start;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.java.plus.springboot.ApplicationContextBean;
 
 
@ComponentScan(basePackages = "com.java.plus.springboot")
@SpringBootApplication 
public class SpringbootStart {

	public static void main(String[] args) {
		 SpringApplication.run(SpringbootStart.class, args);
		 
		 ApplicationContextBean app=new ApplicationContextBean();
		 app.getModuleConfiguration();

	}

}
