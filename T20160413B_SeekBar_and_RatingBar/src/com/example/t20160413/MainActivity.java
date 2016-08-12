package com.example.t20160413;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class MainActivity extends Activity {
	SeekBar seb1;
	Button bt0, bt1, bt2, bt3, kais;
	ProgressBar pgb;
	RadioGroup rgp;
	RadioButton rb1, rb2, rb3, rb4;
	ImageView iv1, iv2;
	RatingBar rtb1;
	int[] tupian = { R.drawable.beijinga, R.drawable.beijingb,
			R.drawable.beijingc, R.drawable.beijingd, R.drawable.a };
	int a = 0;
	int alpha = 255;
	int status = 0;
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 0x11) {
				pgb.setProgress(status);
				if (status > 0 && status <= 20) {
					iv1.setImageResource(tupian[0]);
				}
				if (status > 20 && status <= 40) {
					iv1.setImageResource(tupian[1]);
				}
				if (status > 40 && status <= 60) {
					iv1.setImageResource(tupian[2]);
				}
				if (status > 60 && status <= 80) {
					iv1.setImageResource(tupian[3]);
				}
				if (status > 80 && status <= 100) {
					iv1.setImageResource(tupian[4]);
				}
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		rtb1 = (RatingBar) findViewById(R.id.rtb1);
		seb1 = (SeekBar) findViewById(R.id.seb1);
		kais = (Button) findViewById(R.id.kais);
		pgb = (ProgressBar) findViewById(R.id.pgb);
		bt0 = (Button) findViewById(R.id.bt0);
		bt1 = (Button) findViewById(R.id.bt1);
		bt2 = (Button) findViewById(R.id.bt2);
		bt3 = (Button) findViewById(R.id.bt3);
		iv1 = (ImageView) findViewById(R.id.iv1);
		iv2 = (ImageView) findViewById(R.id.iv2);
		bt2.setOnClickListener(ocl);
		bt3.setOnClickListener(ocl);
		rgp = (RadioGroup) findViewById(R.id.rgp);
		rb1 = (RadioButton) findViewById(R.id.rb1);
		rb2 = (RadioButton) findViewById(R.id.rb2);
		rb3 = (RadioButton) findViewById(R.id.rb3);
		rb4 = (RadioButton) findViewById(R.id.rb4);
		rgp.setOnCheckedChangeListener(occl);

		// 星级条
		rtb1.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {

			@Override
			public void onRatingChanged(RatingBar arg0, float arg1, boolean arg2) {
				// TODO Auto-generated method stub
				pgb.setProgress((int) (arg1 * 20));// 星级条控制进度条
				seb1.setProgress((int) (arg1 * 20));
				status = (int) (arg1 * 20);
				handler.sendEmptyMessage(0x11);

			}
		});

		// 拖动条
		seb1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				pgb.setProgress(arg1);
				rtb1.setRating((float) (arg1 / 10 * 0.5));
				status = arg1;
				handler.sendEmptyMessage(0x11);
			}
		});

		kais.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new Thread() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						while (status <= 100) {
							date();
							handler.sendEmptyMessage(0x11);
							if (status == 100) {
								status = 0;
								break;
							}
						}
					}
				}.start();
			}
		});
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
				// 利用BitmapDrawable将Drawable转换为Bitmap
				BitmapDrawable bitmapDrawable = (BitmapDrawable) iv1
						.getDrawable();
				// 获取第一个图片显示框中的位图,从BitmapDrawable中获取Bitmap
				Bitmap bitmp = bitmapDrawable.getBitmap();
				// bitmap图片实际大小与第一个ImageView的缩放比例,图片宽度大于320放大，小于320缩小
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
				// 初始位图，起始x,y 要截图的宽度和高度
				iv2.setImageBitmap(Bitmap.createBitmap(bitmp, x, y, 120, 120));
				// 这是imageview显示的图片，（图片的id）
				// iv2.setImageResource(R.drawable.a);

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
				alpha += 10;
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
	OnCheckedChangeListener occl = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup arg0, int b) {
			// TODO Auto-generated method stub
			if (rb1.getId() == b) {
				iv1.setImageResource(tupian[0]);
			} else if (rb2.getId() == b) {
				iv1.setImageResource(tupian[1]);
			} else if (rb3.getId() == b) {
				iv1.setImageResource(tupian[2]);
			} else if (rb4.getId() == b) {
				iv1.setImageResource(tupian[3]);
			}
		}
	};

	public void date() {
		// TODO Auto-generated method stub
		status += 5;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

};
