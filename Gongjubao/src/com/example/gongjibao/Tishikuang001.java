package com.example.gongjibao;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Tishikuang001 extends Activity {
	Button tskbtn1, tskbtn2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tishikuang001);
		tskbtn1 = (Button) findViewById(R.id.tskbtn1);
		tskbtn2 = (Button) findViewById(R.id.tskbtn2);
		tskbtn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(Tishikuang001.this, "һ���򵥵���ʾ",
						Toast.LENGTH_SHORT).show();
			}
		});
		tskbtn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// ����һ��toast��ʾ��Ϣ
				Toast t = new Toast(Tishikuang001.this);
				// ����yoast��ʾ��λ�� x,yλƫ����
				t.setGravity(Gravity.CENTER, 0, 0);
				// ����һ��imageview
				ImageView image = new ImageView(Tishikuang001.this);
				image.setImageResource(R.drawable.ic_launcher);
				// ����һ��linearlayout
				LinearLayout ll = new LinearLayout(Tishikuang001.this);
				// ��linearlayout���view���
				ll.addView(image);
				// ����һ���ı���
				TextView tv = new TextView(Tishikuang001.this);
				tv.setText("���Ǵ�ͼƬ��");
				tv.setTextSize(25);
				tv.setTextColor(Color.GREEN);
				ll.addView(tv);
				// ����toast��ʾ�Զ����view
				t.setView(ll);
				// ����toast��ʾ��ʱ��
				t.setDuration(Toast.LENGTH_SHORT);
				t.show();

			}
		});
	}
}
