package com.java.plus.lambda.model;
 
import lombok.Data;

@Data
public class Apple {

	private String name;
	private int weight;
	private Color color;

	
	public Apple(int weight,Color color) {
		this.weight=weight;
		this.color=color;
	}

	
	public Apple(String name,int weight,Color color) {
		this.name=name;
		this.weight=weight;
		this.color=color;
	}
	
	@Override
	public String toString() {
		return "{name:"+this.name+",weight:"+this.weight+",color:"+this.color+"}";
		
	}

}
