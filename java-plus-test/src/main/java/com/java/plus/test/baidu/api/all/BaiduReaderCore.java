package com.java.plus.test.baidu.api.all;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSON;
import com.java.plus.test.baidu.api.BaiduAiCore;
import com.java.plus.test.baidu.api.ExcelReaderUtils;
import com.java.plus.test.baidu.api.model.AITag;
import com.java.plus.test.baidu.api.model.BaiduItem;
import com.java.plus.test.baidu.api.model.Item;

public class BaiduReaderCore {

	private static int sys = 0;

	private static Map<String, Integer> runedMap = new HashMap<>();// 已经跑过的评论
	private static List<BaiduContent> listBaiduContent = null;// 已经跑过的评论,已经tag信息

	public static void run() {
		try {
			init();
			List<String> listData = getNewBaiduComment();

			System.out.println(listData.size());

			Collections.reverse(listData);
			List<String> temp = listData; 

			int count = 0;
			List<BaiduContent> listPageTemp = new ArrayList<BaiduContent>();// 已经跑过的评论,已经tag信息
		  	List<String> runnedTemp = new ArrayList<>();

			for (int i = 0; i < temp.size(); i++) {

				String comment = temp.get(i);
				if (!runedMap.containsKey(comment)) {
					runedMap.put(comment, 1);
					BaiduItem baiduItem = BaiduAiCore.getBaiduTags(comment);
					List<AITag> aiTags = getAITags(baiduItem);
					String tagJson = JSON.toJSONString(aiTags);

					if (tagJson.length() > 5) {
						BaiduContent baiduContent = new BaiduContent();
						baiduContent.setContent(comment);
						baiduContent.setBaiduBaseJson(baiduItem.getBaseJson());
						baiduContent.setTagJson(tagJson);
						listPageTemp.add(baiduContent);
						count++;
					}
					

					runnedTemp.add(comment);

					System.out.println("-----------" + count + "/" + i + "---------");

					if (listPageTemp.size() > 3000 ) {
						String file = "D:/data/temp/write-baiducontent-" + i + ".xlsx";
						if (sys == 1) {
							file = "/home/tuhu_dev/baidunlp/xlsx/write-baiducontent-" + i + ".xlsx";
						}

						ExcelReaderUtils.write(listPageTemp, file);
						listPageTemp=new ArrayList<BaiduContent>(); 
						System.out.println("-----------" + file + "---------");

						String path = "D:/data/temp/runned-baiducontent" + i + ".txt";
						if (sys == 1) {
							path = "/home/tuhu_dev/baidunlp/txt/runned-baiducontent-" + i + ".txt";
						}
						File f = new File(path);
						FileUtils.writeLines(f, runnedTemp);
						runnedTemp = new ArrayList<String>();
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		String file = "D:/data/write-baiducontent1.xlsx";
		if (sys == 1) {
			file = "/home/tuhu_dev/baidunlp/write-baiducontent1.xlsx";
		}
		ExcelReaderUtils.write(listBaiduContent, file);
		System.out.println("-----------完成---------");
	}

	private static void init() {
		try {
			listBaiduContent = getRunedBaiduComment();
			listBaiduContent.stream().forEach(action -> {
				runedMap.put(action.getContent(), 1);
			});

			String path = "D:/data/temp/runned-baiducontent.txt";
			if (sys == 1) {
				path = "/home/tuhu_dev/baidunlp/runned-baiducontent.txt";
			}
			File f = new File(path);

			List<String> temp = FileUtils.readLines(f);
			temp.stream().forEach(action -> {
				runedMap.put(action, 1);
//				runned.add(action);
			});
			if (temp.size() > 0) {
				System.out.println(runedMap.size());
				System.out.println(temp.get(0));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 已经跑了的点评
	 */
	private static List<BaiduContent> getRunedBaiduComment() {
		String file = "D:/data/write-baiducontent.xlsx";
		if (sys == 1) {
			file = "/home/tuhu_dev/baidunlp/write-baiducontent.xlsx";
		}

		List<BaiduContent> comments = new ArrayList<>();
		try {
			List<Map<String, Object>> listdata = ExcelReaderUtils.reader(file);

			listdata.stream().forEach(map -> {
				BaiduContent baidu = new BaiduContent();
				baidu.setContent(Optional.ofNullable(map.get("content")).orElse("").toString());
				baidu.setTagJson(Optional.ofNullable(map.get("tagJson")).orElse("").toString());
				baidu.setBaiduBaseJson(Optional.ofNullable(map.get("baiduBaseJson")).orElse("").toString());

				comments.add(baidu);
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comments;
	}

	public static List<BaiduContent> getRunedBaiduComment(String file) {
		List<Map<String, Object>> listdata = ExcelReaderUtils.reader(file);
		List<BaiduContent> comments = new ArrayList<>();

		listdata.stream().forEach(map -> {
			BaiduContent baidu = new BaiduContent();
			baidu.setContent(Optional.ofNullable(map.get("content")).orElse("").toString());
			baidu.setTagJson(Optional.ofNullable(map.get("tagJson")).orElse("").toString());
			baidu.setBaiduBaseJson(Optional.ofNullable(map.get("baiduBaseJson")).orElse("").toString());

			comments.add(baidu);
		});

		return comments;
	}

	/**
	 * 需要跑的点评
	 */
	private static List<String> getNewBaiduComment() {
		String file = "D:/data/readed-baidu_data.txt";

		if (sys == 1) {
			file = "/home/tuhu_dev/baidunlp/readed-baidu_data.txt";
		}
		List<String> list = null;
		try {
			list = FileUtils.readLines(new File(file));

			System.out.println(list.size());
			list = list.stream().filter(str -> str.length() > 5).collect(Collectors.toList());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	private static List<AITag> getAITags(BaiduItem baiduItem) {
		List<AITag> aiTags = new ArrayList<>();
		try {
			baiduItem.getItems().forEach(item -> {
				aiTags.add(getAITag(item));
			});
		} catch (Exception ex) {
			System.out.println(JSON.toJSONString(baiduItem));
		}
		return aiTags;
	}

	private static AITag getAITag(Item item) {
		AITag aiTag = new AITag();
		aiTag.setAdj(item.getAdj());
		aiTag.setProp(item.getProp());
		aiTag.setSentiment(item.getSentiment());
		return aiTag;
	}

}
