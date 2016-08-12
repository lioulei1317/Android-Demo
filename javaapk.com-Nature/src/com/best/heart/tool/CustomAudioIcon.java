package com.best.heart.tool;
import com.example.nature.R;
import com.example.nature.R.styleable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class CustomAudioIcon extends View implements OnTouchListener {

//	private static final String TAG = "com.example.nature.CustoAudioIcon";

	private static final int defaultType = -1;
	private static final int start = 0;
	private static final int forward = 1;
	private static final int backward = 2;
	private static final int exit = 3;
	private static final int mode = 4;
	private int type;
	private int color;
	private Paint upPaint;
	private Paint pressPaint;
	private Paint boxPaint;
	private Paint paint;
	private int width,height;	
	private boolean pressed = false;
	//只用于StartStopButton
	private boolean flagStart = true;
	//只用于ModeButton
	public static final int MODE_ONE_LOOP = 0;
	public static final int MODE_ALL_LOOP = 1;
	public static final int MODE_RANDOM = 2;
	public static final int MODE_SEQUENCE = 3; 
	private int currentMode = 3;

	public CustomAudioIcon(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.CustomAudioIcon);
		type = typedArray.getInt(R.styleable.CustomAudioIcon_type, defaultType);
		color = typedArray.getColor(R.styleable.CustomAudioIcon_color,Color.BLACK);
		typedArray.recycle();

		init();
		setClickable(true);//为了使这一View可以接受OnClickListener类
		setOnTouchListener(this);
	}

	private void init() {
		boxPaint = new Paint();
		boxPaint.setColor(color);//设置画笔颜色 
		boxPaint.setAntiAlias(true);//设置画笔的锯齿效果。 
		boxPaint.setStrokeWidth(1);//设置空心的边框宽度。 

		upPaint = new Paint();
		upPaint.setColor(Color.BLACK);
		upPaint.setAntiAlias(true);
		upPaint.setStrokeWidth(3);

		pressPaint = new Paint();
		pressPaint.setColor(Color.WHITE);
		pressPaint.setAntiAlias(true);
		pressPaint.setStrokeWidth(3);
	}

	public void onDraw(Canvas canvas) {		
		canvas.drawColor(Color.parseColor("#009E73"));
		paint = pressed ? pressPaint : upPaint;
		width = getMeasuredWidth();
		height = getMeasuredHeight();	
		
		switch (type) {
		case start:
			if(flagStart){
				drawStart(canvas, pressed);
			}else{
				drawStop(canvas, pressed);
			}
			break;	
		case forward:
			drawForward(canvas, pressed);
			break;
		case backward:
			drawBackward(canvas, pressed);
			break;
		case exit:
			drawExit(canvas, pressed);
			break;
		case mode:
			drawMode(canvas, pressed);
			break;
		}			
	}

	// public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
	// setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
	// MeasureSpec.getSize(heightMeasureSpec));
	// }

	private void drawStart(Canvas canvas, boolean pressed) {	
		float scaleWidth = width < height ? width : height;
		// 计算顶点.
		float[] verticles = { (float) (0.21 * scaleWidth),
				(float) (0.1 * scaleWidth), (float) (0.21 * scaleWidth),
				(float) (0.9 * scaleWidth), (float) (0.9 * scaleWidth),
				(float) (0.5 * scaleWidth) };
		canvas.drawLine(verticles[0], verticles[1], verticles[2], verticles[3],paint);
		canvas.drawLine(verticles[0], verticles[1], verticles[4], verticles[5],paint);
		canvas.drawLine(verticles[2], verticles[3], verticles[4], verticles[5],paint);
	}
	
	private void drawStop(Canvas canvas, boolean pressed) {			
		float scaleWidth = width < height ? width : height;
		// 计算顶点。
		float[] verticles = { (float) (0.4 * scaleWidth), (float) (0.1 * scaleWidth), 
				(float) (0.4 * scaleWidth), (float) (0.9 * scaleWidth), 
				(float) (0.6 * scaleWidth), (float) (0.1 * scaleWidth),
				(float) (0.6 * scaleWidth), (float) (0.9 * scaleWidth)};
		canvas.drawLine(verticles[0], verticles[1], verticles[2], verticles[3],paint);
		canvas.drawLine(verticles[4], verticles[5], verticles[6], verticles[7],paint);		
	}

	private void drawForward(Canvas canvas, boolean pressed) {		
		// get the shorter width or height
		int minWH = width < height ? width : height;
		float scaleWidth = (float) (minWH * 0.6);
		// 计算顶点。
		float[] verticles = { (float) (0.21 * scaleWidth),
				(float) (0.1 * scaleWidth), (float) (0.21 * scaleWidth),
				(float) (0.9 * scaleWidth), (float) (0.9 * scaleWidth),
				(float) (0.5 * scaleWidth), (float) (0.9 * scaleWidth),
				(float) (0.1 * scaleWidth), (float) (0.9 * scaleWidth),
				(float) (0.9 * scaleWidth) };
		canvas.save();
		canvas.translate((float) (minWH - scaleWidth) / 2, (float) (minWH - scaleWidth) / 2);
		// 绘制三角形
		canvas.drawLine(verticles[0], verticles[1], verticles[2], verticles[3],paint);
		canvas.drawLine(verticles[0], verticles[1], verticles[4], verticles[5],paint);
		canvas.drawLine(verticles[2], verticles[3], verticles[4], verticles[5],paint);
		// 绘制垂直线
		canvas.drawLine(verticles[6], verticles[7], verticles[8], verticles[9],paint);
		canvas.restore();
	}

	private void drawBackward(Canvas canvas, boolean pressed) {
		// get the shorter width or height
		int minWH = width < height ? width : height;
		float scaleWidth = (float) (minWH * 0.6);
		// 计算顶点。
		float[] verticles = { (float) (0.79 * scaleWidth),
				(float) (0.1 * scaleWidth), (float) (0.79 * scaleWidth),
				(float) (0.9 * scaleWidth), (float) (0.1 * scaleWidth),
				(float) (0.5 * scaleWidth), (float) (0.1 * scaleWidth),
				(float) (0.1 * scaleWidth), (float) (0.1 * scaleWidth),
				(float) (0.9 * scaleWidth) };

		canvas.save();
		canvas.translate((float) (minWH - scaleWidth) / 2, (float) (minWH - scaleWidth) / 2);
		// 绘制三角形
		canvas.drawLine(verticles[0], verticles[1], verticles[2], verticles[3],paint);
		canvas.drawLine(verticles[0], verticles[1], verticles[4], verticles[5],paint);
		canvas.drawLine(verticles[2], verticles[3], verticles[4], verticles[5],paint);
		// 绘制垂直线
		canvas.drawLine(verticles[6], verticles[7], verticles[8], verticles[9],paint);
		canvas.restore();
	}
	
	private void drawExit(Canvas canvas, boolean pressed) {		
		paint.setStyle(Style.STROKE);// 设置paint的风格为“空心”		
		// get the shorter width or height
		int minWH = width < height ? width : height;
		float scaleWidth = (float) (minWH * 0.8);
		canvas.save();
		canvas.translate((float) (0.1 * minWH), (float) (0.1 * minWH));
		canvas.drawCircle((float)(0.5 * scaleWidth), (float)(0.5 * scaleWidth), (float)(0.4 * scaleWidth), paint);
		canvas.restore();			
	}
	
	private void drawMode(Canvas canvas, boolean pressed) {				
		paint.setStyle(Style.STROKE);// 设置paint的风格为“空心”		
		// get the shorter width or height
		int minWH = width < height ? width : height;
		float scaleWidth = (float) (minWH * 0.8);
		canvas.save();		
		canvas.translate((float) (0.1 * minWH), (float) (0.1 * minWH));
		switch(currentMode){		
		case MODE_ONE_LOOP:
			canvas.drawCircle((float)(0.5 * scaleWidth), (float)(0.5 * scaleWidth), (float)(0.1 * scaleWidth), paint);
			break;
		case MODE_ALL_LOOP:
			canvas.drawCircle((float)(0.4 * scaleWidth), (float)(0.5 * scaleWidth), (float)(0.1 * scaleWidth), paint);
			canvas.drawCircle((float)(0.6 * scaleWidth), (float)(0.5 * scaleWidth), (float)(0.1 * scaleWidth), paint);
			break;
		case MODE_RANDOM:
			canvas.drawCircle((float)(0.3 * scaleWidth), (float)(0.5 * scaleWidth), (float)(0.1 * scaleWidth), paint);
			canvas.drawCircle((float)(0.5 * scaleWidth), (float)(0.5 * scaleWidth), (float)(0.1 * scaleWidth), paint);
			canvas.drawCircle((float)(0.7 * scaleWidth), (float)(0.5 * scaleWidth), (float)(0.1 * scaleWidth), paint);
			break;
		case MODE_SEQUENCE:
			canvas.drawCircle((float)(0.2 * scaleWidth), (float)(0.5 * scaleWidth), (float)(0.1 * scaleWidth), paint);
			canvas.drawCircle((float)(0.4 * scaleWidth), (float)(0.5 * scaleWidth), (float)(0.1 * scaleWidth), paint);
			canvas.drawCircle((float)(0.6 * scaleWidth), (float)(0.5 * scaleWidth), (float)(0.1 * scaleWidth), paint);
			canvas.drawCircle((float)(0.8 * scaleWidth), (float)(0.5 * scaleWidth), (float)(0.1 * scaleWidth), paint);
			break;			
		}
		canvas.restore();
	}
	
	

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			pressed = true;
			postInvalidate();
			break;
		case MotionEvent.ACTION_UP:
			pressed = false;			
			postInvalidate();
			if(type == start){
				flagStart = !flagStart;
			}
			if(type == mode){
				currentMode = (currentMode + 1) % 4;
			}
			break;
		}
		return false;
	}

	/**
	 * 如果显示启动三角形，返回true，否则返回false
	 * @return
	 */
	public boolean isStartStatus() {
		return flagStart;
	}

	/**
	 * Change the flag outside
	 * @param flagStart
	 */
	public void setFlagStart(boolean flagStart) {
		this.flagStart = flagStart;
		postInvalidate();
	}
	
	public void setCurrentMode(int changedMode){
		currentMode = changedMode;
		postInvalidate();
	}
}
