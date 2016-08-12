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
	// �ó���ģ����䳤��Ϊ100������
	private int[] data = new int[100];
	// ��¼���ȶԻ������ɰٷֱ�
	int progressStatus = 0;
	int hasData = 0;
	ProgressDialog pd1, pd2;
	// ����һ��������µĽ��ȵ�Handler
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// ������Ϣ���ɸó����͵ġ�
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
		// װ��/res/linearlayout/alertdialog003.xml���沼��
		LinearLayout loginForm = (LinearLayout) getLayoutInflater().inflate(
				R.layout.activity_main2, null);
		yhm = (EditText) loginForm.findViewById(R.id.yhm);
		mm = (EditText) loginForm.findViewById(R.id.mm);
		new AlertDialog.Builder(this)
		// ���öԻ����ͼ��
				.setIcon(R.drawable.tou)
				// ���öԻ���ı���
				.setTitle("��¼")
				// ���öԻ�����ʾ��View����
				.setView(loginForm)
				// Ϊ�Ի�������һ��"��¼"��ť
				.setPositiveButton("��¼", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// �˴���ִ�е�¼����
						if (yhm.getText().toString().equals(null)) {
							Toast.makeText(MainActivity.this, "�û�������Ϊ��", 1)
									.show();
						} else if (mm.getText().toString().equals(null)) {
							Toast.makeText(MainActivity.this, "���벻��Ϊ��", 1)
									.show();
						} else if (!yhm.getText().toString().equals("asd")
								|| !mm.getText().toString().equals("123")) {
							Toast.makeText(MainActivity.this, "�û������������", 1)
									.show();
						} else if (yhm.getText().toString().equals("asd")
								&& mm.getText().toString().equals("123")) {
							Toast.makeText(MainActivity.this, "��¼�ɹ�", 1)
							.show();
							f();
						}

					}
				})
				// Ϊ�Ի�������һ��"ȡ��"��ť
				.setNegativeButton("ȡ��", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// ȡ����¼�������κ����顣
					}
				})
				// ����������ʾ�Ի���
				.create().show();
	}
	public void f(){
		
			// ������������ɽ�������Ϊ0
			progressStatus = 0;
			// ���¿�ʼ������顣
			hasData = 0;
			pd2 = new ProgressDialog(MainActivity.this);
			pd2.setMax(MAX_PROGRESS);
			// ���öԻ���ı���
			pd2.setTitle("������ɰٷֱ�");
			// ���öԻ��� ��ʾ������
			pd2.setMessage("��ʱ�������ɰٷֱ�");
			// ���öԻ������á�ȡ������ť�ر�
			pd2.setCancelable(false);
			// ���öԻ���Ľ��������
			pd2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			// ���öԻ���Ľ������Ƿ���ʾ����  false��ʾ����
			pd2.setIndeterminate(false);
			pd2.show();

			new Thread() {
				public void run() {
					while (progressStatus < MAX_PROGRESS) {
						// ��ȡ��ʱ��������ɰٷֱ�
						progressStatus = MAX_PROGRESS * doWork() / data.length;
						// System.out.println(MAX_PROGRESS * doWork());
						// System.out.println("data.length"+data.length);
						// System.out.println(progressStatus);
						// ���Ϳ���Ϣ��Handler
						handler.sendEmptyMessage(0x123444);
						
					}
					// ��������Ѿ����
					if (progressStatus >= MAX_PROGRESS) {
						// �رնԻ���
						pd2.dismiss();
						Intent intent = new Intent(MainActivity.this,Secend.class);
						startActivity(intent);
					}
					
				}
			}.start();
		}

		// ģ��һ����ʱ�Ĳ�����
		public int doWork() {
			// Ϊ����Ԫ�ظ�ֵ
			
			data[hasData++] = (int) (Math.random() * 10);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return hasData;
		}
	}

