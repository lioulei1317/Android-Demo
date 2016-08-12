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
		// 所以这个参数的作用是，是否把选取的视图加入到root中。
		// false的意思就是不添加到root中，可能需要我们手动添加。

		setContentView(R.layout.activity_main);
		ViewGroup v = (ViewGroup) findViewById(R.id.framelayot001);
		// ①是将R.layout.activity_main2添加到v当中，返回的是v对象，显示的是v
		// LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main2,
		// v);
		// ②是不将R.layout.activity_main2添加到v当中，返回的是v，显示的是v
		// LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main2,
		// v, false);
		// ③是不将R.layout.activity_main2添加到v当中，返回的是v，显示v
		// 手动的将vv添加到v，返回的是v对象，显示的是v，vv是R.layout.activity_main2
		View vv = LayoutInflater.from(MainActivity.this).inflate(
				R.layout.activity_main2, v, false);
		v.addView(vv);
	}

}
