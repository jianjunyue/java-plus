package com.java.plus.algo.feature.core;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.java.plus.algo.feature.exception.FeatureException;
import com.java.plus.algo.feature.model.Feature;
import com.java.plus.algo.feature.operator.NumberOperator;
import com.java.plus.algo.feature.operator.TextOperator;

import lombok.extern.slf4j.Slf4j;
import org.mvel2.MVEL;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ExecutorEngine implements Serializable {
    /**
     * 算子上下文
     */
    private static Map<String, Object> operatorEnv = new HashMap<>();

    static {
        operatorEnv.put("number",new NumberOperator());
        operatorEnv.put("text", new TextOperator());
    }

    /**
     * @param paramEnv 参数上下文
     * @param executor executor.expression notnull
     * @return 向量
     */
    public static double[] evalOperator(Map<String, Object> paramEnv, FeatureExecutor executor) {
        double[] vector = executor.getDefaultVector();
        try {
            Map<String, Object> env = prepareEnv(paramEnv, executor);

            Object result = MVEL.executeExpression(executor.getExpression(), env);
            if (executor.getFeature().getReturnValueNum() == 1 && result instanceof Number) {
                vector[0] = ((Number) result).doubleValue();
            } else {
                double[] resultArray = ((double[]) result);
                System.arraycopy(resultArray, 0, vector, 0, Math.min(resultArray.length, vector.length));
            }
            return vector;
        } catch (Exception e) {
            FeatureException featureException = new FeatureException(e, e.getMessage() + "\n" + JSON.toJSONString(executor.getFeature()));
            log.error("error meesage", featureException);
            throw featureException;
        }

    }

    private static Map<String, Object> prepareEnv(Map<String, Object> paramEnv, FeatureExecutor executor) {
        Map<String, Object> env = Maps.newHashMapWithExpectedSize(paramEnv.size()+paramEnv.size());
        env.putAll(operatorEnv);
        env.putAll(paramEnv);
        return env;
    }

    public static boolean outlierCheck(Map<String, Object> paramEnv, FeatureExecutor executor) {
        Map<String, Object> env = prepareEnv(paramEnv, executor);
        try {
            Object result = MVEL.executeExpression(executor.getOutlierCheckExpression(), env);
            if (result instanceof Boolean) {
                return !((Boolean) result).booleanValue();
            }
        } catch (Exception e) {
            log.error("executor outlierCheck expression error, featureName:{}, param:{}", executor.getFeature().getName(), paramEnv, e);
        }
        return false;
    }

    private static void test(Map<String, Object> paramEnv, String expression) {
        Feature feature = new Feature();
        feature.setOperator(expression);
        feature.setName("name");
        feature.setRawList(Lists.newArrayList("shihu"));
        FeatureExecutor featureExecutor = new FeatureExecutor(feature);

        Object d = MVEL.executeExpression(featureExecutor.getExpression());
        Map<String, Object> env = Maps.newHashMap();
        env.putAll(operatorEnv);
        env.putAll(paramEnv);

        if (d instanceof double[]) {
            double[] dArr = (double[]) d;
            for (double v : dArr) {
                System.out.println(v);
            }
        } else {
            System.out.println(d);
        }
    }

    public static void main(String args[]) {
//
        Map<String, Object> map = new HashMap<>();

        map.put("gradename", "黄金会员");
        test(map, "toDate(\"2020-10-10\")");
//        map.put("car_series_price_level", "c");

//        System.out.println(Double.valueOf("NaN"));
//        map.put("brandcategory_ytd_sale_num",2);
//        test(map, env);
//        "".split(":").length
//
//        TextOperator textOperator = new TextOperator();
//        textOperator.durationOfDate("2019-09-15","2019-12-03");
    }
}
