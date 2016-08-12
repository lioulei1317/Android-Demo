package cn.com.tarena.coreandroid.d0901.application;

import java.util.ArrayList;

import android.app.Application;
import android.util.Log;
import cn.com.tarena.coreandroid.d0901.dao.MusicDao;
import cn.com.tarena.coreandroid.d0901.entity.Music;

public class PlayMusicApplication extends Application {
	/**
	 * ������List����
	 */
	private ArrayList<Music> musics;
	/**
	 * ��ǰ���ŵĸ���
	 */
	private int currentMusicIndex=0;
	private int AudioSessionId;
	public int getAudioSessionId() {
		return AudioSessionId;
	}

	public void setAudioSessionId(int audioSessionId) {
		AudioSessionId = audioSessionId;
	}

	@Override
	public void onCreate() {
		Log.d("", "PlayMusicApplication.onCreate()");
		// ��ȡ������List����
		musics = new MusicDao(this).getMusicList();
		super.onCreate();
	}

	/**
	 * ��ȡ������List����
	 * 
	 * @return ������List����
	 */
	public ArrayList<Music> getMusicList() {
		return this.musics;
	}

	/**
	 * ��ȡ��ǰ���ڲ��ŵĸ���������
	 * 
	 * @return ��ǰ���ڲ��ŵĸ���������
	 */
	public int getCurrentMusicIndex() {
		return currentMusicIndex;
	}

	/**
	 * ���õ�ǰ���ŵĸ���������
	 * 
	 * @param currentMusicIndex
	 *            ��ǰ���ŵĸ���������
	 */
	public void setCurrentMusicIndex(int currentMusicIndex) {
		this.currentMusicIndex = currentMusicIndex;
	}
}
