package com.yiqugou.webview;

import com.example.yiqugou.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebView extends Activity {
	private String url = "https://item.taobao.com/item.htm?id=532050870200&ali_refid=a3_430582_1006:1121376662:N:��ɱ:4c76e4955266ce51f9936898feb48fa7&ali_trackid=1_4c76e4955266ce51f9936898feb48fa7&spm=a230r.1.14.6.hZqNe7#detail/";
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
					dialog = new ProgressDialog(MyWebView.this);
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
