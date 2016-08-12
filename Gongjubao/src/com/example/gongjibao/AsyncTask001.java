package com.example.gongjibao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AsyncTask001 extends Activity {
	TextView show;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asynctask001);
		show = (TextView) findViewById(R.id.asyshowtv001);
	}

	// ��д�÷�����Ϊ����İ�ť�ṩ�¼���Ӧ����
	public void download(View source) throws MalformedURLException {
		DownloadTask task = new DownloadTask(this);
		// URLָ�����������Դ(��ַ)
		task.execute(new URL("http://www.17173.com/"));
	}
	

	// ��һ����������������ִ�е��������������
	// �ڶ�����������̨������ɵĽ���ֵ������
	// ��������������ִ̨��������ɺ󷵻ؽ��������
	class DownloadTask extends AsyncTask<URL, Integer, String> {
		// �ɱ䳤�������������AsyncTask.exucute()��Ӧ
		ProgressDialog pdialog;
		// �����¼�Ѿ���ȡ��
		int hasRead = 0;
		Context mContext;

		public DownloadTask(Context ctx) {
			mContext = ctx;
		}

		// 1
		// �÷�������ִ�к�̨��ʱ����ǰ������
		// ͨ���÷����������һЩ��ʼ����׼������
		// �����ڽ�������ʾ������
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pdialog = new ProgressDialog(mContext);
			// ���öԻ���ı���
			pdialog.setTitle("��������ִ����");
			// ���öԻ�����ʾ����
			pdialog.setMessage("��������ִ���У�����ȴ�...");
			// ���öԻ����ܱ���ȡ����
			pdialog.setCancelable(false);
			// ���ý��������ֵ
			pdialog.setMax(1190);
			// ���öԻ���Ľ��������
			pdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			// ���öԻ����Ƿ���ʾ����
			pdialog.setIndeterminate(false);
			pdialog.show();
		}

		// 2
		// ��̨�߳̽�Ҫ��ɵ�����
		@Override
		protected String doInBackground(URL... params) {
			StringBuilder sb = new StringBuilder();
			try {
				// URLConnection
				// ��ʾ��URL֮��ͨ�ŵ����ӣ��������ͨ��URLConnection��ʵ��
				// ��URL���������ȡURL���õ���Դ
				// ͨ������URL�����openConnection()������URLConnection
				URLConnection conn = params[0].openConnection();
				// ��conn���Ӷ�Ӧ������������������װ��BufferedReader
				// getInputStream():���ظ�URLConnection��Ӧ��������
				// ���ڻ�ȡURLConnection��Ӧ������
				BufferedReader br = new BufferedReader(new InputStreamReader(
						conn.getInputStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
					hasRead++;
					// 3
					// �������ִ�н���
					publishProgress(hasRead);
					// ÿִ��һ��3�������ͻص�һ��4����

				}
				return sb.toString();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		// 4
		// ��doInBackground()�����е���publishProgress()
		// �������������ִ�н��Ⱥ󣬽��ᴥ���÷���
		@Override
		protected void onProgressUpdate(Integer... values) {
			// ���½���
			show.setText("�Ѿ���ȡ�ˡ�" + values[0] + "���У�");
			pdialog.setProgress(values[0]);
		}

		// 5
		// ��doInBackground()��ɺ�ϵͳ���Զ�����onPostExecute()����
		// ����doInBackground()�����ķ���ֵ�����÷���
		@Override
		protected void onPostExecute(String result) {
			// ����HTMLҳ�������
			show.setText(result);
			pdialog.dismiss();

		}

	}
}
