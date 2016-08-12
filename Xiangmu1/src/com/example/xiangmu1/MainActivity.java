package com.example.xiangmu1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewDebug.ExportedProperty;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends Activity {
	static final int NOTIFICATION_ID = 0x123;
	NotificationManager nm;
	int i = 0;

	Gallery fruit_gallery;
	int[] image;
	RadioGroup group1;
	RadioButton rad1;
	RadioButton rad2;
	RadioButton rad3;
	RadioButton rad4;
	Handler mHandler;
	int cut = 0;
	int k = 0;

	GridView gridView;
	ImageView image1;
	TextView gridviewtv001, gridviewtv003;
	int[] pictures = new int[] { R.drawable.liuyifei, R.drawable.liushihsi,
			R.drawable.tangyan, R.drawable.gaoyuanyuan };
	String[] wenzi = { "�����ͬ�������Ҷ�߰���ɳ̲ȹ", "�¿���ʫʫͬ��Ůװ����ɰ�ȹ��",
			"�������Ƕ�������ޱͬ������ȹ��ȹ", "��ԲԲͬ���T���������ݴ������" };
	String[] jiage = new String[4];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		for (int i = 0; i < jiage.length; i++) {
			int suijishu = (int) (Math.random() * 2000 + 100);
			jiage[i] = "��" + suijishu;
		}
		// ��ȡϵͳNotificationManager����
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		group1 = (RadioGroup) findViewById(R.id.group1);
		rad1 = (RadioButton) findViewById(R.id.rad1);
		rad2 = (RadioButton) findViewById(R.id.rad2);
		rad3 = (RadioButton) findViewById(R.id.rad3);
		rad4 = (RadioButton) findViewById(R.id.rad4);
		fruit_gallery = (Gallery) findViewById(R.id.fruit_gallery);
		image = new int[] { R.drawable.liuyifei, R.drawable.liushihsi,
				R.drawable.tangyan, R.drawable.gaoyuanyuan };
		TestAdapter adapter = new TestAdapter(this, image);
		fruit_gallery.setAdapter(adapter);
		fruit_gallery.setOnItemSelectedListener(listener);
		group1.setOnCheckedChangeListener(change);
		gridView = (GridView) findViewById(R.id.gridview);
		image1 = (ImageView) findViewById(R.id.good_image);
		gridviewtv001 = (TextView) findViewById(R.id.gridviewTv002);
		gridviewtv003 = (TextView) findViewById(R.id.gridviewTv003);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < pictures.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pictures", pictures[i]);
			map.put("wenzi", wenzi[i]);
			map.put("jiage", jiage[i]);
			list.add(map);
		}
		SimpleAdapter adapter2 = new SimpleAdapter(this, list,
				R.layout.grid_view002, new String[] { "pictures", "wenzi",
						"jiage" }, new int[] { R.id.good_image,
						R.id.gridviewTv002, R.id.gridviewTv003 });
		gridView.setAdapter(adapter2);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				k = position;
				// TODO Auto-generated method stub
				// image1.setImageResource(pictures[position]);
				// gridviewtv001.setText(wenzi[position]);
				f();

			}
		});

		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if (msg.what == 0x123) {
					if (++cut < image.length) {
						fruit_gallery.setSelection(cut);
					} else {
						cut = 0;
						fruit_gallery.setSelection(cut);
					}
				}
			}
		};
		/*
		 * Timer����ִ��һЩ�򵥵Ķ�ʱ������ TimerTask Ҫִ�е�����
		 * ����һ��Timerʵ����ͨ���ṩ��schedule����������TimerTask���뵽��ʱ��Timer��
		 */
		// �ӳ٣���0�뿪ʼ���У�ÿ��2��runһ��
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				mHandler.sendEmptyMessage(0x123);

			}
		}, 0, 2000);

	}

	public void send(View source) {
		if (i % 2 == 0) {
			Intent intent = new Intent(MainActivity.this, MainActivity2.class);
			// pendingIntent��һ�������Intent��
			// ��Ҫ����������Intent��ִ�����̵ģ�
			// ��pendingIntent��ִ�в������̵ġ�
			// pendingIntentִ�еĲ���ʵ�����ǲ�����������Intent�Ĳ�����
			// ����ʹ��pendingIntent��Ŀ������
			// ����������Intent�Ĳ�����ִ������Ҫ����ĳЩ�����ġ�
			PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0,
					intent, 0);
			Notification notify = new Notification.Builder(this)
			// ���ô�֪ͨ����֪ͨ��ʧ
					.setAutoCancel(true)
					// ������ʾ��״̬����֪ͨ��ʾ��Ϣ
					.setTicker("������Ϣ��")
					// ����֪ͨͼ��
					.setSmallIcon(R.drawable.meinv2)
					// ����֪ͨ����
					.setContentTitle("����һ���µ���Ϣ")
					// ����֪ͨ����
					.setContentText("��������")
					// // ����ʹ��ϵͳĬ�ϵ�������Ĭ��LED��
					.setDefaults(
							Notification.DEFAULT_SOUND
									| Notification.DEFAULT_LIGHTS)
					// ����֪ͨ���Զ�������
					// .setSound(Uri.parse("android.resource://com.example.t20160426c/"
					// + R.raw.msg))
					.setContentIntent(pi).build();
			// ����֪ͨ
			nm.notify(NOTIFICATION_ID, notify);
		} else {
			nm.cancel(NOTIFICATION_ID);
		}
		i++;

	}

	OnItemSelectedListener listener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1,
				int position, long arg3) {
			int id = R.id.rad1;
			switch (position) {
			case 0:
				id = R.id.rad1;
				break;
			case 1:
				id = R.id.rad2;
				break;
			case 2:
				id = R.id.rad3;
				break;
			case 3:
				id = R.id.rad4;
				break;
			}
			group1.check(id);

		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
	};

	OnCheckedChangeListener change = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			int position = 0;
			if (rad1.isChecked()) {
				position = 0;
				fruit_gallery.setSelection(position);
			} else if (rad2.isChecked()) {
				position = 1;
				fruit_gallery.setSelection(position);
			} else if (rad3.isChecked()) {
				position = 2;
				fruit_gallery.setSelection(position);
			} else if (rad4.isChecked()) {
				position = 3;
				fruit_gallery.setSelection(position);
			}
		}
	};

	private void f() {
		LinearLayout loginForm = (LinearLayout) getLayoutInflater().inflate(
				R.layout.grid_view002, null);
		ImageView imageview = (ImageView) loginForm
				.findViewById(R.id.good_image);
		TextView tv1 = (TextView) loginForm.findViewById(R.id.gridviewTv002);
		TextView tv2 = (TextView) loginForm.findViewById(R.id.gridviewTv003);
		AlertDialog.Builder builder = new AlertDialog.Builder(this)
		// ���öԻ������
				.setTitle("��Ϣ")
				// ����ͼ��
				.setIcon(R.drawable.ic_launcher).setView(loginForm);

		builder.setPositiveButton("ȷ��", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		}).create().show();
		imageview.setImageResource(pictures[k]);
		tv1.setText(wenzi[k]);
		tv2.setText(jiage[k]);
	}

}
class other extends TextView{

	public other(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	@Override
	@ExportedProperty(category = "focus")
	public boolean isFocused() {
		// TODO Auto-generated method stub
		return true;
	}
	 
}