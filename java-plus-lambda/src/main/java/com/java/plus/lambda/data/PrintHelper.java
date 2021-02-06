package com.java.plus.lambda.data;

import java.util.List;

import com.java.plus.lambda.model.Apple;

public class PrintHelper {
	
	public static void print( List<Apple> list) {
		list.stream().forEach(apple-> System.out.println(apple.toString())); 
		System.out.println("---------------------------------");
	}

}
