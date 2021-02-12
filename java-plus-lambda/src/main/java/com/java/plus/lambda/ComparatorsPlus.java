package com.java.plus.lambda;

import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import  java.util.stream.Collector;
import  java.util.stream.Collectors;
/**
 * 自定义 Collectors.toList()
 * */
public class ComparatorsPlus<T> implements Collector<T,List<T>,List<T>>  {
	
	
	
	public static void main(String[] args) {
		Collectors.toList();
	}

	@Override
	public Supplier<List<T>> supplier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BiConsumer<List<T>, T> accumulator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BinaryOperator<List<T>> combiner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Function<List<T>, List<T>> finisher() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Characteristics> characteristics() {
		// TODO Auto-generated method stub
		return null;
	}
 

}
