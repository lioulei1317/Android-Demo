package com.example.t20160524a_urlconnection01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class URL001 extends Activity {
	ImageView imageview;
	// 代表从网络下载得到的图片
	Bitmap bitmap;
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x123) {
				// 使用Imageview显示该图片
				imageview.setImageBitmap(bitmap);
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.url001);
		imageview = (ImageView) findViewById(R.id.urlimageview);
		new Thread() {
			public void run() {
				try {
					URL url = new URL(
							"http://img.my.csdn.net/uploads/201504/12/1428806103_9476.png");
					// 打开该URL对应的资源的输入流
					InputStream inputstream = url.openStream();
					// 从InputStream中解析出图片
					bitmap = BitmapFactory.decodeStream(inputstream);
					// 发送消息、通知UI组件显示该图片
					handler.sendEmptyMessage(0x123);
					inputstream.close();
					// 再次打开URL对应资源的输入流
					inputstream = url.openStream();
					// 打开手机文件对应的输出流
					OutputStream os = openFileOutput("crazy.png",
							MODE_WORLD_READABLE);
					byte[] buff = new byte[1024];
					int hasRead = 0;
					// 将URL对应的资源下载到本地
					while ((hasRead = inputstream.read(buff)) > 0) {
						os.write(buff, 0, hasRead);
					}
					inputstream.close();
					os.close();

				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
		}.start();
	}

}
