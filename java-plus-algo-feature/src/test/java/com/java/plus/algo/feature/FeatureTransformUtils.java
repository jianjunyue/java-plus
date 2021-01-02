package com.java.plus.algo.feature;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.java.plus.algo.feature.core.FeatureConfig;
import com.java.plus.algo.feature.enums.RawFeatureType;
import com.java.plus.algo.feature.model.Feature;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FeatureTransformUtils {

	private static final String splitChar = ",";

	public static FeatureConfig loadFeatureConfig(int featureVersion) {

		List<Feature> features = getFeatures();
		Map<String, RawFeatureType> featureTypeMap = getRawFeatureType() ;

		return new FeatureConfig(featureVersion, features, featureTypeMap);
	}

	/**
	 * {modelVersion=6, name=appid, rawList=appid,
	 * operator=text.toOneHot(appid,["android_app","ios_app"]), returnValueNum=2,
	 * returnValueLoc=, remarks=, defaultValues=, defaultRawMap=, outlierCheck=,
	 * status=0}
	 */
	private static List<Feature> getFeatures() {
		List<Feature> features = new ArrayList<Feature>();
		String file = "com\\java\\plus\\algo\\feature\\特征转换模板.xlsx";
		ExcelReader reader = ExcelUtil.getReader(FileUtil.file(file));
		List<Map<String, Object>> list = reader.readAll();

		list.forEach(map -> {
			Feature feature = getFeature(map);
			features.add(feature);
		});
		
		for(int i=0;i<features.size();i++) {
			features.get(i).setId(i);
		}
		
		return features;
	}

	private static Map<String, RawFeatureType> getRawFeatureType() {
		Map<String, RawFeatureType> featureTypeMap = new HashMap<String, RawFeatureType>();
		String file = "com\\java\\plus\\algo\\feature\\原始特征模板.xlsx";
		ExcelReader reader = ExcelUtil.getReader(FileUtil.file(file));
		List<Map<String, Object>> list = reader.readAll();

		list.forEach(map -> {
			String name = ObjectUtils.toString(map.get("name"), "");
			int rawDataType = NumberUtils.toInt(ObjectUtils.toString(map.get("rawDataType"), ""), 1);
			RawFeatureType type = rawDataType == 1 ? RawFeatureType.Number : RawFeatureType.Text;
			featureTypeMap.put(name, type);
		});
		return featureTypeMap;
	}

	private static Feature getFeature(Map<String, Object> map) {
		FeatureItem item = new FeatureItem(map);
		int id = 0;
		Feature feature = toFeature(id, item);
		return feature;
	}

	public static Feature toFeature(int id, FeatureItem item) {
		Feature feature = new Feature();

		feature.setName(item.getName());
		feature.setDefaultRawMap(item.getDefaultRawMap());
		String defaultValues = item.getDefaultValues();
		if (StringUtils.isNotBlank(defaultValues)) {
			String[] split = defaultValues.split(splitChar);
			List<Double> collect = Arrays.stream(split).map(Double::valueOf).collect(Collectors.toList());
			feature.setDefaultValues(collect);
		}
		feature.setId(id);
		feature.setOperator(item.getOperator());
		if (StringUtils.isNotBlank(item.getRawList())) {
			String[] split = item.getRawList().split(splitChar);
			feature.setRawList(Arrays.asList(split));
		}

		if (StringUtils.isNotBlank(item.getReturnValueLoc())) {
			String[] split = item.getReturnValueLoc().split(splitChar);
			List<Integer> collect = Arrays.stream(split).map(Integer::valueOf).collect(Collectors.toList());
			feature.setReturnValueLoc(collect);
		}

		feature.setOutlierCheck(item.getOutlierCheck());
		if (item.getReturnValueNum() != null) {
			feature.setReturnValueNum(NumberUtils.toInt(item.getReturnValueNum(), 1));
		}

		return feature;
	}

	@Data
	static class FeatureItem {

		public FeatureItem(Map<String, Object> map) {
			this.name = ObjectUtils.toString(map.get("name"), "");
			this.rawList = ObjectUtils.toString(map.get("rawList"), "");
			this.operator = ObjectUtils.toString(map.get("operator"), "");
			this.returnValueNum = ObjectUtils.toString(map.get("returnValueNum"), "1");
			this.defaultValues = ObjectUtils.toString(map.get("defaultValues"), "");
			this.returnValueLoc = ObjectUtils.toString(map.get("returnValueLoc"), "");
			this.remarks = ObjectUtils.toString(map.get("remarks"), "");
			this.defaultRawMap = ObjectUtils.toString(map.get("defaultRawMap"), "");
			this.outlierCheck = ObjectUtils.toString(map.get("outlierCheck"), "");
		}

		private String name;
		private String rawList;
		private String operator;
		private String returnValueNum;
		private String defaultValues;
		private String returnValueLoc;
		private String remarks;
		private String defaultRawMap;
		private String outlierCheck;

	}

}
