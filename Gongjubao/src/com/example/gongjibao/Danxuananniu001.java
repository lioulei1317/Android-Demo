package com.example.gongjibao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class Danxuananniu001 extends Activity{
	RadioGroup rgp;
	RadioButton rb1, rb2;
	Button dxanfhbtn;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.danxuananniu001);
			rgp = (RadioGroup) findViewById(R.id.rgp);
			rb1 = (RadioButton) findViewById(R.id.rb1);
			rb2 = (RadioButton) findViewById(R.id.rb2);
			dxanfhbtn=(Button) findViewById(R.id.dxanfhbtn);
			rgp.setOnCheckedChangeListener(occl);
			dxanfhbtn.setOnClickListener(fh);
		}
		OnCheckedChangeListener occl = new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup aroup, int checkId) {
				// 第一个参数  是被选中的RadioButton所在的radiogroup的"id@+id/rgp"
				// 第二个参数  是被选中的RadioButton的id"@+id/rb1"或"@+id/rb2"
				if (rb1.getId() == checkId) {
					Toast.makeText(Danxuananniu001.this, "男", Toast.LENGTH_SHORT).show();
				} else if (rb2.getId() == checkId) {
					Toast.makeText(Danxuananniu001.this, "女", Toast.LENGTH_SHORT).show();
				}
			}
		};
		OnClickListener fh=new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(Danxuananniu001.this,MainActivity01.class);
				startActivity(intent);
			}
		};
}
