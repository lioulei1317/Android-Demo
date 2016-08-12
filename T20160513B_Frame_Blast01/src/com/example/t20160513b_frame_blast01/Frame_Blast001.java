package com.example.t20160513b_frame_blast01;

import java.lang.reflect.Field;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Frame_Blast001 extends Activity {
	private MyView myView;
	private AnimationDrawable anim;
	private MediaPlayer bomb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_blast001);
		// 使用FrameLayout布局管理器，它允许组件自己控制自己
		RelativeLayout relative = new RelativeLayout(this);
		setContentView(relative);
		// 设置使用背景
		relative.setBackgroundResource(R.drawable.back);
		// 加载音效
		bomb = MediaPlayer.create(this, R.raw.bomb);
		myView = new MyView(this);
		//设置MyView用于显示blast动画
		myView.setBackgroundResource(R.drawable.blast);
		// 设置myView默认为隐藏
		myView.setVisibility(View.INVISIBLE);
		// 获取动画对象
		anim = (AnimationDrawable) myView.getBackground();
		relative.addView(myView);
		relative.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// 只处理按下事件（避免每次产生两个动画效果）
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					// 先停止动画播放
					anim.stop();
					float x = event.getX();
					float y = event.getY();
					// 控制myView的显示位置
					myView.setLocation((int) y - 40, (int) x - 20);
					myView.setVisibility(View.VISIBLE);
					// 启动动画
					anim.start();
					// 播放音效
					bomb.start();
				}
				return false;
			}
		});
	}

	// 定义一个自定义View，该自动以View用于播放“爆炸”效果
	class MyView extends ImageView {

		public MyView(Context context) {
			super(context);
		}

		// 定义一个方法，该方法用于控制View的显示位置
		public void setLocation(int top, int left) {
			this.setFrame(left, top, left + 40, top + 40);
		}

		// 重写该方法，控制如果动画播放到最后一帧时，隐藏该View
		@Override
		protected void onDraw(Canvas canvas) {// ①
			// TODO Auto-generated method stub
			super.onDraw(canvas);
			try {
				// mCurFrame变量保存当前动画的播放位置。但是mCurFrame是私有(private)变量,无法通过正常方式在其他类中访问该变量。
				// 虽然通过正常方式无法访问该变量，但仍然可以通过java放射技术来读写private变量
				// 获得mCurFrame变量的Field对象
				Field field = AnimationDrawable.class
						.getDeclaredField("mCurFrame");
				// 将mCurFrame设置成可访问状态
				field.setAccessible(true);
				// 获取anim动画的当前帧
				int curFrame = field.getInt(anim);
				// 如果已经到了最后一帧
				if (curFrame == anim.getNumberOfFrames() - 1) {
					//让该View隐藏
					setVisibility(View.INVISIBLE);

				}

			} catch (Exception e) {
				// TODO: handle exception
			}
			super.onDraw(canvas);
		}

	}

}
