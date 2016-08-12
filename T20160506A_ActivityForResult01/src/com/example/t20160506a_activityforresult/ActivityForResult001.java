package com.example.t20160506a_activityforresult;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ActivityForResult001 extends Activity {
	EditText city;
	Button bn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activityforresult001);
		bn=(Button) findViewById(R.id.activityforbtn001);
		city=(EditText) findViewById(R.id.activityforett001);
		bn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ActivityForResult001.this,ActivityForResult002.class);
				//����ָ����Activity���ȴ����ؽ��
				//����33�������룬���ڱ�ʶ������
				startActivityForResult(intent, 33);
			}
		});
		
	}

	// ��д�÷������÷����Իص��ķ�ʽ����ȡָ��Activity
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		// ��requestCode=33��resultCode=55��Ҳ���Ǵ����ض��Ľ��
		if (requestCode == 33 && resultCode == 55) {
			// ȡ��Intent���Extras����
			Bundle data = intent.getExtras();
			// ȡ��Bundle������
			String resultCity = data.getString("city");
			// �޸�city�ı��������
			city.setText(resultCity);
		}
	}
}
