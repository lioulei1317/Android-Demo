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

	// 重写该方法，为界面的按钮提供事件响应方法
	public void download(View source) throws MalformedURLException {
		DownloadTask task = new DownloadTask(this);
		// URL指向的是网络资源(网址)
		task.execute(new URL("http://www.17173.com/"));
	}
	

	// 第一个参数：启动任务执行的输入参数的类型
	// 第二个参数：后台任务完成的进度值的类型
	// 第三个参数：后台执行任务完成后返回结果的类型
	class DownloadTask extends AsyncTask<URL, Integer, String> {
		// 可变长的输入参数，与AsyncTask.exucute()对应
		ProgressDialog pdialog;
		// 定义记录已经读取的
		int hasRead = 0;
		Context mContext;

		public DownloadTask(Context ctx) {
			mContext = ctx;
		}

		// 1
		// 该方法将在执行后台耗时操作前被调用
		// 通常该方法用于完成一些初始化的准备工作
		// 比如在界面上显示进度条
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pdialog = new ProgressDialog(mContext);
			// 设置对话框的标题
			pdialog.setTitle("任务正在执行中");
			// 设置对话框显示内容
			pdialog.setMessage("任务正在执行中，敬请等待...");
			// 设置对话框不能被“取消”
			pdialog.setCancelable(false);
			// 设置进度条最大值
			pdialog.setMax(1190);
			// 设置对话框的进度条风格
			pdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			// 设置对话框是否显示进度
			pdialog.setIndeterminate(false);
			pdialog.show();
		}

		// 2
		// 后台线程将要完成的任务
		@Override
		protected String doInBackground(URL... params) {
			StringBuilder sb = new StringBuilder();
			try {
				// URLConnection
				// 表示与URL之间通信的链接，程序可以通过URLConnection的实例
				// 向URL发送请求读取URL引用的资源
				// 通过调用URL对象的openConnection()，创建URLConnection
				URLConnection conn = params[0].openConnection();
				// 打开conn连接对应的输入流，并将它包装成BufferedReader
				// getInputStream():返回该URLConnection对应输入流，
				// 用于获取URLConnection响应的内容
				BufferedReader br = new BufferedReader(new InputStreamReader(
						conn.getInputStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
					hasRead++;
					// 3
					// 新任务的执行进度
					publishProgress(hasRead);
					// 每执行一次3方法，就回调一次4方法

				}
				return sb.toString();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		// 4
		// 在doInBackground()方法中调用publishProgress()
		// 方法更新任务的执行进度后，将会触发该方法
		@Override
		protected void onProgressUpdate(Integer... values) {
			// 更新进度
			show.setText("已经读取了【" + values[0] + "】行！");
			pdialog.setProgress(values[0]);
		}

		// 5
		// 当doInBackground()完成后，系统会自动调用onPostExecute()方法
		// 并将doInBackground()方法的返回值传给该方法
		@Override
		protected void onPostExecute(String result) {
			// 返回HTML页面的内容
			show.setText(result);
			pdialog.dismiss();

		}

	}
}
