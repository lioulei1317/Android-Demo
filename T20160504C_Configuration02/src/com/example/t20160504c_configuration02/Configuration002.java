package com.example.t20160504c_configuration02;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Configuration002 extends Activity {
	Button configurationbtn0001;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.configuration002);
		configurationbtn0001 = (Button) findViewById(R.id.configurationbtn0001);
		configurationbtn0001.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Configuration cfg = getResources().getConfiguration();
				// �����ǰ�Ǻ���
				if (cfg.orientation == Configuration.ORIENTATION_LANDSCAPE) {
					// ��Ϊ����
					Configuration002.this
							.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

				}
				// �����ǰ������
				if (cfg.orientation == Configuration.ORIENTATION_PORTRAIT) {
					// ��Ϊ����
					Configuration002.this
							.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

				}

			}
		});

	}

	// ���ڼ������õĸı�
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		String screen = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? "������Ļ"
				: "������Ļ";
		Toast.makeText(this, "ϵͳ��Ļ�������ı�\n�ı�����Ļ����Ϊ:" + screen,
				Toast.LENGTH_SHORT).show();
	}
}
