package com.java.plus;

import java.util.ArrayList;
import java.util.List;
 

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test1 {
	 private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	public static void main(String[] args) throws JsonProcessingException {

		List<TagInfo> list=new ArrayList<TagInfo>();

		TagInfo tag=new TagInfo();
		tag.setTagType("速度");
		tag.setComment("神一样的超级无敌快速度");
		list.add(tag);
		
		tag=new TagInfo();
		tag.setTagType("静音舒适");
		tag.setComment("静音也非常好");
		list.add(tag);
		
	System.out.println(JSON.toJSONString(list));
	 
	 
	List<String> l=new ArrayList<String>();
	l.add("1111");
	l.add("2222");
	
	
	
	
	System.out.println(JSON.toJSONString(OBJECT_MAPPER.writeValueAsString(l)));

	}

}
