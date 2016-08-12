package com.example.liulei11;

import com.example.tianqi.MainActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Denglujiemian extends Activity {
	EditText yhm, mm;
	Button dl, zc;
	SQLiteDatabase db;
	String hqyhm;
	String hqmm;
	String username;
	String password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.denglujiemian);
		yhm = (EditText) findViewById(R.id.yhmEditText);
		mm = (EditText) findViewById(R.id.mmEditText);
		dl = (Button) findViewById(R.id.dlbtn);
		zc = (Button) findViewById(R.id.zcbtn);
		dl.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				hqyhm = yhm.getText().toString();
				hqmm = mm.getText().toString();
				db = Denglujiemian.this.openOrCreateDatabase("my.db3",
						Context.MODE_PRIVATE, null);
				db.execSQL("create table zhucexinxi(_id integer"
						+ " primary key autoincrement,"
						+ " username varchar(50)," + " password varchar(255))");

				Cursor cursor = db.query("zhucexinxi", new String[] { "_id",
						"username", "password" }, null, null, null, null, null);
				while (cursor.moveToNext()) {
					username = cursor.getString(cursor
							.getColumnIndex("username"));
					password = cursor.getString(cursor
							.getColumnIndex("password"));
				}
				db.close();

				if (hqyhm.equals("") || hqmm.equals("")) {
					Toast.makeText(Denglujiemian.this, "用户名或密码不能为空！",
							Toast.LENGTH_SHORT).show();
				} else if (hqyhm.equals(username) && hqmm.equals(password)) {
					Toast.makeText(Denglujiemian.this, "登录成功！",
							Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(Denglujiemian.this,
							MainActivity.class);
					startActivity(intent);
				} else {
					Toast.makeText(Denglujiemian.this, "登录失败！",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
		zc.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Denglujiemian.this,
						Zhucejiemian.class);
				startActivity(intent);
			}
		});
	}

}