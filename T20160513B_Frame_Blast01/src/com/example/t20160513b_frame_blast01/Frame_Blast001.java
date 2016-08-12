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
		// ʹ��FrameLayout���ֹ�����������������Լ������Լ�
		RelativeLayout relative = new RelativeLayout(this);
		setContentView(relative);
		// ����ʹ�ñ���
		relative.setBackgroundResource(R.drawable.back);
		// ������Ч
		bomb = MediaPlayer.create(this, R.raw.bomb);
		myView = new MyView(this);
		//����MyView������ʾblast����
		myView.setBackgroundResource(R.drawable.blast);
		// ����myViewĬ��Ϊ����
		myView.setVisibility(View.INVISIBLE);
		// ��ȡ��������
		anim = (AnimationDrawable) myView.getBackground();
		relative.addView(myView);
		relative.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// ֻ�������¼�������ÿ�β�����������Ч����
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					// ��ֹͣ��������
					anim.stop();
					float x = event.getX();
					float y = event.getY();
					// ����myView����ʾλ��
					myView.setLocation((int) y - 40, (int) x - 20);
					myView.setVisibility(View.VISIBLE);
					// ��������
					anim.start();
					// ������Ч
					bomb.start();
				}
				return false;
			}
		});
	}

	// ����һ���Զ���View�����Զ���View���ڲ��š���ը��Ч��
	class MyView extends ImageView {

		public MyView(Context context) {
			super(context);
		}

		// ����һ���������÷������ڿ���View����ʾλ��
		public void setLocation(int top, int left) {
			this.setFrame(left, top, left + 40, top + 40);
		}

		// ��д�÷�������������������ŵ����һ֡ʱ�����ظ�View
		@Override
		protected void onDraw(Canvas canvas) {// ��
			// TODO Auto-generated method stub
			super.onDraw(canvas);
			try {
				// mCurFrame�������浱ǰ�����Ĳ���λ�á�����mCurFrame��˽��(private)����,�޷�ͨ��������ʽ���������з��ʸñ�����
				// ��Ȼͨ��������ʽ�޷����ʸñ���������Ȼ����ͨ��java���似������дprivate����
				// ���mCurFrame������Field����
				Field field = AnimationDrawable.class
						.getDeclaredField("mCurFrame");
				// ��mCurFrame���óɿɷ���״̬
				field.setAccessible(true);
				// ��ȡanim�����ĵ�ǰ֡
				int curFrame = field.getInt(anim);
				// ����Ѿ��������һ֡
				if (curFrame == anim.getNumberOfFrames() - 1) {
					//�ø�View����
					setVisibility(View.INVISIBLE);

				}

			} catch (Exception e) {
				// TODO: handle exception
			}
			super.onDraw(canvas);
		}

	}

}
