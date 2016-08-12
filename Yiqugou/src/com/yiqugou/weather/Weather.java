package com.yiqugou.weather;

import java.util.List;

public class Weather {
	private String msg;// 返回说明
	private List<WeatherInfo> result;// 返回结果集
	private String retCode;// 返回码

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<WeatherInfo> getResult() {
		return result;
	}

	public void setResult(List<WeatherInfo> result) {
		this.result = result;
	}

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

}
