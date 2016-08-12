package com.example.t20160420e;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	Button submit,bn2,bn3;
	ListView list;
	List<Info> list1;
	Info info;
	ListViewAdapter adapter;
	int number=0;
	int[] tupian = { R.drawable.qq, R.drawable.tuian1, R.drawable.tupain7,
			R.drawable.tupian10, R.drawable.tupian2, R.drawable.tupian3,
			R.drawable.tupian4, R.drawable.tupian5, R.drawable.tupian6,
			R.drawable.tupian8, R.drawable.tupian9 };


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// list = (ListView) findViewById(R.id.listViewId);
		list = (ListView) findViewById(R.id.listViewId);
		submit = (Button) findViewById(R.id.buttonId);
		bn2=(Button) findViewById(R.id.buttonId2);
		bn3=(Button) findViewById(R.id.buttonId3);
		submit.setOnClickListener(click);
		bn2.setOnClickListener(click2);
		bn3.setOnClickListener(click3);
		adapter = new ListViewAdapter(this, list1);
		// 当listview里面没有数据的时候显示view
		list.setEmptyView(findViewById(R.id.tv_emt));
		list.setAdapter(adapter);
		// 对listView绑定点击事件
		list.setOnItemClickListener(onItemClick);
	}

	OnClickListener click = new OnClickListener() {

		@Override
		public void onClick(View v) {
			data(v);
			adapter.addAllInfo(list1);
			number++;
		
		}
	};
	OnClickListener click2 = new OnClickListener() {

		@Override
		public void onClick(View v) {
			data(v);
			adapter.addAllInfo(list1);
			number++;
		
		}
	};
	OnClickListener click3 = new OnClickListener() {

		@Override
		public void onClick(View v) {
			data(v);
			adapter.addAllInfo(list1);
			number++;
		
		}
	};

	OnItemClickListener onItemClick = new OnItemClickListener() {
		/*
		 * AdapterView 当前的AdapterView对象，你这里就是ListView View
		 * AdapterView所绑定的Adapter的getView方法返回的View int 当前被点击的条目的索引号 long
		 * AdapterView所绑定的Adapter的getItemId返回的值
		 */
		@Override
		public void onItemClick(AdapterView<?> parent, View arg1, int position,
				long itemID) {
			info = (Info) parent.getItemAtPosition(position);
			Intent intent = new Intent();
			if(position%2==0){
			intent.setClass(MainActivity.this, Listlayout_Activity.class);
			}else{
				intent.setClass(MainActivity.this, Listlayout_Activity02.class);
			}
			intent.putExtra("info", info);
			startActivity(intent);

		}
	};

	private void data(View v) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String time = sdf.format(new Date(System.currentTimeMillis()));
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy年MM月dd日 HH:mm:ss ");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		list1 = new ArrayList<Info>();
		for (int i = 0; i < 1; i++) {
			info = new Info();
			info.setImg(tupian[(int) (Math.random()* 11)]);
			info.setTime(str);
			switch (v.getId()) {
			case R.id.buttonId:
				info.setTitle("用户A");
				break;
			case R.id.buttonId2:
				info.setTitle("用户B");
				break;
			case R.id.buttonId3:
				info.setTitle("用户C");
				break;
			}
			info.setContext("我拒绝了第" + (number + 1) + "位女神!");
			info.setCount((int) (Math.random() * 100000000) + 1);
			list1.add(info);
		}
	}

}
