package com.example.t20160519b_boradcastreceiver02;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class BroadcastReceiver_002 extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(context,"���յ���Intent��ActionΪ��" 
				+ intent.getAction() + "\n��Ϣ�����ǣ�"
				+intent.getStringExtra("msg"), Toast.LENGTH_LONG).show();
		//����һ��bundle���󣬲���������
		Bundle bundle=new Bundle();
		bundle.putString("first", "��һ��BroadcastReceiver�������Ϣ");
		//��bundle��������
		setResultExtras(bundle);
		
		//ȡ��Broadcast�ļ�������
		//abortBroadcast();
	}

}
