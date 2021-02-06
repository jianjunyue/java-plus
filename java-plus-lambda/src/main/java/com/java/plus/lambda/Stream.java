package com.java.plus.lambda;

import java.util.List;

import com.java.plus.lambda.data.DataHelper;
import com.java.plus.lambda.data.PrintHelper;
import com.java.plus.lambda.model.Dish;

public class Stream {
	public static void main(String[] args) {
		Stream stream = new Stream();

		List<Dish> list = DataHelper.getDishs();

	}
}
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