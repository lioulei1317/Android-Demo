package com.example.gongjibao;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Configuration001 extends Activity {
	EditText ori, navigation, touch, mnc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.configuration001);
		ori = (EditText) findViewById(R.id.configurationet001);
		navigation = (EditText) findViewById(R.id.configurationet002);
		touch = (EditText) findViewById(R.id.configurationet003);
		mnc = (EditText) findViewById(R.id.configurationet004);
		Button bn = (Button) findViewById(R.id.configurationbtn005);
		bn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ��ȡϵͳ��configuration����
				Configuration cfg = getResources().getConfiguration();
				// �ж���Ļ����
				String screen = cfg.orientation == Configuration.ORIENTATION_LANDSCAPE ? "������Ļ"
						: "������Ļ";
				//�������
				String mnccode=cfg.mnc+"";
				//�������
				String naviname=cfg.orientation==Configuration.NAVIGATION_NONAV?"û�з������"
						:cfg.orientation==Configuration.NAVIGATION_WHEEL?"�������Ʒ���"
								:cfg.orientation==Configuration.NAVIGATION_DPAD?"��������Ʒ���"
										:"�켣����Ʒ���";
				//�Ƿ�֧�ִ�����
				String touchname=cfg.touchscreen==Configuration.TOUCHSCREEN_NOTOUCH?"�޴�����"
						:"֧�ִ�����";
				navigation.setText(naviname);
				ori.setText(screen);
				mnc.setText(mnccode);
				touch.setText(touchname);
			}
		});

	}

}
