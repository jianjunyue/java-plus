package com.java.plus.lambda;

import com.java.plus.lambda.common.Predicate;
import com.java.plus.lambda.model.Apple;
import com.java.plus.lambda.model.Color;

/**
 * 匿名类
 */
public class Anonymous {

	public static void main(String[] args) {

		Anonymous anonymous = new Anonymous();
		boolean bl = anonymous.run(new Apple(10 , Color.GREEN));
		System.out.println(bl);

		boolean bl1 = anonymous.predicate1.test(new Apple(120 , Color.GREEN));
		System.out.println(bl1);
		
		boolean bl2 = anonymous.predicate2.test(new Apple(20 , Color.GREEN));
		System.out.println(bl2);

	}

	private Predicate<Apple> predicate = new Predicate<Apple>() {
		public boolean test(Apple apple) {
			return apple.getWeight() > 100;
		}
	};

	private Predicate<Apple> predicate1 = (Apple apple) -> apple.getWeight() > 100;

	private Predicate<Apple> predicate2 = (Apple apple) -> {
		return apple.getWeight() > 100;
	};

	public boolean run(Apple apple) {
		return predicate.test(apple);
	}

}