package com.jiayonghua.android;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter
{
	private Context context;
	private ArrayList<ImageBean> images;
	private LayoutInflater inflater;
	
	public ImageAdapter(Context context,ArrayList<ImageBean> images) {
		this.context = context;
		inflater = LayoutInflater.from(context);
		this.images = images;
	}
	
	@Override
	public int getCount()
	{
		return images.size();
	}
	
	@Override
	public Object getItem(int position)
	{
		return images.get(position);
	}
	
	@Override
	public long getItemId(int position)
	{
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view = inflater.inflate(R.layout.gallery_item, null);
		ImageView image = (ImageView)view;
		image.setImageResource(images.get(position).getIconId());
		image.setAdjustViewBounds(true);
		return image;
	}
	
}
