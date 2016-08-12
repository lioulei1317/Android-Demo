package com.example.t20160413;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button bt0, bt1, bt2, bt3;
	ImageView iv1, iv2;
	int[] tupian = { R.drawable.beijinga, R.drawable.beijingb,
			R.drawable.beijingc, R.drawable.beijingd, R.drawable.a,
			R.drawable.ic_launcher };
	int a = 0;
	int alpha = 255;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bt0 = (Button) findViewById(R.id.bt0);
		bt1 = (Button) findViewById(R.id.bt1);
		bt2 = (Button) findViewById(R.id.bt2);
		bt3 = (Button) findViewById(R.id.bt3);
		iv1 = (ImageView) findViewById(R.id.iv1);
		iv2 = (ImageView) findViewById(R.id.iv2);
		bt2.setOnClickListener(ocl);
		bt3.setOnClickListener(ocl);
		bt1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				iv1.setImageResource(tupian[++a % tupian.length]);
			}
		});
		bt0.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (--a % tupian.length > 0) {
					iv1.setImageResource(tupian[--a % tupian.length]);
				} else {
					Toast.makeText(MainActivity.this, "当前为第一张",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		iv1.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View view, MotionEvent event) {
				// TODO Auto-generated method stub
				BitmapDrawable bitmapDrawable = (BitmapDrawable) iv1
						.getDrawable();
				// 获取第一个图片显示框中的位图
				Bitmap bitmp = bitmapDrawable.getBitmap();
				// bitmap图片实际大小与第一个ImageView的缩放比例
				double scale = iv1.getWidth() / 320.0;
				// 获取需要显示的图片的开始点
				int x = (int) (event.getX() * scale);
				int y = (int) (event.getY() * scale);
				if (x + 120 > iv1.getWidth()) {
					x = iv1.getWidth() - 120;
				}
				if (y + 120 > iv1.getHeight()) {
					y = iv1.getHeight() - 120;
				}
				// 显示图片的指定区域
				iv2.setImageBitmap(Bitmap.createBitmap(bitmp, x, y, 120,
						120));
				iv2.setAlpha(alpha);
				return false;
			}
		});
	}

	OnClickListener ocl = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v == bt2) {
				alpha += 20;
			}
			if (v == bt3) {
				alpha -= 20;
			}
			if (alpha >= 255) {
				alpha = 255;

			}
			if (alpha <= 0) {
				alpha = 0;
			}
			iv1.setImageAlpha(alpha);
		}
	};

}
