package com.example.volleytest;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * ʹ��Get��ʽ�������ݷ���StringRequest����
 * 
 * ���뷵��String���͵����������ݻ��߲��������ʲô����ʱ������StringRequest����
 * 
 * ����ʹ��Get����ʽ����һ��String���͵��ֻ���������Ϣ��
 * 
 * @author Administrator
 * 
 */
public class MainActivity extends Activity {
	Button button1, button2, button3, button4, button5, button6;
	ImageView image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button1 = (Button) findViewById(R.id.Button1);
		button2 = (Button) findViewById(R.id.Button2);
		button3 = (Button) findViewById(R.id.Button3);
		button4 = (Button) findViewById(R.id.Button4);
		button5 = (Button) findViewById(R.id.Button5);
		button6 = (Button) findViewById(R.id.Button6);
		image = (ImageView) findViewById(R.id.image);
		button1.setOnClickListener(listener);
		button2.setOnClickListener(listener);
		button3.setOnClickListener(listener);
		button4.setOnClickListener(listener);
		button5.setOnClickListener(listener);
		button6.setOnClickListener(listener);
	}

	/**
	 * new StringRequest(int method,String url,Listener listener,ErrorListener
	 * errorListener)
	 * 
	 * method������ʽ��Get����ΪMethod.GET;Post����ΪMethod.POST;
	 * 
	 * url�������ַ
	 * 
	 * listener������ɹ���Ļص� errorListener������ʧ�ܵĻص�
	 */
	private void volleyGet1() {
		String url = "https://www.baidu.com/";
		System.out.println("asd==" + url);
		StringRequest request = new StringRequest(Method.GET, url,
				new Listener<String>() {
					@Override
					public void onResponse(String s) {// sΪ���󷵻ص��ַ�������
						Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG)
								.show();
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError volleyError) {
						Toast.makeText(MainActivity.this,
								volleyError.toString(), Toast.LENGTH_LONG)
								.show();
					}
				});
		// ���������Tag��ǩ��������ȫ�����������ͨ��Tag��ǩ��������Ĳ���
		request.setTag("testGet");
		// ���������ȫ�ֶ�����
		MyApplication.getHttpQueues().add(request);
	}

	/**
	 * new JsonObjectRequest(int method,String url,JsonObject
	 * jsonObject,Listener listener,ErrorListener errorListener)
	 * method������ʽ��Get����ΪMethod.GET��Post����ΪMethod.POST url�������ַ
	 * JsonObject��Json��ʽ��������������ʹ�õ���Get����ʽ����������Ѿ�������url�У����Կ��Խ��˲�����Ϊnull
	 * listener������ɹ���Ļص� errorListener������ʧ�ܵĻص�
	 */
	private void volleyGet2() {
		String url = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=218.4.255.255";
		JsonObjectRequest request = new JsonObjectRequest(Method.GET, url,
				null, new Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject jsonObject) {// jsonObjectΪ���󷵻ص�Json��ʽ����
						Toast.makeText(MainActivity.this,
								jsonObject.toString(), Toast.LENGTH_LONG)
								.show();
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError volleyError) {
						Toast.makeText(MainActivity.this,
								volleyError.toString(), Toast.LENGTH_LONG)
								.show();
					}
				});

		// ���������Tag��ǩ��������ȫ�����������ͨ��Tag��ǩ��������Ĳ���
		request.setTag("testGet");
		// ���������ȫ�ֶ�����
		MyApplication.getHttpQueues().add(request);
	}

	/**
	 * ʹ��Post��ʽ����String���͵�����������
	 * 
	 * new StringRequest(int method,String url,Listener listener,ErrorListener
	 * errorListener) method������ʽ��Get����ΪMethod.GET��Post����ΪMethod.POST url�������ַ
	 * listener������ɹ���Ļص� errorListener������ʧ�ܵĻص�
	 */
	private void volleyPost3() {
		String url = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm";
		StringRequest request = new StringRequest(Method.POST, url,
				new Listener<String>() {
					@Override
					public void onResponse(String s) {// sΪ���󷵻ص��ַ�������
						Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG)
								.show();
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError volleyError) {
						Toast.makeText(MainActivity.this,
								volleyError.toString(), Toast.LENGTH_LONG)
								.show();
					}
				}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				// ����������������ֵ����map��
				map.put("tel", "15850781443");
				return map;
			}
		};
		// ���������Tag��ǩ��������ȫ�����������ͨ��Tag��ǩ��������Ĳ���
		request.setTag("testPost");
		// ���������ȫ�ֶ�����
		MyApplication.getHttpQueues().add(request);
	}

	/**
	 * ʹ��Post��ʽ����JsonObject���͵�����������
	 * 
	 * new JsonObjectRequest(int method,String url,JsonObject
	 * jsonObject,Listener listener,ErrorListener errorListener)
	 * method������ʽ��Get����ΪMethod.GET��Post����ΪMethod.POST url�������ַ
	 * JsonObject��Json��ʽ��������������ʹ�õ���Get����ʽ����������Ѿ�������url�У����Կ��Խ��˲�����Ϊnull
	 * listener������ɹ���Ļص� errorListener������ʧ�ܵĻص�
	 */
	private void volleyPost4() {
		String url = "http://www.kuaidi100.com/query";
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", "yuantong");
		map.put("postid", "229728279823");
		// ��mapת��ΪJSONObject����
		JSONObject jsonObject = new JSONObject(map);

		JsonObjectRequest request = new JsonObjectRequest(Method.POST, url,
				jsonObject, new Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject jsonObject) {// jsonObjectΪ���󷵻ص�Json��ʽ����
						Toast.makeText(MainActivity.this,
								jsonObject.toString(), Toast.LENGTH_LONG)
								.show();
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError volleyError) {
						Toast.makeText(MainActivity.this,
								volleyError.toString(), Toast.LENGTH_LONG)
								.show();
					}
				});
		// ���������Tag��ǩ��������ȫ�����������ͨ��Tag��ǩ��������Ĳ���
		request.setTag("testPost");
		// ���������ȫ�ֶ�����
		MyApplication.getHttpQueues().add(request);
	}

	/**
	 * ͨ��Volley��������ͼƬ
	 * 
	 * new ImageRequest(String url,Listener listener,int maxWidth,int
	 * maxHeight,Config decodeConfig,ErrorListener errorListener) url�������ַ
	 * listener������ɹ���Ļص� maxWidth��maxHeight������ͼƬ������ߣ��������Ϊ0���ʾ��ԭ�ߴ���ʾ
	 * decodeConfig
	 * ��ͼƬ���صĴ��淽ʽ��Config.RGB_565��ʾÿ������ռ2���ֽڣ�Config.ARGB_8888��ʾÿ������ռ4���ֽڵȡ�
	 * errorListener������ʧ�ܵĻص�
	 */
	private void loadImageByVolley() {
		String url = "http://s2.nuomi.bdimg.com/upload/deal/2014/1/V_L/623682-1391756281052.jpg";
		ImageRequest request = new ImageRequest(url, new Listener<Bitmap>() {
			@Override
			public void onResponse(Bitmap bitmap) {
				image.setImageBitmap(bitmap);
			}
		}, 0, 0, Config.RGB_565, new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {
				image.setImageResource(R.drawable.ic_launcher);
			}
		});

		// ���������Tag��ǩ��������ȫ�����������ͨ��Tag��ǩ��������Ĳ���
		request.setTag("loadImage");
		// ͨ��Tag��ǩȡ����������ж�Ӧ��ȫ������
		MyApplication.getHttpQueues().add(request);
	}

	/**
	 * ͨ��ImageLoader���ؼ���������ͼƬ ������* new ImageLoader(RequestQueue queue,ImageCache
	 * imageCache) queue��������� imageCache��һ������ͼƬ����Ľӿڣ�һ����Ҫ��������ʵ����
	 * 
	 * getImageListener(ImageView view, int defaultImageResId, int
	 * errorImageResId) view��ImageView���� defaultImageResId��Ĭ�ϵ�ͼƬ����ԴId
	 * errorImageResId������ͼƬ����ʧ��ʱ��ʾ��ͼƬ����ԴId
	 */
	private void loadImageWithCache() {
		String url = "http://zhanhui.3158.cn/data/attachment/exhibition/data/attachment/exhibition/article/2015/12/17/4d80cdd4500e52ff5c7fbd600c0a7a84.jpg";
		ImageLoader loader = new ImageLoader(MyApplication.getHttpQueues(),
				new BitmapCache());
		ImageListener listener = loader.getImageListener(image,
				R.drawable.ic_launcher, R.drawable.ic_launcher);
		// ���ؼ���������ͼƬ
		loader.get(url, listener);
	}

	// @Override
	// protected void onStop() {
	// super.onStop();
	// //ͨ��Tag��ǩȡ����������ж�Ӧ��ȫ������
	// MyApplication.getHttpQueues().cancelAll("abcGet");
	// }

	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch (arg0.getId()) {
			case R.id.Button1:
				volleyGet1();
				break;
			case R.id.Button2:
				volleyGet2();
				break;
			case R.id.Button3:
				volleyPost3();
				break;
			case R.id.Button4:
				volleyPost4();
				break;
			case R.id.Button5:
				loadImageByVolley();
				break;
			case R.id.Button6:
				loadImageWithCache();
				break;
			}
		}
	};

}
