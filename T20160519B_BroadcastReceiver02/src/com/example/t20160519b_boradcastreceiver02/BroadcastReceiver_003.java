package com.example.t20160519b_boradcastreceiver02;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class BroadcastReceiver_003 extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		//��ȡ�ϸ��㲥���ݵ���Ϣ
		Bundle bundle=getResultExtras(true);
		// ����ǰһ��BroadcastReceiver�������keyΪfirst����Ϣ
		String first=bundle.getString("first");
		Toast.makeText(context, "��һ��Broadcast�������ϢΪ��"+first, Toast.LENGTH_LONG).show();
	}

}
