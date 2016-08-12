package com.example.t20160524c_httpclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HttpClient001 extends Activity {
	TextView response;
	// 是apache框架里的网络请求，比URLconnection更加快捷方便
	HttpClient httpClient;
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x123) {
				// 使用response文本框显示服务器响应
				response.append(msg.obj.toString() + "\n");
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.httpclient001_main);
		// 第1步： 创建DefaultHttpClient对象
		// 创建DefaultHttpClient对象
		httpClient = new DefaultHttpClient();
		response = (TextView) findViewById(R.id.httptextview);
	}

	public void accessSecret(View v) {
		response.setText("");
		new Thread() {
			public void run() {
				// 第2步：创建一个HttpGet对象，这里用的是get
				// 如果是post方式，就创建httpPost
				HttpGet grt = new HttpGet("http://user.qzone.qq.com");
				try {
					// 第3步：发送GET请求，获取对应的响应
					HttpResponse httpResponse = httpClient.execute(grt);
					// 4、得到里面的内容
					HttpEntity entity = httpResponse.getEntity();
					if (entity != null) {
						// 读取服务器响应
						BufferedReader br = new BufferedReader(
								new InputStreamReader(entity.getContent()));
						String line = null;
						while ((line = br.readLine()) != null) {
							Message msg = new Message();
							msg.what = 0x123;
							msg.obj = line;
							handler.sendMessage(msg);
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			};
		}.start();
	}

	public void showLogin(View v) {
		// 加载登录界面
		final View loginDialog = getLayoutInflater().inflate(
				R.layout.httpclient001_login, null);
		// 使用对话框供用户登录系统
		new AlertDialog.Builder(HttpClient001.this).setTitle("登录系统")
				.setView(loginDialog)
				.setPositiveButton("登录", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						// 获取用户输入的用户名、密码
						final String name = ((EditText) loginDialog
								.findViewById(R.id.httpedittext001)).getText()
								.toString();
						final String pass = ((EditText) loginDialog
								.findViewById(R.id.httpedittext002)).getText()
								.toString();
						new Thread() {
							// 启动线程
							public void run() {
								try {
									// 创建请求的实例，用的post，所以创建的是httppost
									HttpPost post = new HttpPost(
											"http://user.qzone.qq.com");// ③
									// 如果传递参数个数比较多的话可以对传递的参数进行封装
									// NameValuePair是数组集合对象，用来post请求创术数据用的
									// 它以键值对的方式存放数据
									List<NameValuePair> params = new ArrayList<NameValuePair>();
									params.add(new BasicNameValuePair("name",
											name));
									params.add(new BasicNameValuePair("pass",
											pass));
									// 设置请求参数的编码，一定要用UTF_8
									post.setEntity(new UrlEncodedFormEntity(
											params, HTTP.UTF_8));
									// 发送POST请求，用httoclient对象调用execute发送请求
									HttpResponse response = httpClient
											.execute(post); // ④
									// 如果服务器成功地返回响应,这里200是响应成功
									if (response.getStatusLine()
											.getStatusCode() == 200) {
										// EntityUtils为httpClient提供静态帮助的工具类
										String msg = EntityUtils
												.toString(response.getEntity());
										// loop是子线程中管理消息队列的
										Looper.prepare();// 启动消息循环
										// 提示登录成功，显示结果
										Toast.makeText(HttpClient001.this, msg,
												Toast.LENGTH_SHORT).show();
										Looper.loop();// 接受并处理消息循环
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							};

						}.start();
					}
				}).setNegativeButton("取消", null).show();
	}
}
