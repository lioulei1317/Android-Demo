package com.example.t20160415b;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button bn1,bn2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bn1=(Button) findViewById(R.id.bn1);
		bn2=(Button) findViewById(R.id.bn2);
		bn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "һ���򵥵���ʾ", Toast.LENGTH_SHORT).show();
			}
		});
		bn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//����һ��toast��ʾ��Ϣ
				Toast t=new Toast(MainActivity.this);
				//����Toast��ʾ��λ��  x,yλƫ����
				t.setGravity(Gravity.CENTER, 0, 0);
				//����һ��imageview
				ImageView image=new ImageView(MainActivity.this);
				image.setImageResource(R.drawable.ic_launcher);
				//����һ��linearlayout
				LinearLayout ll=new LinearLayout(MainActivity.this);
				//��linearlayout���view���
				ll.addView(image);
				//����һ���ı���
				TextView tv=new TextView(MainActivity.this);
				tv.setText("���Ǵ�ͼƬ��");
				tv.setTextSize(25);
				tv.setTextColor(Color.GREEN);
				ll.addView(tv);
				//����toast��ʾ�Զ����view
				t.setView(ll);
				//����toast��ʾ��ʱ��
				t.setDuration(Toast.LENGTH_SHORT);
				t.show();
				
			}
		});
	}

}
