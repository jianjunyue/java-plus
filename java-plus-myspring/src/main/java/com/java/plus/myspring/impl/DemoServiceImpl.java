package com.java.plus.myspring.impl;

import com.java.plus.myspring.framework.annotation.GPService;
import com.java.plus.myspring.iface.IDemoService;

/**
 * 核心业务逻辑
 */
@GPService
public class DemoServiceImpl implements IDemoService{

	public String get(String name) {
		return "My name is " + name;
	}

}
