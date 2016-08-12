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
				// 如果当前是横屏
				if (cfg.orientation == Configuration.ORIENTATION_LANDSCAPE) {
					// 设为竖屏
					Configuration002.this
							.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

				}
				// 如果当前是竖屏
				if (cfg.orientation == Configuration.ORIENTATION_PORTRAIT) {
					// 设为横屏
					Configuration002.this
							.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

				}

			}
		});

	}

	// 用于监听设置的改变
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		String screen = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? "横向屏幕"
				: "竖向屏幕";
		Toast.makeText(this, "系统屏幕方向发生改变\n改变后的屏幕方向为:" + screen,
				Toast.LENGTH_SHORT).show();
	}
}
