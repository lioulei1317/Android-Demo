package com.example.t20160420e;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Listlayout_Activity02 extends Activity{
	ImageView image;
	TextView list_txt;
	TextView txt4;
	TextView txt2;
	TextView txt3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_layout2);
		Intent intent = getIntent();
		Info info = (Info) intent.getSerializableExtra("info");
		image = (ImageView) findViewById(R.id.list2_image2);
		list_txt = (TextView) findViewById(R.id.list2_name);//Ãû×Ö
		txt2 = (TextView) findViewById(R.id.list2_time);
		txt3 = (TextView) findViewById(R.id.list2_shuju);
		txt4 = (TextView) findViewById(R.id.list2_pinglun);
		image.setImageResource(info.getImg());
		list_txt.setText(info.getTitle());
		txt2.setText(info.getTime());
		txt3.setText(info.getContext());
		txt4.setText("ÆÀÂÛ("+info.getCount()+")");

	}

}
