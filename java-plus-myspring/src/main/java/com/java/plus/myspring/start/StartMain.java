package com.java.plus.myspring.start;

import javax.servlet.http.HttpServlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.java.plus.myspring.framework.servlet.GPDispatcherServlet;

@ComponentScan(basePackages = "com.java.plus.myspring")
@SpringBootApplication
public class StartMain {
	public static void main(String[] args) {

		SpringApplication.run(StartMain.class, args);

	}
	
	// 一定要加，不然这个方法不会运行
	//http://localhost:8091/api/
	@Bean  
	 public ServletRegistrationBean dispatcherRegistration() {  
	     return new ServletRegistrationBean(new GPDispatcherServlet(),"/*"); // 放入自己的Servlet对象实例
	}  
 
}
