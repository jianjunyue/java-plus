package com.java.plus.test.baidu.api.model;

public class CommentTag { 
 
	public int getCommentid() {
		return commentid;
	}
	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTagJson() {
		return tagJson;
	}
	public void setTagJson(String tagJson) {
		this.tagJson = tagJson;
	}
	public String getBaiduBaseJson() {
		return baiduBaseJson;
	}
	public void setBaiduBaseJson(String baiduBaseJson) {
		this.baiduBaseJson = baiduBaseJson;
	}
	private int commentid; 
	private String content;
	private String tagJson;
	private String baiduBaseJson;
}
