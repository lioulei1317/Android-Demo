package com.example.t20160519a_boradcastreceiver01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BroadcastReceiver002 extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub

		Toast.makeText(
				context,
				"接收到的Intent的Action为：" + intent.getAction() + "\n" + "接受的消息是："
						+ intent.getStringExtra("msg"), Toast.LENGTH_LONG)
				.show();
	}

}
