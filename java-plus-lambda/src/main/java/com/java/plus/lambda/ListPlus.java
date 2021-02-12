package com.java.plus.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListPlus {

	public static void main(String[] args) {

		ListPlus plus = new ListPlus();

		System.out.println(plus.friends);

		plus.add();

		System.out.println(plus.friends);

		plus.removeIf();
	}

	public List<String> friends = Arrays.asList("test1", "test2", "test3");

	public void add() {
//		friends.add("test4");//报异常， Arrays.asList 工厂方法创建的list不能add
//		friends.set(1,"test4");// 不报异常，可以set修改
	}

	public void removeIf() {
		 
		List<String> list = friends.stream().collect(Collectors.toList());
		list.removeIf(filter -> filter.contains("test1")); //  
		System.out.println(list);
	}

}
