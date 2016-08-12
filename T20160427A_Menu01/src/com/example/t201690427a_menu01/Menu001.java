package com.example.t201690427a_menu01;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Menu001 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu0001);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.menu001, menu);
		
		 menu.add(1, 1, 0, "复制");
		 menu.add(1, 2, 0, "删除");
		 menu.add(1, 3, 0, "剪切");
		 menu.add(1, 4, 0, "粘贴");
		//选项菜单
		// menu.add();四个参数的意思
		// 第一个参数是分组的id
		// 第二个参数是菜单项的id
		// 第三个参数是排序，数字越小排序越前，默认为0则按添加的先后顺序
		// 第四个参数是菜单项的名字

		// 添加菜单项的两种方式：
		// 1、直接在xml布局中添加，用getMenuInflater().inflate(R.menu.menu001, menu);
		// 2、在代码中用menu.add();的方式添加
		return true;
	}
	
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// 菜单项的点击效果的监听
//		switch (item.getItemId()) {
//		case R.id.action_settings:
//			Toast.makeText(Menu001.this, "你点击了复制", Toast.LENGTH_SHORT).show();
//			Intent intent = new Intent(Menu001.this, Menu002.class);
//			item.setIntent(intent);//跳转
//			break;
//		case R.id.action_settings1:
//			Toast.makeText(Menu001.this, "你点击了删除", Toast.LENGTH_SHORT).show();
//			break;
//		case R.id.action_settings2:
//			Toast.makeText(Menu001.this, "你点击了粘贴", Toast.LENGTH_SHORT).show();
//			break;
//		case R.id.action_settings3:
//			Toast.makeText(Menu001.this, "你点击了剪切", Toast.LENGTH_SHORT).show();
//			break;
//
//		default:
//			break;
//		}
//		return super.onOptionsItemSelected(item);
//	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// 菜单项的点击效果的监听
		switch (item.getItemId()) {
		case 1:
			Toast.makeText(Menu001.this, "你点击了复制", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(Menu001.this, Menu002.class);
			item.setIntent(intent);//跳转
			break;
		case 2:
			Toast.makeText(Menu001.this, "你点击了删除", Toast.LENGTH_SHORT).show();
			break;
		case 3:
			Toast.makeText(Menu001.this, "你点击了粘贴", Toast.LENGTH_SHORT).show();
			break;
		case 4:
			Toast.makeText(Menu001.this, "你点击了剪切", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
