package com.example.t20160426e;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class ProgressDialog001 extends Activity {
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
		setContentView(R.layout.progressdialo001);
	}
	public void showSpinner(View source) {
		// ���þ�̬������ʾ���ν�����
		//��һ���������������ĸ�������ʾ
		//�ڶ�������������ʾ�ı���
		//����������������ʾ����Ϣ
		//���ĸ���������indeterminate�������ģ����Ϊtrue����߻���һ��Բ�εı�ʾ
		//�������������cancelable��������
		ProgressDialog.show(this, "����ִ����", "����ִ���У���ȴ�", true, true); 
	}
	public void showIndeterminate(View source) {
		pd1 = new ProgressDialog(ProgressDialog001.this);
		// ���öԻ���ı���
		pd1.setTitle("��������ִ����");
		// ���öԻ�����ʾ������
		pd1.setMessage("��������ִ���У�����ȴ�...");
		// ���öԻ������á�ȡ������ť�ر�
		pd1.setCancelable(true);
		// ���öԻ���Ľ��������
		pd1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		// ���öԻ���Ľ������Ƿ���ʾ����
		pd1.setIndeterminate(true);
		pd1.show();
	}

	public void showProgress(View source) {
		// ������������ɽ�������Ϊ0
		progressStatus = 0;
		// ���¿�ʼ������顣
		hasData = 0;
		pd2 = new ProgressDialog(ProgressDialog001.this);
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
					Intent intent = new Intent(ProgressDialog001.this,ProgressDialog002.class);
					startActivity(intent);
				}
				
			}
		}.start();
	}

	// ģ��һ����ʱ�Ĳ�����
	public int doWork() {
		// Ϊ����Ԫ�ظ�ֵ
		
		data[hasData++] = (int) (Math.random() * 100);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return hasData;
	}


}
