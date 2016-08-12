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
	// ������������صõ���ͼƬ
	Bitmap bitmap;
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x123) {
				// ʹ��Imageview��ʾ��ͼƬ
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
					// �򿪸�URL��Ӧ����Դ��������
					InputStream inputstream = url.openStream();
					// ��InputStream�н�����ͼƬ
					bitmap = BitmapFactory.decodeStream(inputstream);
					// ������Ϣ��֪ͨUI�����ʾ��ͼƬ
					handler.sendEmptyMessage(0x123);
					inputstream.close();
					// �ٴδ�URL��Ӧ��Դ��������
					inputstream = url.openStream();
					// ���ֻ��ļ���Ӧ�������
					OutputStream os = openFileOutput("crazy.png",
							MODE_WORLD_READABLE);
					byte[] buff = new byte[1024];
					int hasRead = 0;
					// ��URL��Ӧ����Դ���ص�����
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
