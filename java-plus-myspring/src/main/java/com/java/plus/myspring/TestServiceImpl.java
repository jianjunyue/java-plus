package com.java.plus.myspring;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component 
public class TestServiceImpl implements TestService {

	@Override
    public String hello(){
    	System.out.print( "hello world---------dd1111111111d------");
        return "hello world---------dd1111111111d------";
    }
	 


}
