package com.example.t20160525c_;

import java.util.List;

import com.google.gson.Gson;
	
public class JsonUtil {
	List<Info> l;
	String s="";
	public void jianxi(String data) {
		Gson gson = new Gson();
		// ���ķ�װ��info
		Weather weather = gson.fromJson(data, Weather.class);
		// ȡ����result
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
//				// ��ֵ��ӵ�������
//				list3 = new ArrayList<String>();
//				for (int j = 0; j < list3.size(); j++) {
//					list3.add(wi.getProvince());// ʡ
//					list3.add(wi.getCity());// ����
//					list3.add(wi.getWeather());// ����
//					list3.add(wi.getDate());// ����
//					list3.add(wi.getTemperature());// �¶�
//					list3.add(wi.getHumidity());// ʪ��
//					list3.add(wi.getWind());// ����
//					list3.add(wi.getDressingIndex());// ����ָ��
//					list3.add(wi.getExerciseIndex());// �˶�ָ��
//
//				}
//				System.out.println("���У�" + wi.getCity() + "---������"
//						+ wi.getWeather());
			}
			for (int i = 0; i < l.size(); i++) {
				s+=l.get(i).getDate();
				s+=l.get(i).getWeek();
				s+=l.get(i).getNight();
				s += "\r\n";
			}
			// һ��һ��������ȡ
			// System.out.println(list.get(0).getCity());
			MainActivity.weilai.setText(s);
		}
//		return list3;
	}

	// public String jianxi(String data) {
	// String s = "";
	// Gson gson = new Gson();
	// // ���ķ�װ��info
	// Weather weather = gson.fromJson(data, Weather.class);
	// // ȡ����result
	// List<WeatherInfo> list = weather.getResult();
	// if (list != null) {
	// for (int i = 0; i < list.size(); i++) {
	// WeatherInfo wi = list.get(i);
	// List<Info> list2 = wi.getFuture();
	// s = wi.getCity();
	// System.out.println("���У�" + wi.getCity() + "---������"
	// + wi.getWeather());
	// }
	// // һ��һ��������ȡ
	// // System.out.println(list.get(0).getCity());
	// }
	// return s;
	// }
}
