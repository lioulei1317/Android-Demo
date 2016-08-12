package com.example.gongjibao;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class DrawView002 extends View {
	//定义两个参数作为起始的x/y坐标
	public float currentX=40;
	public float currentY=50;
	//定义、创建画笔
	Paint p=new Paint();
	
	//在xml中定义view需要调用的构造方法
	//(参数context入口，attributeSt是view控件的属性的一个集合)
	//包括定义的宽高、id等
	public DrawView002(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		p.setColor(Color.BLUE);//设置画笔的颜色
		canvas.drawCircle(currentX, currentY, 15, p);//以x/y坐标画一个半径为15px的圆
	}
	//回调就是某些条件发生时出发的方法
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// 当前组件的currentX和currentY
		this.currentX=event.getX();
		this.currentY=event.getY();
		//通知组件重绘
		this.invalidate();
		//返回true表明处理方法已经处理该事件
		return true;
	}


}
