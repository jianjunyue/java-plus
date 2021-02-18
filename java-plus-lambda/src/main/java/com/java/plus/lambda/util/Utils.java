package com.java.plus.lambda.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.math.NumberUtils;
 

public class Utils {

	private void listUtils() {

		List foodList = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(foodList)) {
			System.out.println("foodList is isEmpty");
		}
		
		
	}
	private void mapUtils() {

		Map<Long, String> foodMap = new HashMap<>();
		if (MapUtils.isEmpty(foodMap)) {
			System.out.println("foodMap is isEmpty");
		}
		
		
	}

}
