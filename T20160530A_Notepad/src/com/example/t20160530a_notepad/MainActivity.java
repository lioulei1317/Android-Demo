package com.example.t20160530a_notepad;

import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	ListView lv;
	Button btn_add;

	NotePadAdapter adapter;

	static final int MENU_UPDATE = 0;
	static final int MENU_DELETE = 1;

	Context context;
	DBHelper helper;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		helper = new DBHelper(this);
		db = helper.getReadableDatabase();
		lv = (ListView) findViewById(R.id.list);
		btn_add = (Button) findViewById(R.id.btn_add);
		btn_add.setOnClickListener(click);
		registerForContextMenu(lv);// 为listview 注册上下文菜单
		lv.setOnItemClickListener(listener);
		lv.setEmptyView(findViewById(R.id.empty));
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		List<NotePadInfo> list = DBOperator.queryALLNoteInfo(db);
		adapter = new NotePadAdapter(this, list);
		lv.setAdapter(adapter);
	}

	OnItemClickListener listener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> adapterview, View view,
				int position, long arg3) {
			Intent intent = new Intent(MainActivity.this,
					NotePadInfoActivity.class);
			NotePadInfo info = (NotePadInfo) adapterview
					.getItemAtPosition(position);
			intent.putExtra("info", info);
			intent.putExtra("flag", 0);
			startActivity(intent);
		}
	};
	OnClickListener click = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(context, NotePadInfoActivity.class);
			intent.putExtra("flag", 1);
			startActivity(intent);
		}
	};

	// 此方法为创建菜单方法
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, MENU_UPDATE, 0, "修改");
		menu.add(0, MENU_DELETE, 0, "删除");
	}

	// 上下文菜单的监听器
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		int position = info.position;
		switch (item.getItemId()) {
		case MENU_DELETE:
			NotePadInfo _info1 = (NotePadInfo) adapter.getItem(position);
			adapter.deleteInfo(position);
			DBOperator.deleteNotepadInfo(db, _info1);
			break;
		case MENU_UPDATE:
			Intent intent = new Intent(context, NotePadInfoActivity.class);
			intent.putExtra("flag", 2);
			NotePadInfo _info = (NotePadInfo) adapter.getItem(position);
			intent.putExtra("info", _info);
			startActivity(intent);
			break;

		default:
			break;
		}
		return super.onContextItemSelected(item);
	}
}
