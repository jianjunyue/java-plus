//package com.java.plus.test.baidu.api;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * 跑成功过的数据不再跑；跑失败的，跑不出结果的，记录下来也不再跑
// */
//public class DataCheck {
//
//	public static List<String> readerFilterComment() {
//		String[] files = { "D:/data/write-hive-10.xlsx" };
//		List<String> comments = new ArrayList<>();
//		for (int i = 0; i < files.length; i++) {
//			List<String> list = ExcelReaderUtils.readerComment(files[i]);
//			list = list.stream().filter(comment -> comment.length() > 0).collect(Collectors.toList());
//			comments.addAll(list);
//		}
//		return comments;
//	} 
//}
