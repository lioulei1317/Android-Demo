package com.example.t20160530a_notepad;

import java.text.SimpleDateFormat;
import java.util.Date;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class NotePadInfoActivity extends Activity {
	Button btn_update;// 更新按钮
	Button btn_return;// 返回按钮
	EditText et_title;// 标题输入框
	EditText et_content;// 内容输入框
	NotePadInfo info;
	DBHelper helper;
	SQLiteDatabase db;
	SimpleDateFormat format;
	/**
	 * flag = 0 详情 flag = 1 添加 flag = 2 修改
	 */
	int flag = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.noteinfo_layout);
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		helper = new DBHelper(this);
		//使用getReadableDatabase()获取一个操作数据库的实例
		db = helper.getReadableDatabase();
		Intent intent = getIntent();
		if (intent != null) {
			flag = intent.getIntExtra("flag", 0);
			info = (NotePadInfo) intent.getSerializableExtra("info");

			Log.i("result", "flag == " + flag);
		}
		initView();
	}

	private void initView() {
		btn_return = (Button) findViewById(R.id.btn_return);
		btn_update = (Button) findViewById(R.id.btn_submit);
		et_title = (EditText) findViewById(R.id.et_title);
		et_content = (EditText) findViewById(R.id.et_content);
		btn_return.setOnClickListener(click);
		btn_update.setOnClickListener(click);
		if (info != null) {
			et_title.setText(info.getTitle());
			et_content.setText(info.getContent());
			if (flag == 0) {
				et_content.setEnabled(false);
				et_title.setEnabled(false);
			}
		}
	}

	OnClickListener click = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Log.i("result", "flag == " + flag);
			switch (v.getId()) {
			case R.id.btn_return:
				finish();
				break;
			case R.id.btn_submit:
				if (flag == 0) {
					// 进入修改模式
					flag = 2;
					et_content.setEnabled(true);
					et_title.setEnabled(true);
				} else if (flag == 1) {
					// 入库
					info = new NotePadInfo();
					buildInfo(info);
					DBOperator.addNotepadInfo(db, info);
					finish();
				} else {
					// 更新
					buildInfo(info);
					DBOperator.updateNotepadInfo(db, info);
					finish();
				}
				break;

			}
		}
	};

	private void buildInfo(NotePadInfo info) {
		info.setTitle(et_title.getText().toString());
		info.setContent(et_content.getText().toString());
		Date date = new Date();
		String str = format.format(date);
		info.setDate(str);
	}

}
