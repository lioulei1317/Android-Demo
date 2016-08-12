package com.example.t20160420e;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Listlayout_Activity extends Activity{
	ImageView image;
	TextView biaobai;
	TextView pinglun;
	TextView shijian;
	TextView jujue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_layout);
		Intent intent = getIntent();
		Info info = (Info) intent.getSerializableExtra("info");
		image = (ImageView) findViewById(R.id.image);
		biaobai = (TextView) findViewById(R.id.list_txt);//Ãû×Ö
		shijian = (TextView) findViewById(R.id.txt2);
		jujue = (TextView) findViewById(R.id.txt3);
		pinglun = (TextView) findViewById(R.id.txt4);
		image.setImageResource(info.getImg());
		biaobai.setText(info.getTitle());
		shijian.setText(info.getTime());
		jujue.setText(info.getContext());
		pinglun.setText("ÆÀÂÛ("+info.getCount()+")");

	}

}
