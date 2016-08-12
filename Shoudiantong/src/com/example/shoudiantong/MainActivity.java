package com.example.shoudiantong;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button btn;
	private boolean isopen = false;
	private Camera camera;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn = (Button) findViewById(R.id.shoudianbtn001);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isopen) {
					btn.setText("�ر�");
					Toast.makeText(MainActivity.this, "���Ѿ������ֵ�Ͳ",
							Toast.LENGTH_SHORT).show();
					//�������
					camera = Camera.open();
					Parameters params = camera.getParameters();// ��ȡ���������
					  //���������������FLASH_MODE_TORCH  ���������ƣ�FLASH_MODE_ON ֻ��һ��
					params.setFlashMode(Parameters.FLASH_MODE_TORCH);
					//ʹ��Camera.setParameters����������Ϳ���
					camera.setParameters(params);
					camera.startPreview();// ��ʼ����
					isopen = true;
				} else {
					btn.setText("����");
					Toast.makeText(getApplicationContext(), "���ر����ֵ�Ͳ",
							Toast.LENGTH_SHORT).show();
					camera.stopPreview(); // �ص�����
					camera.release(); // �ص������
					isopen = false;
				}

			}
		});
	}

}
