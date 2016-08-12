package com.example.t20160520b__mediaplayer02;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class MediaPlayer_02 extends Activity {
	private ImageButton Start_Button, Shang_Button, Xia_Button;
	// ���Ź����࣬ϵͳ��
	private MediaPlayer mediaPlayer = null;
	private TextView textView;
	
	private static int current = -1;
	
	// ���������Դ��ID
	final int[] musics = { R.raw.qiyou, R.raw.choubaguai, R.raw.daoxiang };
	final String[] name = { "����-������ ", "��˹�-Ѧ֮ǫ ", "����-�ܽ���" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mediaplayer_02);
		
		// ͨ��findViewById�ҵ���Դ
		Start_Button = (ImageButton) findViewById(R.id.imagebutton002);
		Shang_Button = (ImageButton) findViewById(R.id.imagebutton001);
		Xia_Button = (ImageButton) findViewById(R.id.imagebutton003);
		textView = (TextView) findViewById(R.id.mediaplaytext001);
		
		// ��ʼ��ť������
		Start_Button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mediaPlayer == null) {
					startPlayVideo();// ��ʼ���ų�ʼ�ļ�
					Start_Button.setImageResource(R.drawable.zanting);// ����Ϊ��ͣͼ��
				} else if (mediaPlayer.isPlaying()) {
					pausePlayMusic();// ������ͣ����
					Start_Button.setImageResource(R.drawable.bofang);// ����Ϊ����ͼ��
				} else {
					continuePlay();// ���ü�������
					Start_Button.setImageResource(R.drawable.zanting);// ����Ϊ��ͣͼ��
				}
			}
		});

		// ��һ�װ�ť������
		Shang_Button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				playBackMusic();// ������һ�׷���
			}
		});
		
		// ��һ�װ�ť������
		Xia_Button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				playNextMusic();// ������һ�׷���
			}
		});
	}

	// ��ʼ����
	public void startPlayVideo() {
		// ��ΪcurrentFilePosition�ĳ�ʼֵ��-1�����Դ˴�ǿ�Ƹ�ֵΪ0�������ŵ�һ����Ƶ�ļ�
		current = 0;
		playMusic();

	}

	// ��������
	public void playMusic() {
		if (current >= musics.length) {// �����жϵ�ǰ���ŵ��ļ��Ƿ񳬶����б�
			current = 0; // ���ص�����ˣ���������ѭ�������б���
		}
		if (current < 0) {
			current = musics.length - 1;// ���ص����ˣ�����������ѭ�������б�
		}
		// ÿ�ο�ʼ�����б�ʱ����Ҫ��mediaPlayer�ͷŵ�������һ��׼����һ�׻�����һ��
		releaseMedia();
		try {
			mediaPlayer = new MediaPlayer().create(MediaPlayer_02.this,
					musics[current]);
			
			textView.setText(name[current]);
			
			mediaPlayer.start();// ��ʼ����
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// ��Mediaplayer�Ĳ�������¼����¼�������
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				playNextMusic(); // ��ÿ�β������֮���ò�����һ��
			}
		});
	}

	// ��ͣ
	public void pausePlayMusic() {
		if (mediaPlayer != null) {
			mediaPlayer.pause();
			// ���ò��ɻ�ȡ���㣨���ֲ�������
			textView.setFocusable(false);
		}
	}

	// ����
	public void continuePlay() {
		if (mediaPlayer != null) {
			mediaPlayer.start();
			// ���ÿ��Ի�ȡ���㣬��ʹ���ֹ���������
			textView.setFocusableInTouchMode(true);
			textView.requestFocus();
		}
	}

	// ������һ������
	public void playNextMusic() {
		current++;// ������һ��ʱ��ǰ�����ļ���1
		playMusic();
	}

	// ������һ������
	public void playBackMusic() {
		current--;// ��1��������һ��
		playMusic();
	}

	// �������ͷ�mediaPlayer���Ŷ���
	public void releaseMedia() {
		if (mediaPlayer != null) {
			try {
				mediaPlayer.release();
				mediaPlayer = null;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

}
