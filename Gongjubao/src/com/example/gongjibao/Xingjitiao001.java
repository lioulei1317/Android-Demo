package com.example.gongjibao;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class Xingjitiao001 extends Activity {
	RatingBar rb1;
	ImageView image;
	SeekBar see;
	Button bn1, bn2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xingjitiao001);
		image = (ImageView) findViewById(R.id.image);
		see = (SeekBar) findViewById(R.id.seekbar);
		rb1 = (RatingBar) findViewById(R.id.rb1);
		rb1.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {

			@Override
			public void onRatingChanged(RatingBar arg0, float arg1, boolean arg2) {
				// arg1当前默认星级
				image.setAlpha((int) arg1 * 255 / 5);

			}
		});
		see.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekbar, int arg1,
					boolean arg2) {
				// arg1进度，arg是否拖动
				image.setAlpha(arg1);

			}
		});
	}
}
