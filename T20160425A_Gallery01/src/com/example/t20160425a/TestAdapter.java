package com.example.t20160425a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class TestAdapter extends BaseAdapter{
	Context context;
	int[] image;

	public TestAdapter(Context context, int[] image) {
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
