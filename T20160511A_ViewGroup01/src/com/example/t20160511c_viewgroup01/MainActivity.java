package com.example.t20160511c_viewgroup01;

import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// LayoutInflater.from(MainActivity.this).inflate(resource, root);
		// ������������������ǣ��Ƿ��ѡȡ����ͼ���뵽root�С�
		// false����˼���ǲ���ӵ�root�У�������Ҫ�����ֶ���ӡ�

		setContentView(R.layout.activity_main);
		ViewGroup v = (ViewGroup) findViewById(R.id.framelayot001);
		// ���ǽ�R.layout.activity_main2��ӵ�v���У����ص���v������ʾ����v
		// LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main2,
		// v);
		// ���ǲ���R.layout.activity_main2��ӵ�v���У����ص���v����ʾ����v
		// LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main2,
		// v, false);
		// ���ǲ���R.layout.activity_main2��ӵ�v���У����ص���v����ʾv
		// �ֶ��Ľ�vv��ӵ�v�����ص���v������ʾ����v��vv��R.layout.activity_main2
		View vv = LayoutInflater.from(MainActivity.this).inflate(
				R.layout.activity_main2, v, false);
		v.addView(vv);
	}

}
