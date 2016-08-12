package com.example.t20160427b_menu002;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Menu003 extends Activity {
	ListView listview;

	// 上下文菜单与选项菜单的区别：
	// 选项菜单只启动一次，在一个activity里只有一个选项菜单
	// 上下文菜单对应的是一个view控件
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu003);
		listview = (ListView) findViewById(R.id.listview001);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(Menu003.this,
				android.R.layout.simple_list_item_1, f());
		listview.setAdapter(adapter);

		// 控件注册上下文菜单
		registerForContextMenu(listview);
	}

	public ArrayList<String> f() {
		// 往容器里放东西的方法
		ArrayList<String> s = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			s.add("列表" + (i + 1));
		}
		return s;

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// 创建上下文菜单的方法
	
		// 法一：直接用menu.add();
//		 menu.add(0,1,1,"复制");
//		 menu.add(0,2,1,"粘贴");
//		 menu.add(0,3,1,"剪切");
//		 menu.add(0,4,1,"隐藏");
//		 menu.setHeaderTitle("选项：");//选项标题
		 menu.setHeaderIcon(R.drawable.ic_launcher);
		// 法二：用xml布局设置，用getMenuInflater().inflate(R.menu.menu002, menu);加载
		getMenuInflater().inflate(R.menu.menu003, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// 上下文菜单项的点击效果重写得方法
		switch (item.getItemId()) {
		case R.id.action_settings:
			Toast.makeText(Menu003.this, "你点击了复制", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_settings1:
			Toast.makeText(Menu003.this, "你点击了粘贴", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_settings2:
			Toast.makeText(Menu003.this, "你点击了剪切", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_settings3:
			Toast.makeText(Menu003.this, "你点击了隐藏", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;

		}
		return super.onContextItemSelected(item);
	}
	

}
