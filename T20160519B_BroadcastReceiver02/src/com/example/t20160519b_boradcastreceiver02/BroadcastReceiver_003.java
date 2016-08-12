package com.example.t20160519b_boradcastreceiver02;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class BroadcastReceiver_003 extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		//获取上个广播传递的消息
		Bundle bundle=getResultExtras(true);
		// 解析前一个BroadcastReceiver所存入的key为first的消息
		String first=bundle.getString("first");
		Toast.makeText(context, "第一个Broadcast存入的消息为："+first, Toast.LENGTH_LONG).show();
	}

}
