package com.example.yindaoye1;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BroadcastReceiver001 extends BroadcastReceiver {
	static final int NOTIFICATION_ID = 0x123;
	private Context context;

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
//		Intent intent = new Intent(context, Denglujiemian001.class);
//		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		context.startActivity(i);
		this.context = context;
		fsxiaoxi();
	}

	// ������Ϣ
	private void fsxiaoxi() {

		Notification notification = new Notification(R.drawable.notify,
				"�µ���Ϣ!", System.currentTimeMillis());
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
				new Intent(context, Firstjiemian001.class), 0);
		notification.setLatestEventInfo(context, "����һ���µ���Ϣ", "�й�������Ʊ����ã���ϲ���н�500����......",
				contentIntent);
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		notificationManager.notify(NOTIFICATION_ID, notification);
	}

}
