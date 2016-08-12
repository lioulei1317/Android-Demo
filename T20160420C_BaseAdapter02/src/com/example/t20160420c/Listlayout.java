package com.example.t20160420c;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Listlayout extends Activity{
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
		image = (ImageView) findViewById(R.id.list_image);
		list_txt = (TextView) findViewById(R.id.list_tv1);
		txt2 = (TextView) findViewById(R.id.list_tv2);
		txt3 = (TextView) findViewById(R.id.list_tv3);
		txt4 = (TextView) findViewById(R.id.list_tv4);
		image.setImageResource(info.getImage());
		list_txt.setText(info.getName());
		txt3.setText(info.getContext());

	}
}
