package com.example.gongjibao;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class Jindutiao001 extends Activity{
	Button jdtks;
	ProgressBar pgb;
	int status = 0;
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 0x11) {
				pgb.setProgress(status);
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jindutiao001);
		jdtks=(Button) findViewById(R.id.jdtks);
		pgb = (ProgressBar) findViewById(R.id.pgs2);
		jdtks.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new Thread() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						while (status <= 100) {
							date();
							handler.sendEmptyMessage(0x11);
						}
					};
				}.start();
			}
		});
	}
	
	private void date() {
		// TODO Auto-generated method stub
		status += 2;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
