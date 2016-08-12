package com.example.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GalleryAdapter extends BaseAdapter {
	Context context;
	int[] image;

	public GalleryAdapter(Context context, int[] image) {
		this.context = context;
		this.image = image;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return image.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = new ImageView(context);
		}
		convertView.setBackgroundResource(image[position]);
		return convertView;
	}

}
