package com.example.kaoshi03;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText yhm, mm;
	final static int MAX_PROGRESS = 100;
	// 该程序模拟填充长度为100的数组
	private int[] data = new int[100];
	// 记录进度对话框的完成百分比
	int progressStatus = 0;
	int hasData = 0;
	ProgressDialog pd1, pd2;
	// 定义一个负责更新的进度的Handler
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// 表明消息是由该程序发送的。
			if (msg.what == 0x123444) {
				pd2.setProgress(progressStatus);
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void login(View v) {
		// 装载/res/linearlayout/alertdialog003.xml界面布局
		LinearLayout loginForm = (LinearLayout) getLayoutInflater().inflate(
				R.layout.activity_main2, null);
		yhm = (EditText) loginForm.findViewById(R.id.yhm);
		mm = (EditText) loginForm.findViewById(R.id.mm);
		new AlertDialog.Builder(this)
		// 设置对话框的图标
				.setIcon(R.drawable.tou)
				// 设置对话框的标题
				.setTitle("登录")
				// 设置对话框显示的View对象
				.setView(loginForm)
				// 为对话框设置一个"登录"按钮
				.setPositiveButton("登录", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// 此处可执行登录处理
						if (yhm.getText().toString().equals(null)) {
							Toast.makeText(MainActivity.this, "用户名不能为空", 1)
									.show();
						} else if (mm.getText().toString().equals(null)) {
							Toast.makeText(MainActivity.this, "密码不能为空", 1)
									.show();
						} else if (!yhm.getText().toString().equals("asd")
								|| !mm.getText().toString().equals("123")) {
							Toast.makeText(MainActivity.this, "用户名或密码错误", 1)
									.show();
						} else if (yhm.getText().toString().equals("asd")
								&& mm.getText().toString().equals("123")) {
							Toast.makeText(MainActivity.this, "登录成功", 1)
							.show();
							f();
						}

					}
				})
				// 为对话框设置一个"取消"按钮
				.setNegativeButton("取消", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// 取消登录，不做任何事情。
					}
				})
				// 创建、并显示对话框
				.create().show();
	}
	public void f(){
		
			// 将进度条的完成进度重设为0
			progressStatus = 0;
			// 重新开始填充数组。
			hasData = 0;
			pd2 = new ProgressDialog(MainActivity.this);
			pd2.setMax(MAX_PROGRESS);
			// 设置对话框的标题
			pd2.setTitle("任务完成百分比");
			// 设置对话框 显示的内容
			pd2.setMessage("耗时任务的完成百分比");
			// 设置对话框不能用“取消”按钮关闭
			pd2.setCancelable(false);
			// 设置对话框的进度条风格
			pd2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			// 设置对话框的进度条是否显示进度  false显示进度
			pd2.setIndeterminate(false);
			pd2.show();

			new Thread() {
				public void run() {
					while (progressStatus < MAX_PROGRESS) {
						// 获取耗时操作的完成百分比
						progressStatus = MAX_PROGRESS * doWork() / data.length;
						// System.out.println(MAX_PROGRESS * doWork());
						// System.out.println("data.length"+data.length);
						// System.out.println(progressStatus);
						// 发送空消息到Handler
						handler.sendEmptyMessage(0x123444);
						
					}
					// 如果任务已经完成
					if (progressStatus >= MAX_PROGRESS) {
						// 关闭对话框
						pd2.dismiss();
						Intent intent = new Intent(MainActivity.this,Secend.class);
						startActivity(intent);
					}
					
				}
			}.start();
		}

		// 模拟一个耗时的操作。
		public int doWork() {
			// 为数组元素赋值
			
			data[hasData++] = (int) (Math.random() * 10);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return hasData;
		}
	}

