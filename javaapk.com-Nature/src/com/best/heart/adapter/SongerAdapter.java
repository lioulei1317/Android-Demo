package com.best.heart.adapter;

import java.util.List;

import com.best.heart.tool.MusicLoader;
import com.example.nature.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SongerAdapter extends BaseAdapter {
	Context context;
	List<MusicLoader.MusicInfo> items;
	public SongerAdapter(Context context, List<com.best.heart.tool.MusicLoader.MusicInfo> list) {
		this.context = context;
		this.items = list;
	}
	public SongerAdapter() {
	}
	
	public Context getContext() {
		return context;
	}
	public void setContext(Context context) {
		this.context = context;
	}
	public List<MusicLoader.MusicInfo> getItems() {
		return items;
	}
	public void setItems(List<MusicLoader.MusicInfo> items) {
		this.items = items;
	}
	@Override
	public int getCount() {
		return items.size();
	}
	@Override
	public Object getItem(int position) {
		return items.get(position);
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;
		if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(R.layout.activity_songer_list_item, null);
			vh = new ViewHolder();
			vh.songer = (TextView) convertView.findViewById(R.id.songer);
			vh.album = (TextView) convertView.findViewById(R.id.album);
			String singer = items.get(position).getArtist();
			String album = items.get(position).getAlbum();
			vh.songer.setText(singer);
			vh.album.setText(album);
		}
		return convertView;
	}
	static class ViewHolder{
		TextView songer,album;
	}

}
