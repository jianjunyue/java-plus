package com.java.plus.myspring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/***
 * 只能跟main函数同目录
 * */
@Service
public class ApplicationContextUtil implements ApplicationContextAware {

    public static ApplicationContext applicationContext;
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    	ApplicationContextUtil.applicationContext = applicationContext;
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }
}
