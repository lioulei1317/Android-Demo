package com.example.t20160510a_fragmentlive01;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Fragmentlift001 extends Activity {
	Button startActivity, addFragment, backFragment, replaceFragment, finish;
	final String TAG = "�����־";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragmentlife001);
		Log.d(TAG, "-------Activity-------------onCreate-------");
		startActivity = (Button) findViewById(R.id.fragment_life001);
		addFragment = (Button) findViewById(R.id.fragment_life002);
		backFragment = (Button) findViewById(R.id.fragment_life003);
		replaceFragment = (Button) findViewById(R.id.fragment_life004);
		finish = (Button) findViewById(R.id.fragment_life005);
		// ΪstartActivity��ť���¼�������
		startActivity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Fragmentlift001.this,
						Fragmentlift002.class);
				startActivity(intent);
			}
		});
		// ΪaddFragment��ť���¼�������
		addFragment.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragmentlift003_lifecycleFragment fragment = new Fragmentlift003_lifecycleFragment();
				// ʹ��fragment�滻linearid������ǰ��ʾ��Fragment
				getFragmentManager().beginTransaction()
						.replace(R.id.linearid, fragment).commit();

			}
		});
		// ΪbackFragment��ť���¼�������
		backFragment.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragmentlift004 fragment = new Fragmentlift004();
				getFragmentManager().beginTransaction()
						.replace(R.id.linearid, fragment).addToBackStack("aa")// ���滻ǰ��Fragment��ӵ�Backջ
						.commit();
			}
		});
		// ΪreplaceFragment��ť���¼�������
		replaceFragment.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragmentlift004 fragment = new Fragmentlift004();
				getFragmentManager().beginTransaction()
						.replace(R.id.linearid, fragment).commit();
			}
		});
		// Ϊfinish��ť���¼�������
		finish.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ������Activity
				Fragmentlift001.this.finish();
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d(TAG, "-------Activity-------------onStart-------");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d(TAG, "-------Activity-------------onResume-------");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d(TAG, "-------Activity-------------onPause-------");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d(TAG, "-------Activity-------------onStop-------");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.d(TAG, "-------Activity-------------onRestart-------");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d(TAG, "-------Activity-------------onDestroy-------");
	}
}
