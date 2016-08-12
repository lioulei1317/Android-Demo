package com.example.t20160525a_json01;

import java.io.StringReader;

import android.util.JsonReader;

public class Json02_Utils {
	// json原生解析
	public void parseJson(String jsonData) {
		try {
			// 如果需要解析JSON数据，首要要生成一个JsonReader对象
			// JsonReader对象用来设置如何解析从服务端发回来的json数据
			// StringReader从字符串读取字符
			JsonReader reader = new JsonReader(new StringReader(jsonData));
			// 开始解析数组
			reader.beginArray();
			// 循环判断有没有下一个Json对象
			while (reader.hasNext()) {
				// 开始解析Json对象
				reader.beginObject();
				// 循环判断Json对象里面有没有下一个键值对
				while (reader.hasNext()) {
					String tagName = reader.nextName();
					if (tagName.equals("name")) {
						// name键对应的值
						System.out.println("name--->" + reader.nextString());
					} else if (tagName.equals("age")) {
						// age键对应的值
						System.out.println("age--->" + reader.nextInt());
					}
				}
				// Json对象的结尾
				reader.endObject();
			}
			// 数组解析的结尾
			reader.endArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}