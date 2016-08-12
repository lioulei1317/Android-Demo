package com.example.t20160425a;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends Activity {
	Gallery fruit_gallery;
	int[] image;
	RadioGroup group1;
	RadioButton rad1;
	RadioButton rad2;
	RadioButton rad3;
	RadioButton rad4;
	Handler mHandler;
	int cut=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		group1=(RadioGroup) findViewById(R.id.group1);
		rad1=(RadioButton) findViewById(R.id.rad1);
		rad2=(RadioButton) findViewById(R.id.rad2);
		rad3=(RadioButton) findViewById(R.id.rad3);
		rad4=(RadioButton) findViewById(R.id.rad4);
		fruit_gallery=(Gallery) findViewById(R.id.fruit_gallery);
		image=new int[]{R.drawable.meinv1,R.drawable.meinv2,R.drawable.meinv3,R.drawable.meinv4};
		TestAdapter adapter=new TestAdapter(this,image);
		fruit_gallery.setAdapter(adapter);
		fruit_gallery.setOnItemSelectedListener(listener);
		group1.setOnCheckedChangeListener(change);
		mHandler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if(msg.what==0x123){
					if(++cut<image.length){
						fruit_gallery.setSelection(cut);
					}else{
						cut=0;
						fruit_gallery.setSelection(cut);
					}
				}
			}
		};
		/*
		 * Timer用来执行一些简单的定时器任务
		 * TimerTask 要执行的任务
		 * 创建一个Timer实例，通过提供的schedule（）方法将TimerTask加入到定时器Timer中
		 */
		//延迟，从0秒开始运行，每个2秒run一次
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				mHandler.sendEmptyMessage(0x123);

			}
		}, 0, 2000);

	}

	OnItemSelectedListener listener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1,
				int position, long arg3) {
			int id = R.id.rad1;
			switch (position) {
			case 0:
				id = R.id.rad1;
				break;
			case 1:
				id = R.id.rad2;
				break;
			case 2:
				id = R.id.rad3;
				break;
			case 3:
				id = R.id.rad4;
				break;
			}
			group1.check(id);

		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
	};

	OnCheckedChangeListener change = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			int position = 0;
			if (rad1.isChecked()) {
				position = 0;
				fruit_gallery.setSelection(position);
			} else if (rad2.isChecked()) {
				position = 1;
				fruit_gallery.setSelection(position);
			} else if (rad3.isChecked()) {
				position = 2;
				fruit_gallery.setSelection(position);
			} else if (rad4.isChecked()) {
				position = 3;
				fruit_gallery.setSelection(position);
			}
		}
	};

}
