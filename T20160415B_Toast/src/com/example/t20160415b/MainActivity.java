package com.example.t20160415b;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button bn1,bn2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bn1=(Button) findViewById(R.id.bn1);
		bn2=(Button) findViewById(R.id.bn2);
		bn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "一个简单的提示", Toast.LENGTH_SHORT).show();
			}
		});
		bn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//创建一个toast提示信息
				Toast t=new Toast(MainActivity.this);
				//设置Toast显示的位置  x,y位偏移量
				t.setGravity(Gravity.CENTER, 0, 0);
				//创建一个imageview
				ImageView image=new ImageView(MainActivity.this);
				image.setImageResource(R.drawable.ic_launcher);
				//创建一个linearlayout
				LinearLayout ll=new LinearLayout(MainActivity.this);
				//向linearlayout添加view组件
				ll.addView(image);
				//创建一个文本框
				TextView tv=new TextView(MainActivity.this);
				tv.setText("我是带图片的");
				tv.setTextSize(25);
				tv.setTextColor(Color.GREEN);
				ll.addView(tv);
				//设置toast显示自定义的view
				t.setView(ll);
				//设置toast显示的时间
				t.setDuration(Toast.LENGTH_SHORT);
				t.show();
				
			}
		});
	}

}
