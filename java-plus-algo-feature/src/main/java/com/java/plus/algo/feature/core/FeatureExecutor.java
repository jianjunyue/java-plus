package com.java.plus.algo.feature.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Maps;
import com.java.plus.algo.feature.exception.FeatureException;
import com.java.plus.algo.feature.model.Feature;
import com.java.plus.algo.feature.operator.UtilsOperator;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeatureExecutor implements Serializable {
    Feature feature;
    Object expression;
    Object outlierCheckExpression;
    private double[] defaultVector;
    Map<String, Object> defaultRawMap;

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public Object getExpression() {
        return expression;
    }

    public void setExpression(Object expression) {
        this.expression = expression;
    }

    public Object getOutlierCheckExpression() {
        return outlierCheckExpression;
    }

    public void setOutlierCheckExpression(Object outlierCheckExpression) {
        this.outlierCheckExpression = outlierCheckExpression;
    }

    public double[] getDefaultVector() {
        double[] temp = new double[defaultVector.length];
        System.arraycopy(defaultVector, 0, temp, 0, defaultVector.length);
        return temp;
    }

    public void setDefaultVector(double[] defaultVector) {
        this.defaultVector = defaultVector;
    }

    public Map<String, Object> getDefaultRawMap() {
        return defaultRawMap;
    }

    public void setDefaultRawMap(Map<String, Object> defaultRawMap) {
        this.defaultRawMap = defaultRawMap;
    }

    public FeatureExecutor(Feature feature) {
        this.feature = feature;
        String validateMessage = validate();

        if (validateMessage != null) {
            throw new FeatureException(validateMessage);
        }

        if (StringUtils.isNotBlank(feature.getOperator()) && feature.getOperator() != null) {

            ParserContext parserContext = new ParserContext();

            Method[] methods = UtilsOperator.class.getMethods();

            for (int i = 0; i < methods.length; i++) {
                if (Modifier.isStatic(methods[i].getModifiers())) {
                    parserContext.addImport(methods[i].getName(), methods[i]);
                }
            }
            
//            System.out.println("---------------MVEL.compileExpression-------------------");
//            executor.getExpression()
            expression = MVEL.compileExpression(feature.getOperator(), parserContext);
           
//            System.out.println("---------------MVEL.compileExpression-------------------");

        }

        if (StringUtils.isNotBlank(feature.getOutlierCheck()) && feature.getOutlierCheck() != null) {
            outlierCheckExpression = MVEL.compileExpression(feature.getOutlierCheck());
        }
        defaultVector = buildDefaultVector();
        defaultRawMap = buildDefaultRowMap();
    }

    private String validate() {
        if (feature.getName() == null) {
            return String.format("特征名称为空 featureid=%d", feature.getId());
        }
        if (CollectionUtils.isEmpty(feature.getRawList())) {
            return "原始特征列表为空";
        }
        if (CollectionUtils.isNotEmpty(feature.getReturnValueLoc()) && feature.getReturnValueLoc().size() != feature.getReturnValueNum()) {
            return "如果设置了ReturnValueLoc，则ReturnValueLoc.size 必须等于 ReturnValueNum";
        }
        return null;
    }

    /**
     * 1. 先从原始特征中抽取数据，如果没有就用defaultRawMap中的数据填充，如果还是没有，就直接返回默认向量。
     * 2. 然后判断是否有算子表达式，如果没有算子表达式说明要直接用原始特征作为向量。
     * 3. 如果有算子表达式，则用算子表达式
     * @param rawDataMap
     * @return
     */
    public double[] toVector(Map<String, Object> rawDataMap) {

        List<String> rawList = feature.getRawList();

        Map<String, Object> paramEnv = Maps.newHashMap();

        for (String rawName : rawList) {
            Object rawData = rawDataMap.getOrDefault(rawName, defaultRawMap.get(rawName));
            if (rawData == null) {
                return getDefaultVector();
            }
            paramEnv.put(rawName, rawData);
        }
        boolean isNormal = checkOutlier(paramEnv);
        //正常数据进入计算
        if (isNormal) {
            // 无特征计算的数据返回特征值本身
            if (expression == null) {
                double[] result = getDefaultVector();
                int min = Math.min(rawList.size(), feature.getReturnValueNum());
                for (int i = 0; i < min; i++) {
                    String rawName = rawList.get(i);
                    Object rawData = paramEnv.get(rawName);
                    if (rawData instanceof Number) {
                        result[i] = ((Number) rawData).doubleValue();
                    }
                }
                return result;
            // 有特征计算的数据返回计算后的特征
            } else {
                double[] eval = ExecutorEngine.evalOperator(paramEnv, this);
                return calcEval(eval);
            }
        }
        // 异常数据返回默认值
        return getDefaultVector();
    }

    /**
     *
     * @param paramEnv
     * @return True: 非异常值，False 异常值
     */
    private boolean checkOutlier(Map<String, Object> paramEnv) {
        if (outlierCheckExpression == null) {
            return true;
        } else {
            return ExecutorEngine.outlierCheck(paramEnv, this);
        }
    }

    private double[] buildDefaultVector() {
        double[] result = new double[feature.getReturnValueNum()];

        if ( ! CollectionUtils.isEmpty(feature.getDefaultValues())) {
            int min = Math.min(feature.getDefaultValues().size(), result.length);

            /**
             * 如果default只有一个的时候，defaultVector会把所有位置都填充defaultValue
             * 如果有多个位置，但是按位置填充defaultVector，不够填0
             */
            if (min == 1) {
                for (int i = 0;i < result.length; i++) {
                    result[i] = feature.getDefaultValues().get(0);
                }
            } else {
                for (int i = 0;i < min; i++) {
                    result[i] = feature.getDefaultValues().get(i);
                }
            }
        }

        return result;
    }

    private Map<String, Object> buildDefaultRowMap(){
        Map<String, Object> defaultMap = new HashMap<>();
        String defaultRawMapStr = feature.getDefaultRawMap();

        if (StringUtils.isNotBlank(defaultRawMapStr)) {
            defaultMap.putAll(JSON.parseObject(defaultRawMapStr,
                    new TypeReference<Map<String, Object>>() {
                    }.getType()));
        }
        return defaultMap;

    }


    private double[] calcEval(double[] evalResult) {
        List<Integer> returnValueLoc = feature.getReturnValueLoc();
        if (CollectionUtils.isEmpty(returnValueLoc)) {
            return evalResult;
        }

        double[] result = new double[feature.getReturnValueNum()];

        int i = 0;
        for (Integer loc : returnValueLoc) {
            result[i++] = evalResult[loc];
        }
        return result;
    }


}
