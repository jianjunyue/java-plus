package com.java.plus.predict.load;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import lombok.extern.slf4j.Slf4j;
import ml.dmlc.xgboost4j.java.Booster;
import ml.dmlc.xgboost4j.java.XGBoost;

@Slf4j
public class ModelLoad {

	public Booster getModel(String modelFile) {
		File file = new File(modelFile);
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(file);
			Enumeration<?> entries = zipFile.entries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				log.info("LightGBMModelLoadParse-unZipAndXgboostLoad-模型文件名：{}", entry.getName());
				InputStream inputStream = zipFile.getInputStream(entry);
				Booster booster = modelParse(inputStream);
				inputStream.close();
				
				return booster;
				// 关闭流
			}
		} catch (Exception e) {
			log.error("LightGBMModelLoadParse-unZipAndXgboostLoad-Exception", e);
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

	public Booster modelParse(InputStream inputStream) {
		try {
			if (null != inputStream) {
				// 模型加载
				Booster booster = XGBoost.loadModel(inputStream);
return booster;
			}
		} catch (Exception e) {
			log.error("XgboostModelLoadParse-模型文件解析异常-version:{}-Exception", e);
		} finally {
			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		log.error("XgboostModelLoadParse-模型文件解析异常-version:{}");
		return null;
	}

}
