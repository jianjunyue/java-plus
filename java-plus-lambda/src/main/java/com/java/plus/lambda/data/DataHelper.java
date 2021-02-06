package com.java.plus.lambda.data;

import java.util.ArrayList;
import java.util.List;

import com.java.plus.lambda.model.Apple;
import com.java.plus.lambda.model.Color;
import com.java.plus.lambda.model.Dish;

public class DataHelper {
	
	public static List<Apple>  getApples(){
		 List<Apple> list=new ArrayList<>();
		 list.add(new Apple("apple3",3,Color.GREEN));
		 list.add(new Apple("apple1",1,Color.GREEN));
		 list.add(new Apple("apple4",4,Color.GREEN));
		 list.add(new Apple("apple2",2,Color.GREEN));
		 
		return list;
	}
	
	
	public static List<Dish>  getDishs(){
		 List<Dish> list=new ArrayList<>();
		 list.add(new Dish("pork",false,800,Dish.Type.MEAT));
		 list.add(new Dish("beef",false,700,Dish.Type.MEAT));
		 list.add(new Dish("chicken",false,400,Dish.Type.MEAT));
		 list.add(new Dish("french fries",true,350,Dish.Type.OTHER));
		 list.add(new Dish("rice",true,350,Dish.Type.OTHER));
		 list.add(new Dish("season",true,120,Dish.Type.OTHER));
		 list.add(new Dish("pizza",true,550,Dish.Type.OTHER));
		 list.add(new Dish("prawns",false,300,Dish.Type.FISH));
		 list.add(new Dish("salmon",false,450,Dish.Type.FISH));
		 
		return list;
	}
	

}
