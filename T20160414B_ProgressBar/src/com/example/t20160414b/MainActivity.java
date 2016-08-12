package com.example.t20160414b;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
	Button bn;
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
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bn = (Button) findViewById(R.id.bn);
		pgb = (ProgressBar) findViewById(R.id.pgs2);
		bn.setOnClickListener(new OnClickListener() {

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
