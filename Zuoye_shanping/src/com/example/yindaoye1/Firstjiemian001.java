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
		// ��ȡϵͳNotificationManager����
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

	// ����һ���Ի���
	private void InAlertDialog() {
		ImageView imageview = new ImageView(this);
		imageview.setImageResource(R.drawable.meinv4);
		new AlertDialog.Builder(this).setTitle("��Ϣ").setView(imageview)
				.setPositiveButton("ȷ��", new OnClickListener() {

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
				})// ����������ʾ�Ի���
				.create().show();
	}

	// //������Ϣ
	// private void fsxiaoxi(){
	// Notification notify = new Notification.Builder(this)
	// // ���ô�֪ͨ����֪ͨ��ʧ
	// .setAutoCancel(true)
	// // ������ʾ��״̬����֪ͨ��ʾ��Ϣ
	// .setTicker("������Ϣ��")
	// // ����֪ͨͼ��
	// .setSmallIcon(R.drawable.notify)
	// // ����֪ͨ����
	// .setContentTitle("����һ���µ���Ϣ")
	// // ����֪ͨ����
	// .setContentText("�й�������Ʊ������20160817���н�2�ڣ���ӭǰ���һ���")
	// // ����ʹ��ϵͳĬ�ϵ�������Ĭ��LED��
	// .setDefaults(
	// Notification.DEFAULT_SOUND
	// | Notification.DEFAULT_LIGHTS)
	// .setWhen(System.currentTimeMillis())
	// // ���֪ͨ��Ҫ���������Intent
	// .build();
	// // ����֪ͨ
	// nm.notify(NOTIFICATION_ID, notify);
	// }
}
