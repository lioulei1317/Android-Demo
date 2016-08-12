package cn.com.tarena.coreandroid.d0901.application;

import java.util.ArrayList;

import android.app.Application;
import android.util.Log;
import cn.com.tarena.coreandroid.d0901.dao.MusicDao;
import cn.com.tarena.coreandroid.d0901.entity.Music;

public class PlayMusicApplication extends Application {
	/**
	 * 歌曲的List集合
	 */
	private ArrayList<Music> musics;
	/**
	 * 当前播放的歌曲
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
		// 获取歌曲的List集合
		musics = new MusicDao(this).getMusicList();
		super.onCreate();
	}

	/**
	 * 获取歌曲的List集合
	 * 
	 * @return 歌曲的List集合
	 */
	public ArrayList<Music> getMusicList() {
		return this.musics;
	}

	/**
	 * 获取当前正在播放的歌曲的索引
	 * 
	 * @return 当前正在播放的歌曲的索引
	 */
	public int getCurrentMusicIndex() {
		return currentMusicIndex;
	}

	/**
	 * 设置当前播放的歌曲的索引
	 * 
	 * @param currentMusicIndex
	 *            当前播放的歌曲的索引
	 */
	public void setCurrentMusicIndex(int currentMusicIndex) {
		this.currentMusicIndex = currentMusicIndex;
	}
}
