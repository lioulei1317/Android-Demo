package com.example.t20160428a_actionbar01;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class AlertDialog001 extends Activity {
	EditText show;
	String[] items = new String[] { "刘亦菲", "高圆圆", "唐嫣", "刘诗诗" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alertdialog001);
		show = (EditText) findViewById(R.id.show);
	}

	public void simple(View source) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this)
		// 设置对话框标题
				.setTitle("跑车")
				// 设置图标
				.setIcon(R.drawable.ic_launcher).setMessage("这是一辆跑车\n牛不？");
		// 为AlertDialog.Builder添加【确定】按钮
		setPositiverButton(builder);
		// 为AlertDialog.Builder添加【取消】按钮
		setNegativeButton(builder).create().show();
	}

	public void simpleList(View source) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this)
		// 设置对话框标题
				.setTitle("简单列表项对话框")
				// 设置图标
				.setIcon(R.drawable.meinv4)
				// 设置简单的列表项内容
				.setItems(items, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						show.setText("你选中了《" + items[which] + "》");
					}
				});
		// 为AlertDialog.Builder添加【确定】按钮
		setPositiverButton(builder);
		// 为AlertDialog.Builder添加【取消】按钮
		setNegativeButton(builder).create().show();
	}

	public void singleChoice(View source) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this)
		// 设置对话框标题
				.setTitle("单选列表项对话框")
				// 设置图标
				.setIcon(R.drawable.meinv4)
				// 设置单选列表项，默认选中第二项(索引为1)
				.setSingleChoiceItems(items, 1, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						show.setText("你选中了《" + items[which] + "》");
					}
				});
		// 为AlertDialog.Builder添加【确定】按钮
		setPositiverButton(builder);
		// 为AlertDialog.Builder添加【取消】按钮
		setNegativeButton(builder).create().show();
	}

	public void multiChoice(View source) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this)
		// 设置对话框标题
				.setTitle("多选列表项对话框")
				// 设置图标
				.setIcon(R.drawable.meinv4)
				// 设置多选列表项，设置勾选第2项，第4项
				.setMultiChoiceItems(items,
						new boolean[] { false, true, false, true },
						new OnMultiChoiceClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which, boolean isChecked) {
							}

						});

		// 为AlertDialog.Builder添加【确定】按钮
		setPositiverButton(builder);
		// 为AlertDialog.Builder添加【取消】按钮
		setNegativeButton(builder).create().show();
	}

	public void customList(View source) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this)
		// 设置对话框标题
				.setTitle("自定义列表项对话框")
				// 设置图标
				.setIcon(R.drawable.meinv4)
				// 设置自定义列表项
				.setAdapter(
						new ArrayAdapter<String>(this,
								R.layout.alertdialog002xml, items), null);

		// 为AlertDialog.Builder添加【确定】按钮
		setPositiverButton(builder);
		// 为AlertDialog.Builder添加【取消】按钮
		setNegativeButton(builder).create().show();
	}

	public void customView(View source) {
		// 装载/res/linearlayout/alertdialog003.xml界面布局
		LinearLayout loginForm = (LinearLayout) getLayoutInflater().inflate(
				R.layout.alertdialog003, null);
		new AlertDialog.Builder(this)
		// 设置对话框的图标
				.setIcon(R.drawable.meinv4)
				// 设置对话框的标题
				.setTitle("自定义View对话框")
				// 设置对话框显示的View对象
				.setView(loginForm)
				// 为对话框设置一个"登录"按钮
				.setPositiveButton("登录", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// 此处可执行登录处理
						Toast.makeText(AlertDialog001.this, "登录成功", Toast.LENGTH_SHORT).show();
					}
				})
				// 为对话框设置一个"取消"按钮
				.setNegativeButton("取消", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// 取消登录，不做任何事情。
					}
				})
				// 创建、并显示对话框
				.create().show();
	}

	private AlertDialog.Builder setPositiverButton(AlertDialog.Builder builder) {
		// 调用setPositiverButton方法添加确定
		return builder.setPositiveButton("确定", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				show.setText("您单击了【确定】按钮");
			}
		});

	}

	private AlertDialog.Builder setNegativeButton(AlertDialog.Builder builder) {

		return builder.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				show.setText("您单击了【取消】按钮");
			}

		});

	}
}
