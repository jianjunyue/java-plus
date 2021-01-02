//package com.java.plus.test.baidu.api;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.function.Function;
//import java.util.function.Predicate;
//
//import com.alibaba.fastjson.JSON;
//import com.java.plus.test.baidu.api.model.AITag;
//import com.java.plus.test.baidu.api.model.BaiduItem;
//import com.java.plus.test.baidu.api.model.Comment;
//import com.java.plus.test.baidu.api.model.CommentTag;
//import com.java.plus.test.baidu.api.model.Item;
//
//public class JobStart {
//
//	public static void main(String[] args) {
//		run();
//	}
//
//	private static void run() {
//		List<Comment> comments = ExcelReaderUtils.reader();
//
////		List<Comment> temp = comments.stream()
////				.filter(comment -> (!comment.getContent().equals("暂无评价")) && (comment.getContent().length() > 0))
////				.collect(Collectors.toList()).subList(0, 10);
//
//		List<Comment> temp = distinctList(comments);
//		System.out.println(comments.size());
//		System.out.println(temp.size());
//		
////		temp=temp.subList(0, 10);
//
//		List<CommentTag> list = new ArrayList<>();
//
//		for (int i = 0; i < temp.size(); i++) {
//
//			Comment comment = temp.get(i);
//
//			CommentTag tag = new CommentTag();
//
//			BaiduItem baiduItem = BaiduAiCore.getBaiduTags(comment.getContent());
//			List<AITag> aiTags = getAITags(baiduItem);
//
//			tag.setCommentid(comment.getCommentid());
//			tag.setContent(comment.getContent());
//			tag.setTagJson(JSON.toJSONString(aiTags));
//			tag.setBaiduBaseJson(baiduItem.getBaseJson());
//
//			list.add(tag);
//
//			if (i % 1000 == 0) {
//				ExcelReaderUtils.write(
//						list.stream().filter(data -> data.getTagJson().length() > 5).collect(Collectors.toList()), i);
//				System.out.println("-----------" + i + "---------");
//			}
//		}
//		ExcelReaderUtils.write(list.stream().filter(tag -> tag.getTagJson().length() > 5).collect(Collectors.toList()));
//		System.out.println("-----------完成---------");
//	}
//
//	private static List<AITag> getAITags(BaiduItem baiduItem) {
//		List<AITag> aiTags = new ArrayList<>();
//		try {
//			baiduItem.getItems().forEach(item -> {
//				aiTags.add(getAITag(item));
//			});
//		} catch (Exception ex) {
//			System.out.println(JSON.toJSONString(baiduItem));
//		}
//		return aiTags;
//	}
//
//	private static AITag getAITag(Item item) {
//		AITag aiTag = new AITag();
//		aiTag.setAdj(item.getAdj());
//		aiTag.setProp(item.getProp());
//		aiTag.setSentiment(item.getSentiment());
//		return aiTag;
//	}
//
//	private static List<Comment> distinctList(List<Comment> comments) {
//		return comments.stream().filter(distinctByKey(comment -> comment.getContent())).collect(Collectors.toList());
//	}
//
//	private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
//		Map<Object, Boolean> seen = new ConcurrentHashMap<>();
//		return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
//	}
//
//}
