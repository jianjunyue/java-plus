package com.java.plus.lambda;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.ComparisonChain;
import com.java.plus.lambda.data.DataHelper;
import com.java.plus.lambda.data.PrintHelper;
import com.java.plus.lambda.model.Apple;
  
public class Sort {
	 
	public static void main(String[] args) {
		Sort sort=new Sort();
		
		 List<Apple> list=DataHelper.getApples();

//		 PrintHelper.print(sort.anonymous(list));
//		 PrintHelper.print(sort.lambda(list));
//		 PrintHelper.print(sort.lambda1(list));
		 PrintHelper.print(sort.lambda2(list));
		 PrintHelper.print(sort.lambda1_reversed(list));
		 PrintHelper.print(sort.lambda1_thenComparing(list)); 
		
	}
	
 
	private static final Comparator<Apple> comparator = Comparator.comparing(Apple::getWeight);
	private static final Comparator<Apple> comparator1 = (a1, a2) -> a1.getWeight() < a2.getWeight() ? -1
			: (a1.getWeight() > a2.getWeight() ? 1 : 0); // 待研究
	private static final Comparator<Apple> comparator2 = (o1, o2) -> {
		if (o1.getWeight() < o2.getWeight()) {
			return -1;
		} else if (o1.getWeight() > o2.getWeight()) {
			return 1;
		} else {
			return 0;
		}
	};

    /**
	 * 匿名类方式
	 */
	public List<Apple> anonymous(List<Apple> apples) {
		apples.sort(new Comparator<Apple>() {
			@Override
			public int compare(Apple o1, Apple o2) {
				// TODO Auto-generated method stub
				return o1.getWeight() - o2.getWeight();
			}
		});
		return apples;
	}

	/**
	 * lambda方式
	 */
	public List<Apple> lambda(List<Apple> apples) {
		apples.sort((Apple o1, Apple o2) -> {
			return o1.getWeight() - o2.getWeight();
		});
		return apples;
	}

	/**
	 * 方法引用方式
	 */
	public List<Apple> lambda1(List<Apple> apples) {
		apples.sort(Comparator.comparing(Apple::getWeight));
		return apples;
	}
	 
	/**
	 * 方法引用方式 - 该种方式原list 保持不变，生产一个新的list
	 */
	public List<Apple> lambda2(List<Apple> apples) {
		return apples.stream().sorted(Comparator.comparing(Apple::getWeight)).collect(Collectors.toList()); 
	}

	/**
	 * 方法引用方式 - 倒排
	 */
	public List<Apple> lambda1_reversed(List<Apple> apples) {
		apples.sort(Comparator.comparing(Apple::getWeight).reversed());
		return apples;
	}
	

	/**
	 * 方法引用方式 - 多字段排序
	 */
	public List<Apple> lambda1_thenComparing(List<Apple> apples) {
		apples.sort(Comparator.comparing(Apple::getWeight).thenComparing(Apple::getName));
		return apples;
	}
}
