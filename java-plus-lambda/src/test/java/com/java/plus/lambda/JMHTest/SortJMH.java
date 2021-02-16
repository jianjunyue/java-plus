package com.java.plus.lambda.JMHTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import com.java.plus.lambda.Sort;
import com.java.plus.lambda.data.DataHelper;
import com.java.plus.lambda.model.Apple;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value=2, jvmArgs= {"-Xms4G","-Xmx4G"})
//@Warmup(iterations = 3)
//@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
public class SortJMH {

	public static void main(String[] args) {
		try {
			Options opt = new OptionsBuilder().include(SortJMH.class.getSimpleName()).forks(1).build();
			new Runner(opt).run();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static List<Apple> list = DataHelper.getApples(1000);
	public static List<Apple> list1 = DataHelper.getApples(1000);
	public static List<Apple> list2 = DataHelper.getApples(1000);

	@Benchmark
	public void testlambda() {
		Sort sort = new Sort();
		sort.lambda(list);
	}

	@Benchmark
	public void testlambda1() {
		Sort sort = new Sort();
		sort.lambda1(list1);
	}

	@Benchmark
	public void testlambda2() {
		Sort sort = new Sort();
		sort.lambda2(list2);
	}

}
