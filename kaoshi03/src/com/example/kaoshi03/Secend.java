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
	ArrayAdapter<String> adapter;// listview��������
	ArrayList<String> aList;// listview������
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
		// �����Ƿ���ʾӦ�ó���ͼ��
		actionBar.setDisplayShowHomeEnabled(true);
		// ��Ӧ�ó���ͼ������Ϊ�ɵ���İ�ť
		// actionBar.setHomeButtonEnabled(true);
		// ��Ӧ�ó���ͼ������Ϊ�ɵ���İ�ť������ͼ������������ͷ
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
		 * Timer����ִ��һЩ�򵥵Ķ�ʱ������ TimerTask Ҫִ�е�����
		 * ����һ��Timerʵ����ͨ���ṩ��schedule����������TimerTask���뵽��ʱ��Timer��
		 */
		// �ӳ٣���0�뿪ʼ���У�ÿ��2��runһ��
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				mHandler.sendEmptyMessage(0x123);

			}
		}, 0, 2000);
		listview = (ListView) findViewById(R.id.listview001);
		adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, f());
		listview.setAdapter(adapter);

		// �ؼ�ע�������Ĳ˵�
		registerForContextMenu(listview);

	}



	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// ���������Ĳ˵��ķ���

		// ��һ��ֱ����menu.add();
		menu.add(0, 1, 0, "����");
		menu.add(0, 2, 0, "ɾ��");
		menu.setHeaderTitle("ѡ�");// ѡ�����
		menu.setHeaderIcon(R.drawable.ic_launcher);
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// �����Ĳ˵���ĵ��Ч����д�÷���
		switch (item.getItemId()) {
		case 1:
			Toast.makeText(Secend.this, "�����˸���", Toast.LENGTH_SHORT).show();
			break;
		case 2:
			AdapterContextMenuInfo itemInfo2 = (AdapterContextMenuInfo) item
					.getMenuInfo();
			// item.getMenuInfo()�õ���ǰ�˵����Ӧitem��info����������listview
			// ���Է���ֵ��AdapterContextMenuInfo
			// ��������˵�ǰlistview������ԣ�����id,�±꣬����
			aList.remove(itemInfo2.position);// �Ƴ���ǰѡ��item��ֵ
			// �Ƴ�����������ˢ��adapter,����ᱨ��
			adapter.notifyDataSetChanged();
			break;

		default:
			break;

		}
		return super.onContextItemSelected(item);
	}
	public ArrayList<String> f() {
		// ��������Ŷ����ķ���
		 aList = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			aList.add("�б�" + (i + 1));
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
	// ѡ��˵��Ĳ˵��������Ļص�����
	public boolean onOptionsItemSelected(MenuItem mi) {
		if (mi.isCheckable()) {
			mi.setChecked(true);
		}
		// �жϵ��������ĸ��˵��������Ե�������Ӧ��
		switch (mi.getItemId()) {
		case android.R.id.home:
			// ��������FirstActivity��Intent
			Intent intent = new Intent(this, MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			// ����intent��Ӧ��Activity
			startActivity(intent);
			break;
		}
		return true;
	}

}
