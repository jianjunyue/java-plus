package com.java.plus.test.baidu.api.all;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

import com.java.plus.test.baidu.api.ExcelReaderUtils;
import com.java.plus.test.baidu.api.model.Comment;


/**
 * 错误码说明
 * https://ai.baidu.com/ai-doc/NLP/Bk6z52e59
 * baidu API后台
 * https://console.bce.baidu.com/ai/?_=1608728428989&fromai=1#/ai/nlp/report/index~appId=2098856
 * 情感倾向分析
 * https://ai.baidu.com/ai-doc/NLP/zk6z52hds
 * 
 * */
public class JobStart {
	
	

	public static void main(String[] args) {

		BaiduReaderCore.run();

		System.out.println("-----------STRAT---------");
//		main11111();
		System.out.println("-----------完成---------");
	}

	private static void toReadBaiduFile() {
		String file = "D:/data/query-hive-305302.xlsx";
		List<Comment> comments = ExcelReaderUtils.reader1(file);
		List<Comment> temp = distinctList(comments);
		Map<String, BaiduContent> mapBaidu = getMapBaiduContent();
		List<String> list = new ArrayList<String>();
		temp.stream().forEach(comment -> {
			String str = comment.getContent().toLowerCase();
			if (!mapBaidu.containsKey(str)) {
				list.add(str);
			}
		});

		file = "D:/data/readed-baidu.txt";// 需要跑的数据
		try {
			FileUtils.writeLines(new File(file), list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static List<Comment> distinctList(List<Comment> comments) {
		return comments.stream().filter(distinctByKey(comment -> comment.getContent().toLowerCase()))
				.collect(Collectors.toList());
	}

	private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Map<Object, Boolean> seen = new ConcurrentHashMap<>();
		return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

	private static Map<String, BaiduContent> getMapBaiduContent() {
		Map<String, BaiduContent> mapBaidu = new HashMap<String, BaiduContent>();
		ExcelReaderUtils.readerComment().forEach(content -> {
			mapBaidu.put(content.getContent().toLowerCase(), content);
		});
		return mapBaidu;
	}

	public static void main11111() {
		try {
			List<String> listdata = new ArrayList<String>();
			List<String> list = FileUtils.readLines(new File("D:\\data\\data\\src\\TireTag_content_2.txt"));
			list.stream().forEach(action -> {
				String[] temp = action.split("	");
				if (temp.length == 2) {
					listdata.add(temp[1]);
//					System.out.println(temp[1]);
				}

			});
			String file = "D:/data/readed-baidu_data.txt";// 需要跑的数据
			try {

				System.out.println(listdata.size());
				FileUtils.writeLines(new File(file), listdata);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("-----------完成---------");
//		BaiduReaderCore.run();
	}
}
