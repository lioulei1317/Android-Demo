package cn.com.tarena.coreandroid.d0901.dao;

import java.util.ArrayList;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Audio.Media;
import android.util.Log;
import cn.com.tarena.coreandroid.d0901.entity.Music;

/**
 * 歌曲的数据源操作类
 * 
 * @author tarena
 * 
 */
public class MusicDao {
	private Context context;

	public MusicDao(Context context) {
		this.context = context;
	}

	public ArrayList<Music> getMusicList() {
		ArrayList<Music> musics = null;
		Music music;
		ContentResolver cr;
		String[] projection;
		Cursor cursor;
		Uri uri;

		uri = Media.EXTERNAL_CONTENT_URI;
		projection = new String[] { Media._ID, Media.DATA, Media.TITLE,
				Media.DURATION, Media.ARTIST, Media.ALBUM };
		cr = context.getContentResolver();
		cursor = cr.query(uri, projection, null, null, null);

		if (cursor != null && cursor.getCount() > 0) {
			musics = new ArrayList<Music>();
			while (cursor.moveToNext()) {
				long long1 = cursor.getLong(cursor.getColumnIndex(Media.DURATION));
				if(long1>30000){
				music = new Music();
					
					music.setId(cursor.getLong(cursor.getColumnIndex(Media._ID)));
					music.setDuration(cursor.getLong(cursor
							.getColumnIndex(Media.DURATION)));
					Log.i("DURATION",long1+"long1");
					music.setData(cursor.getString(cursor
							.getColumnIndex(Media.DATA)));
					music.setTitle(cursor.getString(cursor
							.getColumnIndex(Media.TITLE)));
					music.setArtist(cursor.getString(cursor
							.getColumnIndex(Media.ARTIST)));
					music.setAlbum(cursor.getString(cursor
							.getColumnIndex(Media.ALBUM)));
					musics.add(music);
					music = null;
			 }

			}
			
			cursor.close();
		}
		
		// 以下是Log输出获取数据的结果
		Log.d("", "MusicDao.getMusicList(), musics = " + musics);
		if(musics != null) {
			for (Music m : musics) {
				Log.d("", "MusicDao.getMusicList(), music = " + m.toString());
			}
		}
		return musics;
	}
}
