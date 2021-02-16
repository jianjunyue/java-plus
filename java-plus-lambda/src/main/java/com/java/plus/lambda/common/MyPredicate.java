package com.java.plus.lambda.common;



@FunctionalInterface
public interface MyPredicate<T> {

	boolean run(T t); 

}
