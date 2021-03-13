package com.java.plus.predict.model.xgboost;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.java.plus.predict.PredictScore;
import com.java.plus.predict.data.FeatureVectorData;
import com.java.plus.predict.feature.FeatureVector;
import com.java.plus.predict.load.ModelLoad;

import ml.dmlc.xgboost4j.java.Booster;
import ml.dmlc.xgboost4j.java.DMatrix;
import ml.dmlc.xgboost4j.java.XGBoostError;

public class XGBoostPredict {
	public static void main(String[] args) {
		String modelFile = "\\data\\webroot\\zhuque\\0nKIVck6I8g77b1FsWztkg.zip";
		ModelLoad modelLoad = new ModelLoad();
		Booster booster = modelLoad.getModel(modelFile);

		List<FeatureVector> totalFeatureList = FeatureVectorData.getFeatureVectors();
		int colNum = 768;
		int rowNum = totalFeatureList.size();
		float[] modelTotalCols = new float[colNum * rowNum];
//		float missingValue = 0f;
		Float missingValue = Float.NaN;
		// 目标数组的起始位置
		int startIndex = 0;
		for (int i = 0; i < totalFeatureList.size(); i++) {
			FeatureVector featureVector = totalFeatureList.get(i);
			float[] feature = featureVector.getFeature();

			if (i > 0) {
				// i为0时，目标数组的起始位置为0 ,i为1时，目标数组的起始位置为第一个数组长度
				// i为2时，目标数组的起始位置为第一个数组长度+第二个数组长度
				startIndex = startIndex + feature.length;
			}
			System.arraycopy(feature, 0, modelTotalCols, startIndex, feature.length);
		}

		// 预测
		DMatrix dataMat;
		try {
			dataMat = new DMatrix(modelTotalCols, rowNum, colNum, missingValue);
			float[][] predicts = booster.predict(dataMat, Boolean.FALSE, 0);
			for (int i = 0; i < predicts.length; i++) {
				PredictScore predictScore = new PredictScore();

				float xgbScore = predicts[i][0];
				String pid = totalFeatureList.get(i).getPid();

				predictScore.setPid(pid);
				predictScore.setScore(xgbScore);
				System.out.println(JSON.toJSON(predictScore));
			}
		} catch (XGBoostError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//    	float[][] score=	booster.predict(data);
		System.out.println("Hello World!");

	}
}
