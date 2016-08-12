package com.example.liulei11;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Zhucejiemian extends Activity {
	EditText yhm_, mm_;
	Button zc_btn;
	SQLiteDatabase db;
	String username;
	String password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zhucejiemian);
		yhm_ = (EditText) findViewById(R.id.yhm_EditText);
		mm_ = (EditText) findViewById(R.id.mm_EditText);
		zc_btn = (Button) findViewById(R.id.zc_btn);
		db = Zhucejiemian.this.openOrCreateDatabase("my.db3",
				Context.MODE_PRIVATE, null);
		zc_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				username = yhm_.getText().toString();
				password = mm_.getText().toString();
				try {
					insertData(db, username, password);
				} catch (SQLiteException e) {
					// TODO: handle exception
					db.execSQL("create table zhucexinxi(_id integer"
							+ " primary key autoincrement,"
							+ " username varchar(50),"
							+ " password varchar(255))");

					insertData(db, username, password);

				}
				if (username.equals("") || password.equals("")) {
					Toast.makeText(Zhucejiemian.this, "用户名或密码不能为空！",
							Toast.LENGTH_SHORT).show();
				} else {
					Intent intent = new Intent(Zhucejiemian.this,
							Denglujiemian.class);
					startActivity(intent);
				}

			}
		});
	}

	private void insertData(SQLiteDatabase db, String username, String password) {
		db.execSQL("insert into zhucexinxi values(null,?,?)", new String[] {
				username, password });

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (db != null && db.isOpen()) {
			db.close();
		}
	}
}
