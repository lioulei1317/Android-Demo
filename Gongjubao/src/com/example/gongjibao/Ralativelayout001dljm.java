package com.example.gongjibao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ralativelayout001dljm extends Activity {
	EditText xdbjyhmbjk0001, xdbjmmbjk0001;
	Button xdbjdlkbun0001, xdbjfhbun0001,xdbjzcbun0001;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ralativelayout001dljm);
		xdbjyhmbjk0001 = (EditText) findViewById(R.id.xdbjyhmbjk0001);
		xdbjmmbjk0001 = (EditText) findViewById(R.id.xdbjmmbjk0001);
		xdbjdlkbun0001 = (Button) findViewById(R.id.xdbjdlkbun0001);
		xdbjfhbun0001 = (Button) findViewById(R.id.xdbjfhbun0001);
		xdbjzcbun0001=(Button) findViewById(R.id.xdbjzcbun0001);
		xdbjdlkbun0001.setOnClickListener(dl);
		xdbjfhbun0001.setOnClickListener(fanhui);
		xdbjzcbun0001.setOnClickListener(zcjm);

	}

	OnClickListener dl = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent i = getIntent();
			String s = i.getStringExtra("用户名");
			String k = i.getStringExtra("密码");
			if (xdbjyhmbjk0001.getText().toString().equals(s)
					&& xdbjmmbjk0001.getText().toString().equals(k)) {
				Toast.makeText(Ralativelayout001dljm.this, "登录成功",
						Toast.LENGTH_SHORT).show();
			} else if (xdbjyhmbjk0001.getText().toString().equals("")
					|| xdbjmmbjk0001.getText().toString().equals("")) {
				Toast.makeText(Ralativelayout001dljm.this, "用户名或密码不能为空",
						Toast.LENGTH_SHORT).show();
			}else if(!xdbjyhmbjk0001.getText().toString().equals(s)
					|| !xdbjmmbjk0001.getText().toString().equals(k)){
				Toast.makeText(Ralativelayout001dljm.this, "用户名或密码错误",
						Toast.LENGTH_SHORT).show();
			}
		}
	};
	OnClickListener fanhui = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(Ralativelayout001dljm.this,
					Ralativelayout001.class);
			startActivity(intent);
		}
	};
	OnClickListener zcjm=new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(Ralativelayout001dljm.this,Ralativelayout001zcjm.class);
			startActivity(intent);
		}
	};
}
