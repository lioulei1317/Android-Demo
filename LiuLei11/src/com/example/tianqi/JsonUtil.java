package com.example.tianqi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class JsonUtil {
	public Map<String, Object> jiexi(String Data) {
		Map<String, Object> map = new HashMap<String, Object>();
		Gson gson = new Gson();
		Weather weather = gson.fromJson(Data, Weather.class);
		List<WeatherInfo> list = weather.getResult();
		WeatherInfo weatherInfo = list.get(0);
		List<Info> list_info = weatherInfo.getFuture();
		map.put("weatherInfo", weatherInfo);
		map.put("list_info", list_info);
		return map;

	}
}
