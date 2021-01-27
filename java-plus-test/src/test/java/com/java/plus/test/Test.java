package com.java.plus.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.text.csv.CsvWriter;
import cn.hutool.core.util.CharsetUtil;

import org.apache.commons.io.FileUtils;

import com.java.plus.test.baidu.api.all.BaiduContent;
import com.java.plus.test.baidu.api.all.BaiduReaderCore;

public class Test {

	public static void main(String[] args) {
		List<NewContent> newContents = maindata();
		List<String[]> list = new ArrayList<>();
		newContents.stream().forEach(action -> {
			String[] str = new String[] { String.valueOf(action.getCommentid()), action.getComment_content(),
					action.getTagJson(), action.getBaiduBaseJson() };
			list.add(str);
		});
		String filePath = "D:/data/baidu/baidutag_0127_utf8.csv";
		CsvWriter csv = CsvUtil.getWriter(filePath, CharsetUtil.CHARSET_UTF_8);
		csv.write(list);
		System.out.println(list.size());
		System.out.println("-----------main完成---------");
		
		  filePath = "D:/data/baidu/baidutag_0127_utf8_sample.csv";
		  csv = CsvUtil.getWriter(filePath, CharsetUtil.CHARSET_UTF_8);
		csv.write(list.subList(0, 1000));
	}

	public static List<NewContent> maindata() {
		List<NewContent> newContents = new ArrayList<>();
		try {
			Map<String, BaiduContent> map = readerMap();
			List<String> list = FileUtils.readLines(new File("D:\\data\\data\\src\\TireTag_content_2.txt"));
			list.stream().forEach(action -> {
				String[] temp = action.split("	");
				NewContent newContent = new NewContent();
				if (temp.length == 2) {
					newContent.setCommentid(Integer.parseInt(temp[0]));
					newContent.setComment_content(temp[1]);

					BaiduContent baiduContent = map.getOrDefault(newContent.getComment_content(), new BaiduContent());

					newContent.setTagJson(baiduContent.getTagJson());
					newContent.setBaiduBaseJson(baiduContent.getBaiduBaseJson());
					newContents.add(newContent);
				}

			});

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("-----------完成---------");
		return newContents;
	}

	private static Map<String, BaiduContent> readerMap() {
		Map<String, BaiduContent> map = new HashMap<>();
		File f = new File("D:\\data\\xlsx");
		List<File> fileList = (List<File>) FileUtils.listFiles(f, null, false);
		List<BaiduContent> comments = new ArrayList<>();
		fileList.forEach(action -> {
			List<BaiduContent> temp = BaiduReaderCore.getRunedBaiduComment(action.getPath());
			comments.addAll(temp);
		});

//		String file = "D:\\data\\baidunlp\\write-baiducontent-6217.xlsx";
//		List<BaiduContent> comments = BaiduReaderCore.getRunedBaiduComment(file);

		System.out.println(comments.size());
		comments.stream().forEach(action -> {
			map.put(action.getContent(), action);
		});

		return map;
	}

}
