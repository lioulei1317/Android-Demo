package com.example.musicplayer1;

import java.io.IOException;
import java.util.ArrayList;


import com.example.musicplayer2.Model;

import android.R.bool;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	
	int img[] = { R.drawable.pic_11, R.drawable.pic_11, R.drawable.pic_11, R.drawable.pic_11,
			R.drawable.pic_11, R.drawable.pic_11 };

	
	ListView list;

	MyAadapter adapter;

	Saomiao saomiao;
	ArrayList<Model> alist;
	//
	MediaPlayer media;
	// seekbar
	SeekBar se;
	ImageView nextm,lastm,playm,style;
	
	
	Boolean isplay=false;
	
	Boolean isgeci=false;

	int min = 0;
	int sec = 0;
	int min1 = 0;
	int sec1 = 0;
	int check=0;
	Handler hander = new Handler();
	//
	TextView time;
	TextView time2;
	String star;
	String all;
	Intent mintent;
	Mybroad myBD;
	
	TextView geci,showgeci,nowmusic;
//	ImageView
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		list=(ListView) findViewById(R.id.list);
		saomiao=new Saomiao();
		
		alist=new ArrayList<Model>();
		alist=saomiao.query(this);
		adapter=new MyAadapter(alist, this);
		list.setAdapter(adapter);
		
		
		se=(SeekBar) findViewById(R.id.se);
		media=new MediaPlayer();
		myBD=new Mybroad();
		
		time=(TextView) findViewById(R.id.sec);
		time2=(TextView) findViewById(R.id.min);
		nextm=(ImageView) findViewById(R.id.xia);
		lastm=(ImageView) findViewById(R.id.shang);
		playm=(ImageView) findViewById(R.id.play);
		style=(ImageView) findViewById(R.id.line);
		geci=(TextView) findViewById(R.id.geci);
		showgeci=(TextView) findViewById(R.id.showgeci);
		nowmusic=(TextView) findViewById(R.id.nowmusic);
		
		
		nextm.setOnClickListener(this);
		lastm.setOnClickListener(this);
		playm.setOnClickListener(this);
		style.setOnClickListener(this);
		geci.setOnClickListener(this);
		
		showgeci.setVisibility(View.GONE);
		
		
		//启动服务器
		Intent mintent=new Intent();
		mintent.setClass(MainActivity.this, MyService.class);
		startService(mintent);
		
		zhuce();
		
	    list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				
				isplay=true;
				
				playm.setImageResource(R.drawable.dainji_20);
				
				showgeci.setText("歌词");
				
				
				Intent intent=new Intent();
				intent.setAction("ACTION_INDEX");
				intent.putExtra("index", arg2);
				sendBroadcast(intent);
				
				

			}
		});
//
	    
		se.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				// TODO Auto-generated method stub
				if(fromUser==true)
				{
				Intent intent=new Intent();
				intent.setAction("ACTION_SEEKBAR");
				intent.putExtra("seekbar", progress);
				sendBroadcast(intent);
				}
		
		
				
			}
		});
		
	}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.shang:

			nextmusic(0);
		
			lastm.setImageResource(R.drawable.dianji_22);
		
			break;
		case R.id.xia:

			nextmusic(1);
		
			nextm.setImageResource(R.drawable.dianji_25);
			
			break;
		case R.id.geci:
            isgeci=!isgeci;
            if(isgeci==true){
            	geci.setTextColor(Color.rgb(255, 255, 0));
            	showgeci.setVisibility(View.VISIBLE);
            }else{
            	geci.setTextColor(Color.rgb(255, 255, 255));
            	showgeci.setVisibility(View.GONE);
            }
		
			
			break;
		case R.id.line:
    
			check++;
			if(check>3)
				check=1;
			if(check==1){
				style.setImageResource(R.drawable.dianji_28);
				stylemusic(1);
			}
			if(check==2){
				style.setImageResource(R.drawable.dianji1_28);
				stylemusic(2);
			}
			if(check==3){
				style.setImageResource(R.drawable.pic2_28);
				stylemusic(3);
			}
			
			
			break;
		case R.id.play:

			isplay=!isplay;
			if(isplay==true)
			{
				playm.setImageResource(R.drawable.dainji_20);
			}else{
				playm.setImageResource(R.drawable.dianji_19);
			}
			musicplay(isplay);
			
			break;

		default:
			break;
		}
		
	}
	//发送，播放，暂停广播
	public void musicplay(boolean isplay){
		Intent intent=new Intent();
		intent.setAction("ACTION_ISPLAY");
		intent.putExtra("isplay",isplay);
		sendBroadcast(intent);
	}
	
	//上一曲，下一曲，0上一曲，1下一曲
	public void nextmusic(int num){
		showgeci.setText("歌词");
		
		Intent intent=new Intent();
		intent.setAction("ACTION_NEXT");
		intent.putExtra("next",num);
		sendBroadcast(intent);
	}
	
	public void stylemusic(int num){
		Intent intent=new Intent();
		intent.setAction("ACTION_STYLE");
		intent.putExtra("check",num);
		sendBroadcast(intent);
	}
	
	
	
	class Mybroad extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if(intent.getAction().equals("ACTION_MAXTIME")){
				int maxtime=intent.getIntExtra("maxtime", 0);
				
				se.setMax(maxtime);
				time2.setText(""+settime(maxtime));
			}
			if(intent.getAction().equals("ACTION_NOWTIME")){
				int nowtime=intent.getIntExtra("nowtime", 0);
				
				se.setProgress(nowtime);
				time.setText(""+settime(nowtime));
			}
			if(intent.getAction().equals("ACTION_LRC")){
				String lrc=intent.getStringExtra("geci");
				//Toast.makeText(MainActivity.this, "剧本开始"+lrc, Toast.LENGTH_LONG).show();
				
				showgeci.setText(""+lrc);
				
				
			}
			if(intent.getAction().equals("ACTION_NOWMUSIC")){
				String nowmusic1=intent.getStringExtra("nowmusic");
				//Toast.makeText(MainActivity.this, "剧本开始"+lrc, Toast.LENGTH_LONG).show();
				
				nowmusic.setText("正在播放："+nowmusic1);
				
				
			}
			
		}
		
	}
	public void zhuce() {
		IntentFilter mFilter = new IntentFilter();
		mFilter.addAction("ACTION_MAXTIME");
		mFilter.addAction("ACTION_NOWTIME");
		mFilter.addAction("ACTION_NOWMUSIC");
		mFilter.addAction("ACTION_LRC");
		registerReceiver(myBD, mFilter);
	}
	public String settime(int time){
		int fen=time/60000;
		int miao=time/1000%60;
		return fen+":"+miao;
	}


}
