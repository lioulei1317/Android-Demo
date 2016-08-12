package com.two.ui;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Hashtable;

import com.example.yiqugou.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;


/**
 * ��ά�����
 * */
public class MainActivity extends Activity {

	private int QR_WIDTH = 300;
	private int QR_HEIGHT = 300;
	private Button create;
	private Button cancel, scan;
	private BufferedOutputStream outStream;
	private EditText content;
	private long time = Calendar.getInstance().getTimeInMillis();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initview();
		initdata();

	}

	private void initdata() {
		/*���ɶ�ά��
		 * 
		 * */
		
		create.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(content.getText().toString().equals("")){
					
					Toast.makeText(MainActivity.this, "��������յ�����", 0).show();}
				else{
				View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.bitmapdialog, null);
				ImageView img = (ImageView) view.findViewById(R.id.dialog_bitmap);
				final String url = content.getText().toString();
				final Bitmap scanbitmap = createQRImage(url);
				img.setImageBitmap(scanbitmap);
				
					new AlertDialog.Builder(MainActivity.this)
						.setTitle("��ά��")
						.setView(view)
						.setPositiveButton("��������",
								new DialogInterface.OnClickListener() {

									

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
											/*
											 * ��ͼƬת����һ��byte[]��
											 * */
											byte [] bitmaps = getbitmaptobytes(scanbitmap);
											
											/*
											 * ��Byte[]ת����long����
											 * */
											long longbitmaps = bytes2long(bitmaps);
											/*
											 * �ж�SD���Ƿ����㹻�Ŀռ乩����ʹ��
											 * */
//											boolean iscapacity = isEnaleforDownload(longbitmaps);
											if(true){
											
											try {
												File sdCardDir = Environment.getExternalStorageDirectory();
												//��ֹ�����ظ�����
												String fileName =time+".png";
												File dir;
												dir = new File(sdCardDir.getCanonicalPath()+"/myscan/");
												if (!dir.exists()) {
													dir.mkdirs();
												}
												
												File cacheFile = new File(dir, fileName);
												FileOutputStream fstream = new FileOutputStream(cacheFile);
									            outStream = new BufferedOutputStream(fstream);
									            System.out.println("1222222");
									            outStream.write(bitmaps);
									           
									            
									            Toast.makeText(MainActivity.this, "ͼƬ�ɹ�����myscanĿ¼��", 0).show();
									            
											} catch (Exception e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
												Log.d("ydh","���汾��ͼƬ�쳣������");
											}finally{
												
												if(outStream!=null){
													try {
														outStream.close();
													} catch (IOException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
												}
												//TODO 
												dialog.dismiss();
												dialog=null;
											}
										
										}  }else{
											Toast.makeText(MainActivity.this, "SDCard�洢�ռ䲻��", 0).show();
										}

									}

									private boolean isEnaleforDownload(long longbitmaps) {
										/*
										 * Statfs : ��ȡϵͳ�ļ�����
										 * @.getAbsolutePath()��һ������·�����ľ���·���ַ���
										 * 
										 * */
										StatFs statfs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
										
										//�������ֻ����м����洢������ÿ������
										int blockCount = statfs.getBlockCount();
										
										//���ֻ�����õĿ�������������õĴ洢��Ҳ����˵��ʣ���ڴ�����
										int availableBlocks = statfs.getAvailableBlocks();
										
										/*
										 * ���ÿһ����Ĵ�С�� ����ֵ��long���ܣ�int���ܴﵽ����
										 * */
										long blockSize = statfs.getBlockSize();
										//��ÿ��õĴ洢�ռ�
										
										long asavespace = availableBlocks * blockSize;
										
										if(asavespace>longbitmaps){
											return true;
										}
										return false;
									}

									/*
									 * ��Byte[]ת����long����
									 * */
									private long bytes2long(byte[] bitmaps) {
										int num = 0;
										for (int ix = 0; ix < 8; ++ix) {  
											        num <<= 8;  
											        num |= (bitmaps[ix] & 0xff);  
											  }  
										return num;
									}

									/*
									 * ��ͼƬת����Byte[]
									 * */
									private byte[] getbitmaptobytes(Bitmap bitmap) {
										ByteArrayOutputStream out = new ByteArrayOutputStream();
										bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
										return out.toByteArray();
									}
								}).show();

			}
			}
			});
		
		/*
		 * ��ת��ɨ���ά��ҳ��
		 * */
		scan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,MipcaActivityCapture.class);
				startActivity(intent);
			}
		});
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				content.setText("");
			}
		});
	}

	private void initview() {
		create = (Button) findViewById(R.id.create);
		cancel = (Button) findViewById(R.id.cancel);
		content = (EditText) findViewById(R.id.input);
		scan = (Button) findViewById(R.id.main_scan);
		
	}

	public Bitmap createQRImage(String url) {
		try {
			// �ж�URL�Ϸ���
			if (url == null || "".equals(url) || url.length() < 1) {
				return null;
			}
			Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			// ͼ������ת����ʹ���˾���ת��
//			BitMatrix bitMatrix = new MultiFormatWriter().encode(new String(url.getBytes("GBK"),"ISO-8859-1"),BarcodeFormat.QR_CODE, 300, 300);
			BitMatrix bitMatrix = new QRCodeWriter().encode(url,BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT);
			int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
			// �������ﰴ�ն�ά����㷨��������ɶ�ά���ͼƬ��
			// ����forѭ����ͼƬ����ɨ��Ľ��
			for (int y = 0; y < QR_HEIGHT; y++) {
				for (int x = 0; x < QR_WIDTH; x++) {
					if (bitMatrix.get(x, y)) {
						pixels[y * QR_WIDTH + x] = 0xff000000;
					} else {
						pixels[y * QR_WIDTH + x] = 0xffffffff;
					}
				}
			}
			// ���ɶ�ά��ͼƬ�ĸ�ʽ��ʹ��ARGB_8888
			Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT,Bitmap.Config.ARGB_8888);
			bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
			return bitmap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
