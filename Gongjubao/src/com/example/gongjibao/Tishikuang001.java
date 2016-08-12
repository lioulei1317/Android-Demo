package com.example.gongjibao;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Tishikuang001 extends Activity {
	Button tskbtn1, tskbtn2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tishikuang001);
		tskbtn1 = (Button) findViewById(R.id.tskbtn1);
		tskbtn2 = (Button) findViewById(R.id.tskbtn2);
		tskbtn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(Tishikuang001.this, "一个简单的提示",
						Toast.LENGTH_SHORT).show();
			}
		});
		tskbtn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// 创建一个toast提示信息
				Toast t = new Toast(Tishikuang001.this);
				// 设置yoast显示的位置 x,y位偏移量
				t.setGravity(Gravity.CENTER, 0, 0);
				// 创建一个imageview
				ImageView image = new ImageView(Tishikuang001.this);
				image.setImageResource(R.drawable.ic_launcher);
				// 创建一个linearlayout
				LinearLayout ll = new LinearLayout(Tishikuang001.this);
				// 向linearlayout添加view组件
				ll.addView(image);
				// 创建一个文本框
				TextView tv = new TextView(Tishikuang001.this);
				tv.setText("我是带图片的");
				tv.setTextSize(25);
				tv.setTextColor(Color.GREEN);
				ll.addView(tv);
				// 设置toast显示自定义的view
				t.setView(ll);
				// 设置toast显示的时间
				t.setDuration(Toast.LENGTH_SHORT);
				t.show();

			}
		});
	}
}
