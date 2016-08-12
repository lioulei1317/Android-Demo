package com.best.heart;

import com.example.nature.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class IndexActivity extends Activity {
	TextView myMusic,findMusic;
	ImageView iv_tu1,iv_tu2,iv_tu3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_index);
		myMusic = (TextView) findViewById(R.id.mymusic);
		findMusic  = (TextView) findViewById(R.id.findmusic);
		iv_tu1 = (ImageView) findViewById(R.id.imageView1);
		iv_tu2 = (ImageView) findViewById(R.id.imageView2);
		iv_tu3 = (ImageView) findViewById(R.id.imageView3);
	}

	public void addMusic(View v){
		Intent intent = new Intent(this, MusicListActivity.class);
		startActivity(intent);
	}
	public void shoucang(View v){
		//Intent intent = new Intent(this, CollectActivity.class);
		//startActivity(intent);
	}
	public void my(View v){
		myMusic.setTextSize(20);
		findMusic.setTextSize(10);
	}
	public void find(View v){
		myMusic.setTextSize(10);
		findMusic.setTextSize(20);
	}
	public void tu1(View v){
		Intent intent = new Intent(this, MusicListActivity.class);
		startActivity(intent);
	}
	public void tu2(View v){
		//Intent intent = new Intent(this, SongerListActivity.class);
		//startActivity(intent);
	}
	public void tu3(View v){
	}
	@Override
	protected void onRestart() {
		super.onRestart();
	}
}
