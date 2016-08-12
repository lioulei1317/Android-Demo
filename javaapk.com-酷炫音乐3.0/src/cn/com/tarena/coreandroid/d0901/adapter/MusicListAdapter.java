package cn.com.tarena.coreandroid.d0901.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import cn.com.tarena.coreandroid.d0901.R;
import cn.com.tarena.coreandroid.d0901.entity.Music;
import cn.com.tarena.coreandroid.d0901.utils.TextFormatter;

public class MusicListAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<Music> musics;
	private int musicId = 0;

	public MusicListAdapter(Context context, ArrayList<Music> musics) {
		this.context = context;
		setMusics(musics);
	}

	public int getMusicId() {
		return musicId;
	}

	public void setMusicId(int musicId) {
		this.musicId = musicId;
	}

	/**
	 * 自定义设置数据源，避免数据源为null
	 * 
	 * @param musics
	 *            数据源
	 */
	public void setMusics(ArrayList<Music> musics) {
		if (musics == null) {
			this.musics = new ArrayList<Music>();
		} else {
			this.musics = musics;
		}
	}

	@Override
	public int getCount() {
		return musics.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			convertView = View.inflate(context, R.layout.music_list_item, null);
			holder = new ViewHolder();
			holder.tvTitle = (TextView) convertView
					.findViewById(R.id.tv_item_title);
			holder.tvDuration = (TextView) convertView
					.findViewById(R.id.tv_item_duration);
			holder.tvArtist = (TextView) convertView
					.findViewById(R.id.tv_item_artist);
			holder.tvAlbum = (TextView) convertView
					.findViewById(R.id.tv_item_album);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Music music = musics.get(position);
		if (position == this.musicId) {
			convertView.setBackgroundColor(0x7081D0Eb);
		} else {
			convertView.setBackgroundColor(0);
		}

		holder.tvTitle.setText(music.getTitle());
		holder.tvDuration.setText(TextFormatter.getMusicTime(music
				.getDuration()));
		holder.tvArtist.setText(music.getArtist());
		holder.tvAlbum.setText(music.getAlbum());
		return convertView;
	}

	private class ViewHolder {
		public TextView tvTitle, tvDuration, tvArtist, tvAlbum;
	
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {

		return musicId;
	}

}
