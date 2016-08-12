package com.example.t20160525a_json01;

import java.io.StringReader;

import android.util.JsonReader;

public class Json02_Utils {
	// jsonԭ������
	public void parseJson(String jsonData) {
		try {
			// �����Ҫ����JSON���ݣ���ҪҪ����һ��JsonReader����
			// JsonReader��������������ν����ӷ���˷�������json����
			// StringReader���ַ�����ȡ�ַ�
			JsonReader reader = new JsonReader(new StringReader(jsonData));
			// ��ʼ��������
			reader.beginArray();
			// ѭ���ж���û����һ��Json����
			while (reader.hasNext()) {
				// ��ʼ����Json����
				reader.beginObject();
				// ѭ���ж�Json����������û����һ����ֵ��
				while (reader.hasNext()) {
					String tagName = reader.nextName();
					if (tagName.equals("name")) {
						// name����Ӧ��ֵ
						System.out.println("name--->" + reader.nextString());
					} else if (tagName.equals("age")) {
						// age����Ӧ��ֵ
						System.out.println("age--->" + reader.nextInt());
					}
				}
				// Json����Ľ�β
				reader.endObject();
			}
			// ��������Ľ�β
			reader.endArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}