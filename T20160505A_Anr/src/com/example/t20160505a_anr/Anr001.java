package com.example.t20160505a_anr;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Anr001 extends Activity {
	Button anr;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anr001);
		anr=(Button) findViewById(R.id.anrbtn001);
		anr.setOnClickListener(new OnClickListener() {
			int i=0;
			@Override
			public void onClick(View v) {
				// 在主线程里不能进行延迟操作，主线程阻塞将会造成ANR错误！
				while(true){
				try {
					Thread.sleep(5000);
					System.out.println("---------"+i++);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				}
		});
	}


}
