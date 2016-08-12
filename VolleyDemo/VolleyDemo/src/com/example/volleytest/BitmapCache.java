package com.example.volleytest;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import com.android.volley.toolbox.ImageLoader.ImageCache;

public class BitmapCache implements ImageCache {

	// LruCache�ǻ����ڴ�Ļ�����
	private LruCache<String, Bitmap> lruCache;
	// LruCache����󻺴��С
	private int max = 10 * 1024 * 1024;

	public BitmapCache() {
		lruCache = new LruCache<String, Bitmap>(max) {
			@Override
			// ����ͼƬ�Ĵ�С
			protected int sizeOf(String key, Bitmap value) {
				return value.getRowBytes() * value.getHeight();
			}
		};
	}

	@Override
	public Bitmap getBitmap(String url) {
		return lruCache.get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		lruCache.put(url, bitmap);
	}

}
