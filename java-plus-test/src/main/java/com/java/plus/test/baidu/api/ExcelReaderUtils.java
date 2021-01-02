package com.java.plus.test.baidu.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.java.plus.test.baidu.api.all.BaiduContent;
import com.java.plus.test.baidu.api.model.Comment;
import com.java.plus.test.baidu.api.model.CommentTag;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

public class ExcelReaderUtils {

	public static List<Comment> reader() {
		String file = "com\\java\\plus\\test\\baidu\\api\\query-hive-100000.xlsx";
		ExcelReader reader = ExcelUtil.getReader(FileUtil.file(file));
		List<Map<String, Object>> list = Optional.ofNullable(reader.readAll())
				.orElse(new ArrayList<Map<String, Object>>());

		List<Comment> comments = new ArrayList<>();

		list.stream().forEach(map -> {
			Comment comment = new Comment();
			comment.setCommentid(Integer.parseInt(Optional.ofNullable(map.get("dwd_srv_comment.commentid")).orElse(0).toString()));
			comment.setContent(Optional.ofNullable(map.get("dwd_srv_comment.comment_content")).orElse("").toString());
			comments.add(comment);
		});

		return comments;
	}
	

	public static List<Comment> reader1(String file ) { 
		ExcelReader reader = ExcelUtil.getReader(FileUtil.file(file));
		List<Map<String, Object>> list = Optional.ofNullable(reader.readAll())
				.orElse(new ArrayList<Map<String, Object>>());

		List<Comment> comments = new ArrayList<>();

		list.stream().forEach(map -> {
			Comment comment = new Comment();
			comment.setCommentid(Integer.parseInt(Optional.ofNullable(map.get("dwd_srv_comment.commentid")).orElse(0).toString()));
			comment.setContent(Optional.ofNullable(map.get("dwd_srv_comment.comment_content")).orElse("").toString());
			comments.add(comment);
		});

		return comments;
	}
	
	

	public static List<Map<String, Object>> reader(String file ) { 
		ExcelReader reader = ExcelUtil.getReader(FileUtil.file(file));
		List<Map<String, Object>> list = Optional.ofNullable(reader.readAll())
				.orElse(new ArrayList<Map<String, Object>>());
		return list;
	}
	
	public static List<BaiduContent> readerComment() {
		String file ="D:/data/write-hive-100000.xlsx" ;
		ExcelReader reader = ExcelUtil.getReader(FileUtil.file(file));
		List<Map<String, Object>> list = Optional.ofNullable(reader.readAll())
				.orElse(new ArrayList<Map<String, Object>>());

		List<BaiduContent> comments = new ArrayList<>();

		list.stream().forEach(map -> { 
			BaiduContent baidu=new BaiduContent();
			baidu.setContent(Optional.ofNullable(map.get("content")).orElse("").toString());
			baidu.setTagJson(Optional.ofNullable(map.get("tagJson")).orElse("").toString());
			baidu.setBaiduBaseJson(Optional.ofNullable(map.get("baiduBaseJson")).orElse("").toString());

			comments.add(baidu);
		});

		return comments;
	}
	

	public static <T> void write(List<T> list,String file ) {
		try { 
			ExcelWriter writer = ExcelUtil.getWriter(file);
			writer.write(list,true);
			//关闭writer，释放内存
			writer.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}


	public static void write(List<CommentTag> list) {
		try {
			String file = "D:/data/write-hive-100000.xlsx";
			ExcelWriter writer = ExcelUtil.getWriter(file);
			writer.write(list,true);
			//关闭writer，释放内存
			writer.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static void write(List<CommentTag> list, int i ) {
		try {
			String file = "D:/data/temp/write-hive-"+i+".xlsx";
			ExcelWriter writer = ExcelUtil.getWriter(file);
			writer.write(list,true);
			//关闭writer，释放内存
			writer.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	

	public static void writeFilter(List<String> list) {
		try {
			String file = "D:/data/write-hive-101.xlsx";
			ExcelWriter writer = ExcelUtil.getWriter(file);
			writer.write(list,true);
			//关闭writer，释放内存
			writer.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	

}
