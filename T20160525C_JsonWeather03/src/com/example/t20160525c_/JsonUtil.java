package com.example.t20160525c_;

import java.util.List;

import com.google.gson.Gson;
	
public class JsonUtil {
	List<Info> l;
	String s="";
	public void jianxi(String data) {
		Gson gson = new Gson();
		// 外层的封装类info
		Weather weather = gson.fromJson(data, Weather.class);
		// 取容器result
		List<WeatherInfo> list = weather.getResult();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				l=list.get(0).getFuture();
				MainActivity.tianqiqk.setText(list.get(0).getWeather());
				MainActivity.riqi.setText(list.get(0).getDate());
				MainActivity.wendu.setText(list.get(0).getTemperature());
				MainActivity.shidu.setText(list.get(0).getHumidity());
				MainActivity.chuanyizs.setText(list.get(0).getDressingIndex());
				MainActivity.yundongzs.setText(list.get(0).getExerciseIndex());
//				WeatherInfo wi = list.get(i);
//				List<Info> list2 = wi.getFuture();
//				// 把值添加到容器里
//				list3 = new ArrayList<String>();
//				for (int j = 0; j < list3.size(); j++) {
//					list3.add(wi.getProvince());// 省
//					list3.add(wi.getCity());// 城市
//					list3.add(wi.getWeather());// 天气
//					list3.add(wi.getDate());// 日期
//					list3.add(wi.getTemperature());// 温度
//					list3.add(wi.getHumidity());// 湿度
//					list3.add(wi.getWind());// 风向
//					list3.add(wi.getDressingIndex());// 穿衣指数
//					list3.add(wi.getExerciseIndex());// 运动指数
//
//				}
//				System.out.println("城市：" + wi.getCity() + "---天气："
//						+ wi.getWeather());
			}
			for (int i = 0; i < l.size(); i++) {
				s+=l.get(i).getDate();
				s+=l.get(i).getWeek();
				s+=l.get(i).getNight();
				s += "\r\n";
			}
			// 一级一级的往下取
			// System.out.println(list.get(0).getCity());
			MainActivity.weilai.setText(s);
		}
//		return list3;
	}

	// public String jianxi(String data) {
	// String s = "";
	// Gson gson = new Gson();
	// // 外层的封装类info
	// Weather weather = gson.fromJson(data, Weather.class);
	// // 取容器result
	// List<WeatherInfo> list = weather.getResult();
	// if (list != null) {
	// for (int i = 0; i < list.size(); i++) {
	// WeatherInfo wi = list.get(i);
	// List<Info> list2 = wi.getFuture();
	// s = wi.getCity();
	// System.out.println("城市：" + wi.getCity() + "---天气："
	// + wi.getWeather());
	// }
	// // 一级一级的往下取
	// // System.out.println(list.get(0).getCity());
	// }
	// return s;
	// }
}
