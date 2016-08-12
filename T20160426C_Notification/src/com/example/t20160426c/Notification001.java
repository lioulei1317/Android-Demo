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
		// ��ȡϵͳNotificationManager����
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	}

	public void send(View source) {
		Intent intent = new Intent(Notification001.this, Notification002.class);
		// pendingIntent��һ�������Intent��
		// ��Ҫ����������Intent��ִ�����̵ģ�
		// ��pendingIntent��ִ�в������̵ġ�
		// pendingIntentִ�еĲ���ʵ�����ǲ�����������Intent�Ĳ�����
		// ����ʹ��pendingIntent��Ŀ������
		// ����������Intent�Ĳ�����ִ������Ҫ����ĳЩ�����ġ�
		PendingIntent pi = PendingIntent.getActivity(Notification001.this, 0,
				intent, 0);
		Notification notify = new Notification.Builder(this)
		// ���ô�֪ͨ����֪ͨ��ʧ
				.setAutoCancel(true)
				// ������ʾ��״̬����֪ͨ��ʾ��Ϣ
				.setTicker("������Ϣ��")
				// ����֪ͨͼ��
				.setSmallIcon(R.drawable.notify)
				// ����֪ͨ����
				.setContentTitle("����һ���µ���Ϣ")
				// ����֪ͨ����
				.setContentText("�й�������Ʊ���𾴵�������ã�����201608���н�2�ڣ���ӭǰ���һ���")
				// ����ʹ��ϵͳĬ�ϵ�������Ĭ��LED��
//				.setDefaults(
//						Notification.DEFAULT_SOUND
//								| Notification.DEFAULT_LIGHTS)
				// ����֪ͨ���Զ�������
				 .setSound(Uri.parse("android.resource://com.example.t20160426c/"
				 + R.raw.msg))
				.setWhen(System.currentTimeMillis())
				// ���֪ͨ��Ҫ���������Intent
				.setContentIntent(pi).build();
		// ����֪ͨ
		nm.notify(NOTIFICATION_ID, notify);

	}
	public void del(View v){
		nm.cancel(NOTIFICATION_ID);
		
	}
}
