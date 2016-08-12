package com.example.t20160429a_actionbar02;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ActionBar002 extends Activity {
	private TextView txt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actionbar002);
		txt = (TextView) findViewById(R.id.txt);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflator = new MenuInflater(this);
		// ״̬R.menu.context��Ӧ�Ĳ˵�������ӵ�menu��
		inflator.inflate(R.menu.action_bar002, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	// ѡ��˵��Ĳ˵��������Ļص�����
	public boolean onOptionsItemSelected(MenuItem mi) {
		if (mi.isCheckable()) {
			mi.setChecked(true);
		}
		// �жϵ��������ĸ��˵��������Ե�������Ӧ��
		switch (mi.getItemId()) {
		case R.id.font_10:
			txt.setTextSize(10 * 2);
			break;
		case R.id.font_12:
			txt.setTextSize(12 * 2);
			break;
		case R.id.font_14:
			txt.setTextSize(14 * 2);
			break;
		case R.id.font_16:
			txt.setTextSize(16 * 2);
			break;
		case R.id.font_18:
			txt.setTextSize(18 * 2);
			break;
		case R.id.red_font:
			txt.setTextColor(Color.RED);
			mi.setChecked(true);
			break;
		case R.id.green_font:
			txt.setTextColor(Color.GREEN);
			mi.setChecked(true);
			break;
		case R.id.blue_font:
			txt.setTextColor(Color.BLUE);
			mi.setChecked(true);
			break;
		case R.id.plain_item:
			Toast toast = Toast.makeText(ActionBar002.this, "����������ͨ�˵���",
					Toast.LENGTH_SHORT);
			toast.show();
			break;
		}
		return true;
	}

}
