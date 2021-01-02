package com.java.plus.test.baidu.api.model;

import lombok.Data;

//@Data
public class Comment {
	
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
	private int commentid;
	private String content;

}
