package com.java.plus.myspring.test1;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 

/**
 * http://localhost:8091/spring/test
 * */

@RestController
@RequestMapping("/spring/")
public class TestController {
	
	@Autowired
	private TestService testService;


    @RequestMapping("test")
    public String test(String name){
    	
    	return	testService.hello() + "----" +name; 
    }

 

}
