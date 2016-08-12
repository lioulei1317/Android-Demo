package cn.com.tarena.coreandroid.d0901.utils;

public interface ApplicationConsts {
	// 以下是Activity发出的广播

	/**
	 * Activity发出的，播放按钮被点击
	 */
	String ACTIVITY_PLAY_BUTTON_CLICK = "tarena.intent.action.PLAY_BUTTON_CLICK";
	String ACTIVITY_PREVIOUS_BUTTON_CLICK = "XXXXXXXXXX";
	String ACTIVITY_NEXT_BUTTON_CLICK = "YYYYYYYYYYY";
	String ACTIVITY_MUSIC_INDEX_CHANGED = "AAAAAAAAAAAA";
	String ACTIVITY_SEEKBAR_CHANGED = "11111111111";
	String ACTIVITY_START_MAIN = "android.intent.action.MAIN";
	
	
	// 以下是Service发出的广播

	/**
	 * Service发出的，播放歌曲
	 */
	String SERVICE_PLAYER_PLAY = "tarena.intent.action.PLAYER_PLAY";
	/**
	 * Service发出的，暂停播放歌曲
	 */
	String SERVICE_PLAYER_PAUSE = "tarena.intent.action.PLYAER_PAUSE";
	/**
	 * Service发出的，更新进度播放歌曲
	 */
	String SERVICE_UPDATE_PROGRESS = "8888888888";
}
