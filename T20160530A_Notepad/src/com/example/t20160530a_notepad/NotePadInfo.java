package com.example.t20160530a_notepad;

import java.io.Serializable;

public class NotePadInfo implements Serializable{
	private static final long serialVersionUID=1459597418665194830L;
	private int id;//笔记id
	private String title;//标题
	private String content;//内容
	private String date;//最近操作日期
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
