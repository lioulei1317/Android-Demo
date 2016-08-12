package com.example.t12_03_exam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Listlayout_Activity extends Activity {
	ImageView image;
	TextView list_txt;
	TextView txt4;
	TextView txt2;
	TextView txt3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_layout);
		Intent intent = getIntent();
		Info info = (Info) intent.getSerializableExtra("info");
		image = (ImageView) findViewById(R.id.image);
		list_txt = (TextView) findViewById(R.id.list_txt);
		txt2 = (TextView) findViewById(R.id.txt2);
		txt3 = (TextView) findViewById(R.id.txt3);
		txt4 = (TextView) findViewById(R.id.txt4);
		image.setImageResource(info.getImg());
		list_txt.setText(info.getTitle());
		txt3.setText(info.getContext());

	}

}
