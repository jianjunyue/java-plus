package com.java.plus.myspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.java.plus.myspring.ApplicationContextUtil;

@SpringBootApplication
public class StartMain {
	public static void main(String[] args) {

		SpringApplication.run(StartMain.class, args);
		
		
		App app=new App();
		app.run();
	}
}
