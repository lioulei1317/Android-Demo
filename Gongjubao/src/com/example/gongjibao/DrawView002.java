package com.example.gongjibao;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class DrawView002 extends View {
	//��������������Ϊ��ʼ��x/y����
	public float currentX=40;
	public float currentY=50;
	//���塢��������
	Paint p=new Paint();
	
	//��xml�ж���view��Ҫ���õĹ��췽��
	//(����context��ڣ�attributeSt��view�ؼ������Ե�һ������)
	//��������Ŀ�ߡ�id��
	public DrawView002(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		p.setColor(Color.BLUE);//���û��ʵ���ɫ
		canvas.drawCircle(currentX, currentY, 15, p);//��x/y���껭һ���뾶Ϊ15px��Բ
	}
	//�ص�����ĳЩ��������ʱ�����ķ���
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// ��ǰ�����currentX��currentY
		this.currentX=event.getX();
		this.currentY=event.getY();
		//֪ͨ����ػ�
		this.invalidate();
		//����true�����������Ѿ�������¼�
		return true;
	}


}
