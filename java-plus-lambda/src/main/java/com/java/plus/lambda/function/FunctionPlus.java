package com.java.plus.lambda.function;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import com.java.plus.lambda.common.MyPredicate;

/**
 * Function Function< T, R > 接收T对象，返回R对象 
 * Predicate Predicate< T >  接收T对象并返回boolean 
 * Consumer Consumer< T >    接收T对象，不返回值 
 * Supplier Supplier< T >    提供T对象（例如工厂），不接收值
 * 
 * BiConsumer<T, U>    接收T和U对象，不返回值 
 * 
 */
public class FunctionPlus {

	public static void main(String[] args) {

		FunctionPlus plus = new FunctionPlus();

		plus.runFunction();
		plus.runPredicate();
		plus.runMyPredicate();
		plus.runConsumer();
		plus.runSupplier();
	}

	private final Function<String, Integer> strLenFunction = str -> str.length();

	/**
	 * Function Function< T, R > 接收T对象，返回R对象
	 */
	public void runFunction() {
		System.out.println("--------------------Function-----------------------");

		int len = strLenFunction.apply("test");
		System.out.println(len);

		Function<String, String> strSubFunction = str -> str + ":";

		System.out.println(strSubFunction.apply("my"));

	}

	/**
	 * Predicate Predicate< T > 接收T对象并返回boolean
	 */
	public void runPredicate() {

		System.out.println("--------------------Predicate-----------------------");

		Predicate<String> strPredicate = str -> str.length() > 4;

		System.out.println(strPredicate.test("my"));
	}

	/**
	 * MyPredicate MyPredicate< T > 接收T对象并返回boolean，自定义MyPredicate
	 */
	public void runMyPredicate() {

		System.out.println("--------------------MyPredicate-----------------------");

		MyPredicate<String> strMyPredicate = str -> str.length() > 4;

		System.out.println(strMyPredicate.run("my"));
	}

	/**
	 * Consumer Consumer< T > 接收T对象，不返回
	 */
	public void runConsumer() {

		System.out.println("--------------------Consumer-----------------------");

		Consumer<String> strConsumer = str -> System.out.println(str.length());
		strConsumer.accept("mytest");
	}

	/**
	 * Supplier Supplier< T > 提供T对象（例如工厂），不接收值
	 */
	public void runSupplier() {

		String str = "test_";

		System.out.println("--------------------Supplier-----------------------");

		Supplier<String> strSupplier = () -> str + "runSupplier ";
		System.out.println(strSupplier.get());

		Supplier<String> strSupplier1 = () -> {
			return str + "runSupplier1 ";
		} ;
		System.out.println(strSupplier1.get());
	}

}
