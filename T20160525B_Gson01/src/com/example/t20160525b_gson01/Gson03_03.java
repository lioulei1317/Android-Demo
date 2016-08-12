package com.example.t20160525b_gson01;

import com.google.gson.Gson;

public class Gson03_03 {
	public void jiexi(String DATA) {
		// 创建gson的对象，用来解析json数据的封装类
		// 需要在libs文件夹下引入Gson的jar包
		Gson gson = new Gson();
		// 调用fromJson方法将json数据解析成Gson02_02类的对象
		Gson02_02 person = gson.fromJson(DATA, Gson02_02.class);
		// 需要什么数据就用什么对象来调用
		System.out.println("name------->" + person.getName());
		System.out.println("age------->" + person.getAge());

	}
}
