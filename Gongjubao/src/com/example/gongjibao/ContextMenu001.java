package com.example.gongjibao;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ContextMenu001 extends Activity {

	// 为每个菜单定义一个标识
	final int MENU1 = 0x111;
	final int MENU2 = 0x112;
	final int MENU3 = 0x113;
	private TextView txt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contextmenu001);
		txt = (TextView) findViewById(R.id.txt);
		// 为文本框注册上下文菜单
		registerForContextMenu(txt);
	}

	// 创建上下文菜单时触发该方法
	@Override
	public void onCreateContextMenu(ContextMenu menu, View source,
			ContextMenu.ContextMenuInfo menuInfo) {
		// add()方法的四个参数，依次是
		// 组别：如果不分组的话就写Menu.NONE,
		// id,这个很重要，Android根据这个id来确定不同的菜单项
		// 顺序，哪个菜单项在前面由这个参数决定，越小排序越前
		// 文本名称，菜单项的显示文本
		menu.add(0, MENU1, 0, "红色");
		menu.add(0, MENU2, 0, "绿色");
		menu.add(0, MENU3, 0, "蓝色");
		
		
		
		// 将三个菜单项设为单选菜单项
		// 第一个参数为groupid
		// 第二个参数checkable就是是否可以选择。根据
		//第三个参数exclusive设置为true就是单选，false就是多选
		menu.setGroupCheckable(0, true, true);
		
		
		// 设置上下文菜单的标题、图标
		menu.setHeaderIcon(R.drawable.ic_launcher);
		menu.setHeaderTitle("选择背景色");
	}

	// 上下菜单的菜单项被单击时触发该方法。
	@Override
	public boolean onContextItemSelected(MenuItem mi) {
		switch (mi.getItemId()) {
		case MENU1:
			mi.setChecked(true);
			txt.setBackgroundColor(Color.RED);
			break;
		case MENU2:
			mi.setChecked(true);
			txt.setBackgroundColor(Color.GREEN);
			break;
		case MENU3:
			mi.setChecked(true);
			txt.setBackgroundColor(Color.BLUE);
			break;
		}
		return true;
	}
}
