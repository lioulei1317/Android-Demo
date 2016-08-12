package com.example.t20160420e;

import java.io.Serializable;
//多个数据需要装入容器，可以改变里面的值
//序列化:允许将实现Serializable接口的对象转换为字节序列，这些字节序列可以完全存储以备重新生成原来的对象
public class Info implements Serializable {
	int img;
	String title;
	String time;
	String context;
	int Count;

	public int getImg() {
		return img;
	}

	public void setImg(int img) {
		this.img = img;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public int getCount() {
		return Count;
	}

	public void setCount(int count) {
		Count = count;
	}

}
