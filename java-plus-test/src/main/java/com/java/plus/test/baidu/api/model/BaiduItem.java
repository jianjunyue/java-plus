package com.java.plus.test.baidu.api.model;

import java.util.List;

import lombok.Data;

@Data
public class BaiduItem {
 
	public long getLog_id() {
		return log_id;
	}
	public void setLog_id(long log_id) {
		this.log_id = log_id;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public String getBaseJson() {
		return baseJson;
	}
	public void setBaseJson(String baseJson) {
		this.baseJson = baseJson;
	}
	private long log_id;
	private List<Item> items;
	private String baseJson;
 
}
