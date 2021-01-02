package com.java.plus.test.baidu.api.model;

import lombok.Data;

@Data
public class Item {

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
	public int getBegin_pos() {
		return begin_pos;
	}
	public void setBegin_pos(int begin_pos) {
		this.begin_pos = begin_pos;
	}
	public int getEnd_pos() {
		return end_pos;
	}
	public void setEnd_pos(int end_pos) {
		this.end_pos = end_pos;
	}
	public String get_abstract() {
		return _abstract;
	}
	public void set_abstract(String _abstract) {
		this._abstract = _abstract;
	}
	private String prop;//匹配上的属性词
	private String adj;//匹配上的描述词
	private int sentiment;//该情感搭配的极性（0表示消极，1表示中性，2表示积极）
	private int begin_pos;
	private int end_pos;
	private String _abstract;

}
