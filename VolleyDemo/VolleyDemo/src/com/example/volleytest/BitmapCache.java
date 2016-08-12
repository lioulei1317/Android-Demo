package com.example.volleytest;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import com.android.volley.toolbox.ImageLoader.ImageCache;

public class BitmapCache implements ImageCache {

	// LruCache是基于内存的缓存类
	private LruCache<String, Bitmap> lruCache;
	// LruCache的最大缓存大小
	private int max = 10 * 1024 * 1024;

	public BitmapCache() {
		lruCache = new LruCache<String, Bitmap>(max) {
			@Override
			// 缓存图片的大小
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
