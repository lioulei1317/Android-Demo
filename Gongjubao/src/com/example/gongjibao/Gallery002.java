package com.example.gongjibao;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class Gallery002 extends BaseAdapter{
	Context context;
	int[] image;

	public Gallery002(Context context, int[] image) {
		this.context = context;
		this.image = image;
	}

	@Override
	public int getCount() {
		return image.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View contenView, ViewGroup arg2) {
		if (contenView == null) {
			contenView = new ImageView(context);
		}
		contenView.setBackgroundResource(image[position]);
		return contenView;
	}
}
