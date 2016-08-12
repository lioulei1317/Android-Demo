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
				// 获取系统的configuration对象
				Configuration cfg = getResources().getConfiguration();
				// 判断屏幕方向
				String screen = cfg.orientation == Configuration.ORIENTATION_LANDSCAPE ? "横向屏幕"
						: "竖向屏幕";
				//网络代号
				String mnccode=cfg.mnc+"";
				//方向控制
				String naviname=cfg.orientation==Configuration.NAVIGATION_NONAV?"没有方向控制"
						:cfg.orientation==Configuration.NAVIGATION_WHEEL?"滚动控制方向"
								:cfg.orientation==Configuration.NAVIGATION_DPAD?"方向键控制方向"
										:"轨迹球控制方向";
				//是否支持触摸屏
				String touchname=cfg.touchscreen==Configuration.TOUCHSCREEN_NOTOUCH?"无触摸屏"
						:"支持触摸屏";
				navigation.setText(naviname);
				ori.setText(screen);
				mnc.setText(mnccode);
				touch.setText(touchname);
			}
		});

	}

}
