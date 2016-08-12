package com.example.gongjibao;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class Menu005 extends Activity {

	// 定义字体大小菜单项的标识
	final int FONT_10 = 0x111;
	final int FONT_12 = 0x112;
	final int FONT_14 = 0x113;
	final int FONT_16 = 0x114;
	final int FONT_18 = 0x115;
	// 定义普通菜单项的标识
	final int PLAIN_ITEM = 0x11b;
	// 定义字体颜色菜单项的标识
	final int FONT_RED = 0x116;
	final int FONT_BLUE = 0x117;
	final int FONT_GREEN = 0x118;
	private EditText edit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu005);
		edit = (EditText) findViewById(R.id.menuet001);
	}

	// 当用户单击MENU键时触发该方法
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.menu005, menu);
		return super.onCreateOptionsMenu(menu);
	}

	// 选项菜单的菜单项单击后会掉的方法
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// 判断单击的是哪个菜单项，并针对性的做出相应。
		switch (item.getItemId()) {
		case R.id.FONT_10:
			edit.setTextSize(10 * 2);
			break;
		case R.id.FONT_12:
			edit.setTextSize(12 * 2);
			break;
		case R.id.FONT_14:
			edit.setTextSize(14 * 2);
			break;
		case R.id.FONT_16:
			edit.setTextSize(16 * 2);
			break;
		case R.id.FONT_18:
			edit.setTextSize(18 * 2);
			break;
		case R.id.FONT_RED:
			edit.setTextColor(Color.RED);
			break;
		case R.id.FONT_GREEN:
			edit.setTextColor(Color.GREEN);
			break;
		case R.id.FONT_BLUE:
			edit.setTextColor(Color.BLUE);
			break;
		case R.id.PLAIN_ITEM:
			Toast.makeText(Menu005.this, "普通菜单项", Toast.LENGTH_SHORT)
					.show();

			break;

		}
		return super.onOptionsItemSelected(item);
	}

}
