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
					btn.setText("关闭");
					Toast.makeText(MainActivity.this, "您已经打开了手电筒",
							Toast.LENGTH_SHORT).show();
					//打开照相机
					camera = Camera.open();
					Parameters params = camera.getParameters();// 获取相机参数集
					  //设置照相机参数，FLASH_MODE_TORCH  持续的亮灯，FLASH_MODE_ON 只闪一下
					params.setFlashMode(Parameters.FLASH_MODE_TORCH);
					//使用Camera.setParameters对象来激活和控制
					camera.setParameters(params);
					camera.startPreview();// 开始亮灯
					isopen = true;
				} else {
					btn.setText("开启");
					Toast.makeText(getApplicationContext(), "您关闭了手电筒",
							Toast.LENGTH_SHORT).show();
					camera.stopPreview(); // 关掉亮灯
					camera.release(); // 关掉照相机
					isopen = false;
				}

			}
		});
	}

}
