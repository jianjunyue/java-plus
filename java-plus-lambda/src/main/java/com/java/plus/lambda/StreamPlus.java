package com.java.plus.lambda;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.java.plus.lambda.data.DataHelper;
import com.java.plus.lambda.data.PrintHelper;
import com.java.plus.lambda.model.Dish; 

/*
Stream流中间操作（返回流）：
filter    参数：Predicate<T>   T -> boolean
map       参数：Function<T,R>  T -> R   原流中选取一列
flatMap   参数：Function<T,Stream<R>>  T -> Stream<R>   原流中选取一列, 合并成一个新流
sorted    参数：Comparator<T>  (T,T) -> int
distinct
limit       返回int



Stream流终端操作（）：
forEach 返回 void
count 返回 long
collect 返回 集合 比如：List、Map等

 */
public class StreamPlus {

	private static List<Dish> dishs = DataHelper.getDishs();

	public static void main(String[] args) {
		StreamPlus stream = new StreamPlus();

		stream.getStreamList();
		stream.getStreamMap();

	}

	/**
	 * Map中，key值不能有重复
	 */
	public void getStreamList() {
		System.out.println("-----------------------getStreamList-----------------------------");

		//Dish::getName ==>等价于 dish -> dish.getName()   ==>等价于 
//		new Function(Dish dish) {
//			return dish.getName();
//		}
		
		// stream().map(Function<Dish, Boolean>)  -->返回 Boolean
		List<Boolean> distinctList = dishs.stream().map(dish -> dish.isVegetarian()).distinct()
				.collect(Collectors.toList());
		System.out.println(distinctList);
		List<Boolean> dishList = dishs.stream().map(dish -> dish.isVegetarian()).collect(Collectors.toList());

		System.out.println(dishList);

		//Collectors.toMap(Function<Dish, String> , Function<Dish, Dish> ) -->返回 Map<String, Dish>
		Map<String, Dish> dishMap = dishs.stream().collect(Collectors.toMap(Dish::getName, dish -> dish));
		//Collectors.toMap(Function<Dish, Dish> , Function<Dish, String> ) -->返回 Map<Dish, String>
		Map<Dish, String> dishMap1 = dishs.stream().collect(Collectors.toMap(dish -> dish,Dish::getName));
		Map<String, String> dishMap2 = dishs.stream().collect(Collectors.toMap(Dish::getName,Dish::getName));
		Map<String, String> dishStrMap = dishs.stream()
				.collect(Collectors.toMap(Dish::getName, dish -> dish.getName()));

		System.out.println(dishMap);

		System.out.println(dishStrMap);
	}

	/**
	 * Map中，key值不能有重复
	 */
	public void getStreamMap() {
		System.out.println("-----------------------getStreamMap-----------------------------");
		Map<String, Dish> dishMap = dishs.stream().collect(Collectors.toMap(Dish::getName, dish -> dish));
		Map<String, String> dishStrMap = dishs.stream()
				.collect(Collectors.toMap(Dish::getName, dish -> dish.getName()));

		System.out.println(dishMap);

		System.out.println(dishStrMap);
	}

}
