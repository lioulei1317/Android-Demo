package cn.com.tarena.coreandroid.d0901.myinterface;

public interface IMusicPlay {
	void play();

	void playSeekTo(int progress);

	void playNext();

	void playPrevious();

	void stop();

	int getProgress();
	int getAudioSessionId();
}
