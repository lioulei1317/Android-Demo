package com.example.t20160426c;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Notification001 extends Activity {
	static final int NOTIFICATION_ID = 0x123;
	NotificationManager nm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification001);
		// 获取系统NotificationManager服务
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	}

	public void send(View source) {
		Intent intent = new Intent(Notification001.this, Notification002.class);
		// pendingIntent是一种特殊的Intent。
		// 主要的区别在于Intent的执行立刻的，
		// 而pendingIntent的执行不是立刻的。
		// pendingIntent执行的操作实质上是参数传进来的Intent的操作，
		// 但是使用pendingIntent的目的在于
		// 它所包含的Intent的操作的执行是需要满足某些条件的。
		PendingIntent pi = PendingIntent.getActivity(Notification001.this, 0,
				intent, 0);
		Notification notify = new Notification.Builder(this)
		// 设置打开通知，该通知消失
				.setAutoCancel(true)
				// 设置显示在状态栏的通知提示消息
				.setTicker("有新消息！")
				// 设置通知图标
				.setSmallIcon(R.drawable.notify)
				// 设置通知标题
				.setContentTitle("您有一条新的消息")
				// 设置通知内容
				.setContentText("中国福利彩票：尊敬的先生你好！你在201608期中奖2亿，欢迎前来兑换！")
				// 设置使用系统默认的声音、默认LED灯
//				.setDefaults(
//						Notification.DEFAULT_SOUND
//								| Notification.DEFAULT_LIGHTS)
				// 设置通知的自定义声音
				 .setSound(Uri.parse("android.resource://com.example.t20160426c/"
				 + R.raw.msg))
				.setWhen(System.currentTimeMillis())
				// 设改通知将要启动程序的Intent
				.setContentIntent(pi).build();
		// 发送通知
		nm.notify(NOTIFICATION_ID, notify);

	}
	public void del(View v){
		nm.cancel(NOTIFICATION_ID);
		
	}
}
