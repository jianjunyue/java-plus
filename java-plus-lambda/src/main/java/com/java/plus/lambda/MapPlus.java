package com.java.plus.lambda;

import java.util.HashMap;
import java.util.Map;

public class MapPlus {

	private static Map<Integer, String> mapinfo = new HashMap<>();
	static {
		mapinfo.put(1, "test1");
		mapinfo.put(2, "test2");
		mapinfo.put(3, "test3");
		mapinfo.put(4, "test4");
	}

	public static void main(String[] args) {
		MapPlus plus = new MapPlus();

		plus.forEach();
		plus.getOrDefault();
		plus.computeIfAbsent();
		plus.computeIfPresent();
		plus.compute();

	}

	/**
	 * 1、forEach方法
	 */
	public void forEach() {

		mapinfo.forEach((id, name) -> System.out.println("id:" + id + " , name:" + name));

	}

	/**
	 * 2、getOrDefault方法
	 */
	public void getOrDefault() {
		System.out.println("-------------getOrDefault--------------");
		System.out.println(mapinfo.getOrDefault("1", "test1"));
		System.out.println(mapinfo.getOrDefault("0", "test0"));

	}

	/**
	 * 3、计算方法- computeIfAbsent 如果key不存在，侧把方法返回的结果添加到map
	 * 对 hashMap 中指定 key 的值进行重新计算，如果不存在这个 key，则添加到 hasMap 中
	 */
	public void computeIfAbsent() {
		System.out.println("-------------computeIfAbsent--------------");
		mapinfo.computeIfAbsent(-1, key -> compute1(key));
		System.out.println(mapinfo);
	}

	/**
	 * 3、计算方法- computeIfPresent 如果key存在，侧把方法返回的结果添加到map
	 * 对 hashMap 中指定 key 的值进行重新计算，前提是该 key 存在于 hashMap 中。
	 */
	public void computeIfPresent() {
		System.out.println("-------------computeIfPresent--------------");
		mapinfo.computeIfPresent(1, (key, value) -> compute2(key, value));
		System.out.println(mapinfo);
	}


	/**
	 * 3、计算方法- computeIfPresent 如果key存在，侧把方法返回的结果添加到map
	 *  无论key是否存在，都对 hashMap 中指定 key 的值进行重新计算，。
	 */
	public void compute() {
		System.out.println("-------------compute--------------");
		mapinfo.compute(1, (key, value) -> compute3(key, value));
		mapinfo.compute(-3, (key, value) -> compute3(key, value));
		System.out.println(mapinfo);
	}

	public String compute1(int key) {
		return "test_" + key;
	}

	public String compute2(int key, String value) {
		return "test_" + key + "_" + value;
	}

	public String compute3(int key, String value) {
		return "test:" + key + ":" + value;
	}

}
