package com.example.gongjibao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter001_04 extends Activity {
	ImageView image;
	TextView list_txt;
	TextView txt4;
	TextView txt2;
	TextView txt3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listviewadapter002);
		Intent intent = getIntent();
		ListViewAdapter001_02 info = (ListViewAdapter001_02) intent
				.getSerializableExtra("info");
		image = (ImageView) findViewById(R.id.image);
		list_txt = (TextView) findViewById(R.id.list_txt);// Ãû×Ö
		txt2 = (TextView) findViewById(R.id.txt2);
		txt3 = (TextView) findViewById(R.id.txt3);
		txt4 = (TextView) findViewById(R.id.txt4);
		image.setImageResource(info.getImg());
		list_txt.setText(info.getTitle());
		txt2.setText(info.getTime());
		txt3.setText(info.getContext());
		txt4.setText("ÆÀÂÛ(" + info.getCount() + ")");

	}

}
