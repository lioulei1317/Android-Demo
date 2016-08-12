package com.example.kaoshi03;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Gallery;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class Secend extends Activity {
	Gallery fruit_gallery;
	int[] image;
	RadioGroup group1;
	RadioButton rad1;
	RadioButton rad2;
	RadioButton rad3;
	RadioButton rad4;
	Handler mHandler;
	ListView listview;
	TestAdapter testadapter;
	ArrayAdapter<String> adapter;// listview的适配器
	ArrayList<String> aList;// listview的容器
	ActionBar actionBar;
	int cut = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main3);
		group1 = (RadioGroup) findViewById(R.id.group1);
		rad1 = (RadioButton) findViewById(R.id.rad1);
		rad2 = (RadioButton) findViewById(R.id.rad2);
		rad3 = (RadioButton) findViewById(R.id.rad3);
		rad4 = (RadioButton) findViewById(R.id.rad4);
		fruit_gallery = (Gallery) findViewById(R.id.fruit_gallery);
		image = new int[] { R.drawable.meinv1, R.drawable.meinv2,
				R.drawable.meinv3, R.drawable.meinv4 };
		 testadapter = new TestAdapter(this, image);
		fruit_gallery.setAdapter(testadapter);
		fruit_gallery.setOnItemSelectedListener(listener);
		group1.setOnCheckedChangeListener(change);
		actionBar = getActionBar();
		// 设置是否显示应用程序图标
		actionBar.setDisplayShowHomeEnabled(true);
		// 将应用程序图标设置为可点击的按钮
		// actionBar.setHomeButtonEnabled(true);
		// 将应用程序图标设置为可点击的按钮，并在图标上添加向左箭头
		actionBar.setDisplayHomeAsUpEnabled(true);
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if (msg.what == 0x123) {
					if (++cut < image.length) {
						fruit_gallery.setSelection(cut);
					} else {
						cut = 0;
						fruit_gallery.setSelection(cut);
					}
				}
			}
		};
		/*
		 * Timer用来执行一些简单的定时器任务 TimerTask 要执行的任务
		 * 创建一个Timer实例，通过提供的schedule（）方法将TimerTask加入到定时器Timer中
		 */
		// 延迟，从0秒开始运行，每个2秒run一次
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				mHandler.sendEmptyMessage(0x123);

			}
		}, 0, 2000);
		listview = (ListView) findViewById(R.id.listview001);
		adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, f());
		listview.setAdapter(adapter);

		// 控件注册上下文菜单
		registerForContextMenu(listview);

	}



	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// 创建上下文菜单的方法

		// 法一：直接用menu.add();
		menu.add(0, 1, 0, "复制");
		menu.add(0, 2, 0, "删除");
		menu.setHeaderTitle("选项：");// 选项标题
		menu.setHeaderIcon(R.drawable.ic_launcher);
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// 上下文菜单项的点击效果重写得方法
		switch (item.getItemId()) {
		case 1:
			Toast.makeText(Secend.this, "你点击了复制", Toast.LENGTH_SHORT).show();
			break;
		case 2:
			AdapterContextMenuInfo itemInfo2 = (AdapterContextMenuInfo) item
					.getMenuInfo();
			// item.getMenuInfo()得到当前菜单项对应item的info对象，这里是listview
			// 所以返回值是AdapterContextMenuInfo
			// 里面包括了当前listview里的属性，包括id,下标，内容
			aList.remove(itemInfo2.position);// 移除当前选中item的值
			// 移除过后不能忘记刷新adapter,否则会报错
			adapter.notifyDataSetChanged();
			break;

		default:
			break;

		}
		return super.onContextItemSelected(item);
	}
	public ArrayList<String> f() {
		// 往容器里放东西的方法
		 aList = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			aList.add("列表" + (i + 1));
		}
		return aList;

	}

	OnItemSelectedListener listener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1,
				int position, long arg3) {
			int id = R.id.rad1;
			switch (position) {
			case 0:
				id = R.id.rad1;
				break;
			case 1:
				id = R.id.rad2;
				break;
			case 2:
				id = R.id.rad3;
				break;
			case 3:
				id = R.id.rad4;
				break;
			}
			group1.check(id);

		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
	};

	OnCheckedChangeListener change = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			int position = 0;
			if (rad1.isChecked()) {
				position = 0;
				fruit_gallery.setSelection(position);
			} else if (rad2.isChecked()) {
				position = 1;
				fruit_gallery.setSelection(position);
			} else if (rad3.isChecked()) {
				position = 2;
				fruit_gallery.setSelection(position);
			} else if (rad4.isChecked()) {
				position = 3;
				fruit_gallery.setSelection(position);
			}
		}
	};

	@Override
	// 选项菜单的菜单项被单击后的回调方法
	public boolean onOptionsItemSelected(MenuItem mi) {
		if (mi.isCheckable()) {
			mi.setChecked(true);
		}
		// 判断单击的是哪个菜单项，并针对性的作出响应。
		switch (mi.getItemId()) {
		case android.R.id.home:
			// 创建启动FirstActivity的Intent
			Intent intent = new Intent(this, MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			// 启动intent对应的Activity
			startActivity(intent);
			break;
		}
		return true;
	}

}
