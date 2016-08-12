package com.example.t20160520c_mediaplayer03;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MediaPlayer_003 extends Activity {
	Button btn1, btn2;
	MediaPlayer mediaPlayer1, mediaPlayer2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mediaplayer_003);
		btn1 = (Button) findViewById(R.id.mdaolayer001);
		btn2 = (Button) findViewById(R.id.mdaolayer002);
		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mediaPlayer1 == null) {
					btn1.setText("1.��ͣ����");
					// ʹ��MediaPlayer����Uri��װ��ָ���������ļ�
					mediaPlayer1 = new MediaPlayer().create(
							MediaPlayer_003.this,
							Uri.parse("http://up.haoduoge.com/mp3/2016-05-03/1462260302.mp3"));
					// ����
					mediaPlayer1.start();
				} else {
					if (mediaPlayer1.isPlaying()) {// �ж��Ƿ��ڲ���
						// ��ͣ����
						mediaPlayer1.pause();
						btn1.setText("1.��ʼ����");
					} else {
						// ��������
						mediaPlayer1.start();
						btn1.setText("1.��ͣ����");
					}
				}
			}
		});
		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mediaPlayer2 == null) {
					btn2.setText("2.��ͣ����");
					// ����Ҫ���ص�����mp3��ַ
					Uri uri = Uri
							.parse("http://up.haoduoge.com/mp3/2016-05-03/1462260302.mp3");
					mediaPlayer2 = new MediaPlayer();
					try {
						// ʹ��MediaPlayer����Uri��װ��ָ���������ļ�
						mediaPlayer2.setDataSource(MediaPlayer_003.this, uri);
						// ׼������
						mediaPlayer2.prepare();
						// ��������
						mediaPlayer2.start();

					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				} else {
					if (mediaPlayer2.isPlaying()) {// �ж��Ƿ��ڲ���
						// ��ͣ����
						mediaPlayer2.pause();
						btn2.setText("2.��ʼ����");

					} else {
						// ��������
						mediaPlayer2.start();
						btn2.setText("2.��ͣ����");
					}

				}
			}
		});
	}

}
