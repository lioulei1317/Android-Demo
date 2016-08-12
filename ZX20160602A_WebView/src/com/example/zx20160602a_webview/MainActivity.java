package com.example.zx20160602a_webview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {
	private String url = "https://www.taobao.com/";
	WebView webView;
	private ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.web);
		// Uri uri = Uri.parse(url);// urlΪ��Ҫ���ӵĵ�ַ
		// Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		// startActivity(intent);
		init();
	}

	private void init() {
		webView = (WebView) findViewById(R.id.webView);
		// WebView���ر�����Դ
		// webView.loadUrl("file:///android_asset/example.html");
		// WebView����web��Դ
		webView.loadUrl(url);
		// ����webviewĬ��ͨ��������������ϵͳ������򿪵���Ϊ��ʹ����ҳ������webview�д�
		webView.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// ����ֵ��trueʱ��������ҳ��webview�д򿪣�Ϊfalse����ϵͳ�����������������ȥ��
				view.loadUrl(url);
				return true;
			}
			// WebViewClient����webviewȥ����һЩҳ����ƺ�����֪ͨ

		});
		// ����֧��JavaScript
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		// webview����ҳ������ʹ�û������
		settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		webView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
				// newProgress 1-100֮�������
				if (newProgress == 100) {
					// ��ҳ������ϣ��ر�ProgressDialog
					closeDialog();
				} else {
					// ��ҳ���ڼ���,��ProgressDialog
					openDialog(newProgress);
				}
			}

			private void closeDialog() {
				// TODO Auto-generated method stub
				if (dialog != null && dialog.isShowing()) {
					dialog.dismiss();
					dialog = null;
				}
			}

			private void openDialog(int newProgress) {
				// TODO Auto-generated method stub
				if (dialog == null) {
					dialog = new ProgressDialog(MainActivity.this);
					dialog.setTitle("�ף�����Ŭ�������У����Ե�!");
					// ���ý�������ʽ
					dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
					dialog.setProgress(newProgress);
					dialog.show();
				} else {
					dialog.setProgress(newProgress);
				}
			}
		});
	}

	// ��д�ֻ�������--���ص��߼�
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// Toast.makeText(this, webView.getUrl(),
			// Toast.LENGTH_SHORT).show();
			if (webView.canGoBack()) {
				webView.goBack();// ������һҳ��
				return true;
			} else {
				System.exit(0);// �˳�����
			}
		}

		return super.onKeyDown(keyCode, event);
	}
}
