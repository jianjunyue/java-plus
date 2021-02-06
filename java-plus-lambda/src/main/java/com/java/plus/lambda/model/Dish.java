package com.java.plus.lambda.model;

import lombok.Data;

//菜肴
@Data
public class Dish {
	private String name; 
	private boolean vegetarian; 
	private int calories; 
	private Type type;
	
	public Dish(String name, boolean vegetarian,int calories,Type type) {
		this.name=name;
		this.vegetarian=vegetarian;
		this.calories=calories;
		this.type=type;
	}

	public enum Type{
		MEAT,
		FISH,
		OTHER
	}
}
