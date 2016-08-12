package com.example.yindaoye1;

import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class Firstjiemian001 extends Activity {
	static final int NOTIFICATION_ID = 0x123;
	NotificationManager nm;
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x123) {
				InAlertDialog();
			} else if (msg.what == 0x124) {
				Intent intent = new Intent();
				intent.setAction("com.sky.RECEIVER");
				sendBroadcast(intent);
				// fsxiaoxi();
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fristjiemian001);
		// 获取系统NotificationManager服务
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				handler.sendEmptyMessage(0x123);
			}
		}, 3000);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	// 创建一个对话框
	private void InAlertDialog() {
		ImageView imageview = new ImageView(this);
		imageview.setImageResource(R.drawable.meinv4);
		new AlertDialog.Builder(this).setTitle("信息").setView(imageview)
				.setPositiveButton("确定", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						new Timer().schedule(new TimerTask() {

							@Override
							public void run() {
								// TODO Auto-generated method stub

								handler.sendEmptyMessage(0x124);
							}
						}, 3000);
					}
				})// 创建、并显示对话框
				.create().show();
	}

	// //发送消息
	// private void fsxiaoxi(){
	// Notification notify = new Notification.Builder(this)
	// // 设置打开通知，该通知消失
	// .setAutoCancel(true)
	// // 设置显示在状态栏的通知提示消息
	// .setTicker("有新消息！")
	// // 设置通知图标
	// .setSmallIcon(R.drawable.notify)
	// // 设置通知标题
	// .setContentTitle("您有一条新的消息")
	// // 设置通知内容
	// .setContentText("中国福利彩票：你在20160817期中奖2亿，欢迎前来兑换！")
	// // 设置使用系统默认的声音、默认LED灯
	// .setDefaults(
	// Notification.DEFAULT_SOUND
	// | Notification.DEFAULT_LIGHTS)
	// .setWhen(System.currentTimeMillis())
	// // 设改通知将要启动程序的Intent
	// .build();
	// // 发送通知
	// nm.notify(NOTIFICATION_ID, notify);
	// }
}
