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
		
		 menu.add(1, 1, 0, "����");
		 menu.add(1, 2, 0, "ɾ��");
		 menu.add(1, 3, 0, "����");
		 menu.add(1, 4, 0, "ճ��");
		//ѡ��˵�
		// menu.add();�ĸ���������˼
		// ��һ�������Ƿ����id
		// �ڶ��������ǲ˵����id
		// ��������������������ԽС����Խǰ��Ĭ��Ϊ0����ӵ��Ⱥ�˳��
		// ���ĸ������ǲ˵��������

		// ��Ӳ˵�������ַ�ʽ��
		// 1��ֱ����xml��������ӣ���getMenuInflater().inflate(R.menu.menu001, menu);
		// 2���ڴ�������menu.add();�ķ�ʽ���
		return true;
	}
	
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// �˵���ĵ��Ч���ļ���
//		switch (item.getItemId()) {
//		case R.id.action_settings:
//			Toast.makeText(Menu001.this, "�����˸���", Toast.LENGTH_SHORT).show();
//			Intent intent = new Intent(Menu001.this, Menu002.class);
//			item.setIntent(intent);//��ת
//			break;
//		case R.id.action_settings1:
//			Toast.makeText(Menu001.this, "������ɾ��", Toast.LENGTH_SHORT).show();
//			break;
//		case R.id.action_settings2:
//			Toast.makeText(Menu001.this, "������ճ��", Toast.LENGTH_SHORT).show();
//			break;
//		case R.id.action_settings3:
//			Toast.makeText(Menu001.this, "�����˼���", Toast.LENGTH_SHORT).show();
//			break;
//
//		default:
//			break;
//		}
//		return super.onOptionsItemSelected(item);
//	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// �˵���ĵ��Ч���ļ���
		switch (item.getItemId()) {
		case 1:
			Toast.makeText(Menu001.this, "�����˸���", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(Menu001.this, Menu002.class);
			item.setIntent(intent);//��ת
			break;
		case 2:
			Toast.makeText(Menu001.this, "������ɾ��", Toast.LENGTH_SHORT).show();
			break;
		case 3:
			Toast.makeText(Menu001.this, "������ճ��", Toast.LENGTH_SHORT).show();
			break;
		case 4:
			Toast.makeText(Menu001.this, "�����˼���", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
