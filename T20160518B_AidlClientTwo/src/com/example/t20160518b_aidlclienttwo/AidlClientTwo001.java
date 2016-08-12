package com.example.t20160518b_aidlclienttwo;

import java.util.List;

import com.example.t20160518b_aidlservicetwo.IPet;
import com.example.t20160518b_aidlservicetwo.Person;
import com.example.t20160518b_aidlservicetwo.Pet;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class AidlClientTwo001 extends Activity {
	private IPet petService;
	private Button get;
	EditText personView;
	ListView showView;
	private ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			petService = null;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// ��ȡԶ��Service��onBind�������صĶ���Ĵ���
			petService = IPet.Stub.asInterface(service);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aidlclienttwo001);
		get = (Button) findViewById(R.id.getbtn002);
		personView = (EditText) findViewById(R.id.personedt002);
		showView = (ListView) findViewById(R.id.showlistv002);
		// ��������󶨵�Service��Intent
		Intent intent = new Intent();
		intent.setAction("zcvasdfvasdjvbasd");
		// ��Զ��Service
		bindService(intent, conn, Service.BIND_AUTO_CREATE);
		get.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					String personName = personView.getText().toString();
					// ����Զ��Service�ķ���
					List<Pet> pets = petService.getPets(new Person(1,
							personName, personName));
					// �����򷵻ص�List��װ��ArrayAdapter
					ArrayAdapter<Pet> adapter = new ArrayAdapter<Pet>(
							AidlClientTwo001.this,
							android.R.layout.simple_list_item_1, pets);
					showView.setAdapter(adapter);

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//�����
		this.unbindService(conn);
	}
}
