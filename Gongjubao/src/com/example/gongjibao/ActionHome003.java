package com.example.gongjibao;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ActionHome003 extends Activity {
	private TextView txt;
	ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actionhome001);
		txt = (TextView) findViewById(R.id.actiontxt001);
		actionBar = getActionBar();
		// �����Ƿ���ʾӦ�ó���ͼ��
		actionBar.setDisplayShowHomeEnabled(true);
		// ��Ӧ�ó���ͼ������Ϊ�ɵ���İ�ť
		// actionBar.setHomeButtonEnabled(true);
		// ��Ӧ�ó���ͼ������Ϊ�ɵ���İ�ť������ͼ������������ͷ
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflator = new MenuInflater(this);
		// ״̬R.menu.context��Ӧ�Ĳ˵�������ӵ�menu��
		inflator.inflate(R.menu.action_home001, menu);
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
		case android.R.id.home:
			// ��������FirstActivity��Intent
			Intent intent = new Intent(this, ActionHome001.class);
			/**
			 * Task����һ����������Activity���൱�������������Ķ����� ��һ��������Activity����ᴦ�������棬
			 * �����ӵĶ�����Activity���������ˡ� ��Task��ȡ��������Activity�����Ǵ����ȡ����
			 * ���activity��task���ڣ��õ����,���������µ�Activity
			 * intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
			 * Ĭ�ϵ���ת����,��Activity�ŵ�һ���µ�Task��
			 * intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			 * ���Activity�Ѿ����е���Task���ٴ���ת�������������Activity
			 * intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			 * ��Ӷ����Flag�����activity��task���ڣ� ��Activity֮�ϵ�����Activity������
			 */
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			// ����intent��Ӧ��Activity
			startActivity(intent);
			break;
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
			Toast toast = Toast.makeText(ActionHome003.this, "����������ͨ�˵���",
					Toast.LENGTH_SHORT);
			toast.show();
			break;
		}
		return true;
	}
}
