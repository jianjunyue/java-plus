package com.java.plus.myspring;
 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.java.plus.myspring.ApplicationContextUtil;

@RestController
public class TestController {

//    @RequestMapping("/test")
//    public String test(){
//        //注意getBean(String s) bean在spring中对象名小写开头
//        return ((TestServiceImpl)ApplicationContextUtil.getBean("testServiceImpl")).hello();
//    }
//
//    @RequestMapping("/test2")
//    public String test2(){
//        return ApplicationContextUtil.getBean(TestServiceImpl.class).hello();
//    }

}
