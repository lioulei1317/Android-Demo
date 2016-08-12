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
	String[] wenzi = { "刘亦菲同款无袖荷叶边百褶沙滩裙", "新款刘诗诗同款女装夏天可爱裙子",
			"唐嫣我是杜拉拉戚薇同款连衣裙套裙", "高圆圆同款纯棉T恤宽松显瘦打底上衣" };
	String[] jiage = new String[4];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		for (int i = 0; i < jiage.length; i++) {
			int suijishu = (int) (Math.random() * 2000 + 100);
			jiage[i] = "￥" + suijishu;
		}
		// 获取系统NotificationManager服务
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
		 * Timer用来执行一些简单的定时器任务 TimerTask 要执行的任务
		 * 创建一个Timer实例，通过提供的schedule（）方法将TimerTask加入到定时器Timer中
		 */
		// 延迟，从0秒开始运行，每个2秒run一次
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
			// pendingIntent是一种特殊的Intent。
			// 主要的区别在于Intent的执行立刻的，
			// 而pendingIntent的执行不是立刻的。
			// pendingIntent执行的操作实质上是参数传进来的Intent的操作，
			// 但是使用pendingIntent的目的在于
			// 它所包含的Intent的操作的执行是需要满足某些条件的。
			PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0,
					intent, 0);
			Notification notify = new Notification.Builder(this)
			// 设置打开通知，该通知消失
					.setAutoCancel(true)
					// 设置显示在状态栏的通知提示消息
					.setTicker("有新消息！")
					// 设置通知图标
					.setSmallIcon(R.drawable.meinv2)
					// 设置通知标题
					.setContentTitle("您有一条新的消息")
					// 设置通知内容
					.setContentText("福利来了")
					// // 设置使用系统默认的声音、默认LED灯
					.setDefaults(
							Notification.DEFAULT_SOUND
									| Notification.DEFAULT_LIGHTS)
					// 设置通知的自定义声音
					// .setSound(Uri.parse("android.resource://com.example.t20160426c/"
					// + R.raw.msg))
					.setContentIntent(pi).build();
			// 发送通知
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
		// 设置对话框标题
				.setTitle("信息")
				// 设置图标
				.setIcon(R.drawable.ic_launcher).setView(loginForm);

		builder.setPositiveButton("确定", new OnClickListener() {

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