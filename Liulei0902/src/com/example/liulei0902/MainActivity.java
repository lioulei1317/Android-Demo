package com.example.liulei0902;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	SeekBar seekbar1, seekbar2, seekbar3, seekbar4, seekbar5, seekbar6,
			seekbar7, seekbar8;
	Button bn;
	TextView tv1;
	int seeb1 = 0;
	int seeb2 = 0;
	int seeb3 = 0;
	int seeb4 = 0;
	int seeb5 = 0;
	int seeb6 = 0;
	int seeb7 = 0;
	int seeb8 = 0;
	ArrayList<Integer> list = new ArrayList<Integer>();
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0x121) {
				list.add(1);
			}
			if (msg.what == 0x122) {
				list.add(2);
			}
			if (msg.what == 0x123) {
				list.add(3);
			}
			if (msg.what == 0x124) {
				list.add(4);
			}
			if (msg.what == 0x125) {
				list.add(5);
			}
			if (msg.what == 0x126) {
				list.add(6);
			}
			if (msg.what == 0x127) {
				list.add(7);
			}
			if (msg.what == 0x128) {
				list.add(8);
			}
			if (list.size() == 8) {
				tv1.setText("赛马的结果是:\n第一名:" + list.get(0) + "号\n" + "第二名:"
						+ list.get(1) + "号\n" + "第三名:" + list.get(2) + "号\n"
						+ "第四名:" + list.get(3) + "号\n" + "第五名:" + list.get(4)
						+ "号\n" + "第六名:" + list.get(5) + "号\n" + "第七名:"
						+ list.get(6) + "号\n" + "第八名:" + list.get(7) + "号");
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		seekbar1 = (SeekBar) findViewById(R.id.seekbar001);
		seekbar2 = (SeekBar) findViewById(R.id.seekbar002);
		seekbar3 = (SeekBar) findViewById(R.id.seekbar003);
		seekbar4 = (SeekBar) findViewById(R.id.seekbar004);
		seekbar5 = (SeekBar) findViewById(R.id.seekbar005);
		seekbar6 = (SeekBar) findViewById(R.id.seekbar006);
		seekbar7 = (SeekBar) findViewById(R.id.seekbar007);
		seekbar8 = (SeekBar) findViewById(R.id.seekbar008);
		bn = (Button) findViewById(R.id.bn1);
		tv1 = (TextView) findViewById(R.id.textv1);
		bn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Thread thread1 = new Thread() {
					public void run() {
						while (seeb1 <= 200) {
							date();
							seekbar1.setProgress(seeb1);
						}
						handler.sendEmptyMessage(0x121);
					}
				};
				Thread thread2 = new Thread() {
					public void run() {
						while (seeb2 <= 200) {
							date();
							seekbar2.setProgress(seeb2);
						}
						handler.sendEmptyMessage(0x122);
					}
				};
				Thread thread3 = new Thread() {
					public void run() {
						while (seeb3 <= 200) {
							date();
							seekbar3.setProgress(seeb3);
						}
						handler.sendEmptyMessage(0x123);
					}
				};
				Thread thread4 = new Thread() {
					public void run() {
						while (seeb4 <= 200) {
							date();
							seekbar4.setProgress(seeb4);
						}
						handler.sendEmptyMessage(0x124);
					}
				};
				Thread thread5 = new Thread() {
					public void run() {
						while (seeb5 <= 200) {
							date();

							seekbar5.setProgress(seeb5);
						}
						handler.sendEmptyMessage(0x125);
					}
				};
				Thread thread6 = new Thread() {
					public void run() {
						while (seeb6 <= 200) {
							date();

							seekbar6.setProgress(seeb6);
						}
						handler.sendEmptyMessage(0x126);
					}
				};
				Thread thread7 = new Thread() {
					public void run() {
						while (seeb7 <= 200) {
							date();
							seekbar7.setProgress(seeb7);
						}
						handler.sendEmptyMessage(0x127);
					}
				};
				Thread thread8 = new Thread() {
					public void run() {
						while (seeb8 <= 200) {
							date();
							seekbar8.setProgress(seeb8);
						}
						handler.sendEmptyMessage(0x128);
					}
				};
				thread1.start();
				thread2.start();
				thread3.start();
				thread4.start();
				thread5.start();
				thread6.start();
				thread7.start();
				thread8.start();
			}
		});

	}

	private void date() {
		// TODO Auto-generated method stub
		seeb1 += (int) (Math.random() * 5 + 1);
		seeb2 += (int) (Math.random() * 5 + 1);
		seeb3 += (int) (Math.random() * 5 + 1);
		seeb4 += (int) (Math.random() * 5 + 1);
		seeb5 += (int) (Math.random() * 5 + 1);
		seeb6 += (int) (Math.random() * 5 + 1);
		seeb7 += (int) (Math.random() * 5 + 1);
		seeb8 += (int) (Math.random() * 5 + 1);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
