package com.java.plus.predict.model.lightgbm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.alibaba.fastjson.JSON;
import com.java.plus.predict.data.FeatureVectorData;
import com.java.plus.predict.feature.FeatureVector;

import ml.dmlc.xgboost4j.java.Booster;

public class LightGBMPredict {

	public static void main(String[] args) {
		String modelFile = "D:\\data\\webroot\\zhuque\\q1zb--2DwrhVCQPDUaOERw.zip";
		LightGBMModel lightGBMModel;
		try {
			lightGBMModel = getModel(modelFile);

			List<FeatureVector> totalFeatureList = FeatureVectorData.getFeatureVectors();
			int colNum = 768;
			int rowNum = totalFeatureList.size();
			double[] modelTotalCols = new double[colNum * rowNum];
			// 目标数组的起始位置
			int startIndex = 0;
			for (int i = 0; i < totalFeatureList.size(); i++) {
				FeatureVector featureVector = totalFeatureList.get(i);
				float[] feature = featureVector.getFeature();
				for (float ele : feature) {
					modelTotalCols[startIndex++] = ele;
				}
			}
			double[] predicts = lightGBMModel.predict(modelTotalCols, colNum);
			System.out.println(JSON.toJSONString(predicts));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static LightGBMModel getModel(String modelFile) {
		File file = new File(modelFile);
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(file);
			Enumeration<?> entries = zipFile.entries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) entries.nextElement();

				InputStream inputStream = zipFile.getInputStream(entry);
				LightGBMModel lightGBMModel = new LightGBMModel(inputStream);

//				log.info("LightGBMModelLoadParse-unZipAndXgboostLoad-模型文件名：{}", entry.getName());
				inputStream.close();
				return lightGBMModel;
				// 关闭流
			}
		} catch (Exception e) {
//			log.error("LightGBMModelLoadParse-unZipAndXgboostLoad-Exception", e);
		} finally {
			// 关闭流
			if (zipFile != null) {
				try {
					zipFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
