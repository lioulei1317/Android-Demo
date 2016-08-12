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
	// ��apache�������������󣬱�URLconnection���ӿ�ݷ���
	HttpClient httpClient;
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x123) {
				// ʹ��response�ı�����ʾ��������Ӧ
				response.append(msg.obj.toString() + "\n");
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.httpclient001_main);
		// ��1���� ����DefaultHttpClient����
		// ����DefaultHttpClient����
		httpClient = new DefaultHttpClient();
		response = (TextView) findViewById(R.id.httptextview);
	}

	public void accessSecret(View v) {
		response.setText("");
		new Thread() {
			public void run() {
				// ��2��������һ��HttpGet���������õ���get
				// �����post��ʽ���ʹ���httpPost
				HttpGet grt = new HttpGet("http://user.qzone.qq.com");
				try {
					// ��3��������GET���󣬻�ȡ��Ӧ����Ӧ
					HttpResponse httpResponse = httpClient.execute(grt);
					// 4���õ����������
					HttpEntity entity = httpResponse.getEntity();
					if (entity != null) {
						// ��ȡ��������Ӧ
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
		// ���ص�¼����
		final View loginDialog = getLayoutInflater().inflate(
				R.layout.httpclient001_login, null);
		// ʹ�öԻ����û���¼ϵͳ
		new AlertDialog.Builder(HttpClient001.this).setTitle("��¼ϵͳ")
				.setView(loginDialog)
				.setPositiveButton("��¼", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						// ��ȡ�û�������û���������
						final String name = ((EditText) loginDialog
								.findViewById(R.id.httpedittext001)).getText()
								.toString();
						final String pass = ((EditText) loginDialog
								.findViewById(R.id.httpedittext002)).getText()
								.toString();
						new Thread() {
							// �����߳�
							public void run() {
								try {
									// ���������ʵ�����õ�post�����Դ�������httppost
									HttpPost post = new HttpPost(
											"http://user.qzone.qq.com");// ��
									// ������ݲ��������Ƚ϶�Ļ����ԶԴ��ݵĲ������з�װ
									// NameValuePair�����鼯�϶�������post�����������õ�
									// ���Լ�ֵ�Եķ�ʽ�������
									List<NameValuePair> params = new ArrayList<NameValuePair>();
									params.add(new BasicNameValuePair("name",
											name));
									params.add(new BasicNameValuePair("pass",
											pass));
									// ������������ı��룬һ��Ҫ��UTF_8
									post.setEntity(new UrlEncodedFormEntity(
											params, HTTP.UTF_8));
									// ����POST������httoclient�������execute��������
									HttpResponse response = httpClient
											.execute(post); // ��
									// ����������ɹ��ط�����Ӧ,����200����Ӧ�ɹ�
									if (response.getStatusLine()
											.getStatusCode() == 200) {
										// EntityUtilsΪhttpClient�ṩ��̬�����Ĺ�����
										String msg = EntityUtils
												.toString(response.getEntity());
										// loop�����߳��й�����Ϣ���е�
										Looper.prepare();// ������Ϣѭ��
										// ��ʾ��¼�ɹ�����ʾ���
										Toast.makeText(HttpClient001.this, msg,
												Toast.LENGTH_SHORT).show();
										Looper.loop();// ���ܲ�������Ϣѭ��
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							};

						}.start();
					}
				}).setNegativeButton("ȡ��", null).show();
	}
}
