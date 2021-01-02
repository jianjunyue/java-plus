package com.java.plus.myspring.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.plus.myspring.framework.annotation.GPAutowired;
import com.java.plus.myspring.framework.annotation.GPController;
import com.java.plus.myspring.framework.annotation.GPRequestMapping;
import com.java.plus.myspring.framework.annotation.GPRequestParam;
import com.java.plus.myspring.iface.IDemoService;

@GPController
@GPRequestMapping("/secondServlet")
public class DemoController {

	@GPAutowired
	private IDemoService demoService;

	@GPRequestMapping("/query")
	public String query(HttpServletRequest req, HttpServletResponse resp, @GPRequestParam("name") String name) {
		String result = "";
		try {
			result = demoService.get(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result + " - " + name;
	}
//
//	@GPRequestMapping("/add")
//	public void add(HttpServletRequest req, HttpServletResponse resp,
//					@GPRequestParam("a") Integer a, @GPRequestParam("b") Integer b){
//		try {
//			resp.getWriter().write(a + "+" + b + "=" + (a + b));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@GPRequestMapping("/remove")
//	public void remove(HttpServletRequest req,HttpServletResponse resp,
//					   @GPRequestParam("id") Integer id){
//	}

}
