package com.java.plus.test.baidu.api.model;

import lombok.Data;

@Data
public class AITag {

	public String getProp() {
		return prop;
	}
	public void setProp(String prop) {
		this.prop = prop;
	}
	public String getAdj() {
		return adj;
	}
	public void setAdj(String adj) {
		this.adj = adj;
	}
	public int getSentiment() {
		return sentiment;
	}
	public void setSentiment(int sentiment) {
		this.sentiment = sentiment;
	}
	private String prop;
	private String adj;
	private int sentiment;

}
