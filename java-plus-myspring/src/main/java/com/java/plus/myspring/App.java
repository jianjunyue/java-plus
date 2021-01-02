package com.java.plus.myspring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextInitializer;

/**
 * Hello world!
 *
 */
public class App 
{ 
    public String run()
    { 

		String str = ((TestService) ApplicationContextUtil.getBean("testServiceImpl")).hello();
//		String str = ((TestServiceImpl) ApplicationContextUtil.getBean("testServiceImpl")).hello();
		System.out.print(str);
        System.out.println( "Hello World!" );
        
        return str;
    }
}
