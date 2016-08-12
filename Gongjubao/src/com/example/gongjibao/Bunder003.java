package com.example.gongjibao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Bunder003 extends Activity{
	TextView name,passwd,sex;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bunder002);
		name=(TextView) findViewById(R.id.bumdertvw001);
		passwd=(TextView) findViewById(R.id.bumdertvw002);
		sex=(TextView) findViewById(R.id.bumdertvw003);
		Intent intent=getIntent();
		Bunder002 p=(Bunder002) intent.getSerializableExtra("Bander002");
		name.setText("您的用户名为："+p.getName());
		passwd.setText("您的密码为："+p.getPasswd());
		sex.setText("您的性别为："+p.getSex());
		
	}

}
