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
	String[] items = new String[] { "�����", "��ԲԲ", "����", "��ʫʫ" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alertdialog001);
		show = (EditText) findViewById(R.id.show);
	}

	public void simple(View source) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this)
		// ���öԻ������
				.setTitle("�ܳ�")
				// ����ͼ��
				.setIcon(R.drawable.ic_launcher).setMessage("����һ���ܳ�\nţ����");
		// ΪAlertDialog.Builder��ӡ�ȷ������ť
		setPositiverButton(builder);
		// ΪAlertDialog.Builder��ӡ�ȡ������ť
		setNegativeButton(builder).create().show();
	}

	public void simpleList(View source) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this)
		// ���öԻ������
				.setTitle("���б���Ի���")
				// ����ͼ��
				.setIcon(R.drawable.meinv4)
				// ���ü򵥵��б�������
				.setItems(items, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						show.setText("��ѡ���ˡ�" + items[which] + "��");
					}
				});
		// ΪAlertDialog.Builder��ӡ�ȷ������ť
		setPositiverButton(builder);
		// ΪAlertDialog.Builder��ӡ�ȡ������ť
		setNegativeButton(builder).create().show();
	}

	public void singleChoice(View source) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this)
		// ���öԻ������
				.setTitle("��ѡ�б���Ի���")
				// ����ͼ��
				.setIcon(R.drawable.meinv4)
				// ���õ�ѡ�б��Ĭ��ѡ�еڶ���(����Ϊ1)
				.setSingleChoiceItems(items, 1, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						show.setText("��ѡ���ˡ�" + items[which] + "��");
					}
				});
		// ΪAlertDialog.Builder��ӡ�ȷ������ť
		setPositiverButton(builder);
		// ΪAlertDialog.Builder��ӡ�ȡ������ť
		setNegativeButton(builder).create().show();
	}

	public void multiChoice(View source) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this)
		// ���öԻ������
				.setTitle("��ѡ�б���Ի���")
				// ����ͼ��
				.setIcon(R.drawable.meinv4)
				// ���ö�ѡ�б�����ù�ѡ��2���4��
				.setMultiChoiceItems(items,
						new boolean[] { false, true, false, true },
						new OnMultiChoiceClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which, boolean isChecked) {
							}

						});

		// ΪAlertDialog.Builder��ӡ�ȷ������ť
		setPositiverButton(builder);
		// ΪAlertDialog.Builder��ӡ�ȡ������ť
		setNegativeButton(builder).create().show();
	}

	public void customList(View source) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this)
		// ���öԻ������
				.setTitle("�Զ����б���Ի���")
				// ����ͼ��
				.setIcon(R.drawable.meinv4)
				// �����Զ����б���
				.setAdapter(
						new ArrayAdapter<String>(this,
								R.layout.alertdialog002xml, items), null);

		// ΪAlertDialog.Builder��ӡ�ȷ������ť
		setPositiverButton(builder);
		// ΪAlertDialog.Builder��ӡ�ȡ������ť
		setNegativeButton(builder).create().show();
	}

	public void customView(View source) {
		// װ��/res/linearlayout/alertdialog003.xml���沼��
		LinearLayout loginForm = (LinearLayout) getLayoutInflater().inflate(
				R.layout.alertdialog003, null);
		new AlertDialog.Builder(this)
		// ���öԻ����ͼ��
				.setIcon(R.drawable.meinv4)
				// ���öԻ���ı���
				.setTitle("�Զ���View�Ի���")
				// ���öԻ�����ʾ��View����
				.setView(loginForm)
				// Ϊ�Ի�������һ��"��¼"��ť
				.setPositiveButton("��¼", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// �˴���ִ�е�¼����
						Toast.makeText(AlertDialog001.this, "��¼�ɹ�", Toast.LENGTH_SHORT).show();
					}
				})
				// Ϊ�Ի�������һ��"ȡ��"��ť
				.setNegativeButton("ȡ��", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// ȡ����¼�������κ����顣
					}
				})
				// ����������ʾ�Ի���
				.create().show();
	}

	private AlertDialog.Builder setPositiverButton(AlertDialog.Builder builder) {
		// ����setPositiverButton�������ȷ��
		return builder.setPositiveButton("ȷ��", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				show.setText("�������ˡ�ȷ������ť");
			}
		});

	}

	private AlertDialog.Builder setNegativeButton(AlertDialog.Builder builder) {

		return builder.setNegativeButton("ȡ��", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				show.setText("�������ˡ�ȡ������ť");
			}

		});

	}
}
