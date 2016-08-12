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

	// �����Ĳ˵���ѡ��˵�������
	// ѡ��˵�ֻ����һ�Σ���һ��activity��ֻ��һ��ѡ��˵�
	// �����Ĳ˵���Ӧ����һ��view�ؼ�
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu003);
		listview = (ListView) findViewById(R.id.listview001);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(Menu003.this,
				android.R.layout.simple_list_item_1, f());
		listview.setAdapter(adapter);

		// �ؼ�ע�������Ĳ˵�
		registerForContextMenu(listview);
	}

	public ArrayList<String> f() {
		// ��������Ŷ����ķ���
		ArrayList<String> s = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			s.add("�б�" + (i + 1));
		}
		return s;

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// ���������Ĳ˵��ķ���
	
		// ��һ��ֱ����menu.add();
//		 menu.add(0,1,1,"����");
//		 menu.add(0,2,1,"ճ��");
//		 menu.add(0,3,1,"����");
//		 menu.add(0,4,1,"����");
//		 menu.setHeaderTitle("ѡ�");//ѡ�����
		 menu.setHeaderIcon(R.drawable.ic_launcher);
		// ��������xml�������ã���getMenuInflater().inflate(R.menu.menu002, menu);����
		getMenuInflater().inflate(R.menu.menu003, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// �����Ĳ˵���ĵ��Ч����д�÷���
		switch (item.getItemId()) {
		case R.id.action_settings:
			Toast.makeText(Menu003.this, "�����˸���", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_settings1:
			Toast.makeText(Menu003.this, "������ճ��", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_settings2:
			Toast.makeText(Menu003.this, "�����˼���", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_settings3:
			Toast.makeText(Menu003.this, "����������", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;

		}
		return super.onContextItemSelected(item);
	}
	

}
