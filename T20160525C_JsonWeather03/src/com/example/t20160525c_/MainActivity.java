package com.example.t20160525c_;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	Button bn;
	EditText sheng, shi;
	HttpResponse httpresponse;
	HttpEntity httpentity;
	static TextView tianqiqk, riqi, wendu, shidu, fengxiang, chuanyizs, yundongzs,weilai;


	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			Bundle data = msg.getData();
			String val = data.getString("val");
			Log.i("������--->", val);
			JsonUtil json = new JsonUtil();
			 json.jianxi(val);
//			 String kongqizil=json.jianxi(val);
//			ArrayList<String> list3 = json.jianxi(val);
//			for (int i = 0; i < list3.size(); i++) {
//				String sheng = list3.get(0);
//				System.out.println("ʡqqqqqqqqqqqqqqqqqqq" + sheng);
//			}
			// shengtextview.setText(sheng);
			// tianqiqk.setText(kongqizil);
			// riqi.setText();
			// wendu.setText();
			// shidu.setText();
			// fengxiang.setText();
			// chuanyizs.setText();
			// yundongzs.setText();
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 bn = (Button) findViewById(R.id._btn);
		sheng = (EditText) findViewById(R.id.shengEdittexe);
		shi = (EditText) findViewById(R.id.shiEdittexe);
		tianqiqk = (TextView) findViewById(R.id.jintiantqqktext01);
		riqi = (TextView) findViewById(R.id.riqitext02);
		wendu = (TextView) findViewById(R.id.wendutext03);
		shidu = (TextView) findViewById(R.id.shidutext04);
		fengxiang = (TextView) findViewById(R.id.fengxiangtext05);
		chuanyizs = (TextView) findViewById(R.id.chuangyitext06);
		yundongzs = (TextView) findViewById(R.id.yundongtext07);
		weilai=(TextView) findViewById(R.id.weilaitext08);

		bn.setOnClickListener(ocl);

		// // ����EditText�Ƿ��ȡ����
		// shi.setOnFocusChangeListener(new OnFocusChangeListener() {
		// @Override
		// public void onFocusChange(View v, boolean hasFocus) {
		// if (hasFocus) {
		// Log.i("test", "��ý���");
		// } else {
		// Log.i("test", "ʧȥ����");
		// // �������߳�
		// new Thread(network).start();
		// }
		// }
		// });
	}

	OnClickListener ocl = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// �������߳�
			new Thread(network).start();
		}
	};
	Runnable network = new Runnable() {

		@Override
		public void run() {
			// 1������httpget�Ķ���
			HttpGet httpget = new HttpGet(
					"http://apicloud.mob.com/v1/weather/query?key=13292cda522db&city="
							+ shi.getText().toString() + "&province=" + sheng.getText().toString());
			// 2������httpclient�Ķ���
			HttpClient httpclient = new DefaultHttpClient();
			InputStream input = null;

			try {
				// 3������GET���󣬻�ȡ��Ӧ����Ӧ
				httpresponse = httpclient.execute(httpget);
				// 4���õ����������
				httpentity = httpresponse.getEntity();
				// ��ȡ��������Ӧ
				input = httpentity.getContent();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						input));
				String line = "";
				String result = "";
				while ((line = br.readLine()) != null) {
					result = result + line;
				}
				Log.d("������", result);

				Message message = new Message();
				Bundle bundle = new Bundle();
				bundle.putString("val", result);
				message.setData(bundle);
				handler.sendMessage(message);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	};

}
