package com.java.plus.algo.feature.temp;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Test {

	public static void main(String[] args) {
		Function f = new Function("111", null);
		System.out.println(f.getName());

		for (Field field : Function.class.getDeclaredFields()) {
			ConfigField annotation = field.getAnnotation(ConfigField.class);
			if (annotation != null && ((field.getModifiers() & Modifier.STATIC) == 0)) {
				System.out.println(annotation.allowNull());
				System.out.println(annotation.document());
			}
		}
	}

}
