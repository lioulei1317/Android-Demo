package com.example.t20160525b_gson01;

import com.google.gson.Gson;

public class Gson03_03 {
	public void jiexi(String DATA) {
		// ����gson�Ķ�����������json���ݵķ�װ��
		// ��Ҫ��libs�ļ���������Gson��jar��
		Gson gson = new Gson();
		// ����fromJson������json���ݽ�����Gson02_02��Ķ���
		Gson02_02 person = gson.fromJson(DATA, Gson02_02.class);
		// ��Ҫʲô���ݾ���ʲô����������
		System.out.println("name------->" + person.getName());
		System.out.println("age------->" + person.getAge());

	}
}
