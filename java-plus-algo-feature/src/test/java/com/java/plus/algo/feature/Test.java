package com.java.plus.algo.feature;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mvel2.MVEL;
import org.mvel2.ParserContext;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.java.plus.algo.feature.core.FeatureExecutor;
import com.java.plus.algo.feature.operator.NumberOperator;
import com.java.plus.algo.feature.operator.TextOperator;
import com.java.plus.algo.feature.operator.UtilsOperator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

public class Test {
	private static Map<String, Object> operatorEnv = new HashMap<>();

	static {
		operatorEnv.put("number", new NumberOperator());
		operatorEnv.put("text", new TextOperator());
	}

	private static Map<String, Object> prepareEnv(Map<String, Object> paramEnv) {
		Map<String, Object> env = Maps.newHashMapWithExpectedSize(paramEnv.size() + paramEnv.size());
		env.putAll(operatorEnv);
		env.putAll(paramEnv);
		return env;
	}

	public static void main(String[] args) {

		ParserContext parserContext = new ParserContext();

		Method[] methods = TextOperator.class.getMethods();
//
		for (int i = 0; i < methods.length; i++) {
			if (Modifier.isStatic(methods[i].getModifiers())) {
				//只支持类下静态方法（static）
				parserContext.addImport(methods[i].getName(), methods[i]);
			}
		}
		
//		
//		parserContext.addImport("text", TextOperator.class);
//		parserContext.addImport(TextOperator.class);
//		parserContext.addImport(NumberOperator.class);
		String operator = "text.toOneHot(appid,[\"android_app\",\"ios_app\"])";
//		Object expression = MVEL.compileExpression(operator, parserContext);
		Object expression = MVEL.compileExpression(operator);

		Map<String, Object> paramEnv = new HashMap<>();
		paramEnv.put("appid", "android_app");
		Map<String, Object> env = prepareEnv(paramEnv);

		Object result = MVEL.executeExpression(expression, env);

		System.out.println(JSON.toJSONString(result));

//        if (executor.getFeature().getReturnValueNum() == 1 && result instanceof Number) {
//            vector[0] = ((Number) result).doubleValue();
//        } else {
//            double[] resultArray = ((double[]) result);
//            System.arraycopy(resultArray, 0, vector, 0, Math.min(resultArray.length, vector.length));
//        }

	}

}
