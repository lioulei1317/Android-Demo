package com.example.gongjibao;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.widget.ImageView;

public class Handler001 extends Activity {
	ImageView handlerimv001;
	int[] image = { R.drawable.meinv1, R.drawable.meinv2, R.drawable.meinv3,
			R.drawable.meinv4 };
	int s=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handler001);
		handlerimv001 = (ImageView) findViewById(R.id.handlerimv001);
		
		final Handler handler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				if(msg.what==0x123){
					handlerimv001.setImageResource(image[s++%image.length]);
				}
			}
		};
		//定义一个计时器，让该计时器周期性地执行指定任务
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				handler.sendEmptyMessage(0x123);
			}
		},0,1200);
	}

}
