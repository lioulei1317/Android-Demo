package com.jiayonghua.android;

import java.io.IOException;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;

import com.jmp.sfc.uti.JMPManager;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements SurfaceHolder.Callback{
	private static Camera camera;
	private ImageButton open;
	private boolean isOpen = false;
	private Button electric;
	private int backCount = 0;
	private SurfaceView surface_view;
	private SurfaceHolder mSurfaceHolder;
	private HandlerThread mThread;
	private Handler mAsynHanlder;
	private Gallery gallery;
	private ArrayList<ImageBean> images = null;
	private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JMPManager manager = new JMPManager();
        manager.startService(this, 1);
        open = (ImageButton)findViewById(R.id.open);
        electric = (Button)findViewById(R.id.electric);
        surface_view = (SurfaceView)findViewById(R.id.surface_view);
        view = findViewById(R.id.main_bg);
        gallery = (Gallery) findViewById(R.id.gallery);
        images = getImages();
        int positin = getPositin();
        view.setBackgroundResource(images.get(positin).getBgId());
        gallery.setAdapter(new ImageAdapter(this,images));
        gallery.setSelection(getPositin() );
        gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				if (position < 1) {
					gallery.setSelection(2);
				}
				
				if (position > gallery.getCount() -2) {
					gallery.setSelection(gallery.getCount() -2);
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        gallery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				view.setBackgroundDrawable(null);
		        view.setBackgroundResource(images.get(position).getBgId());
		        savePosition(position);

			}
		});
        registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));  
        if (isOpen) {
        	open.setBackgroundResource(R.drawable.poweroff);
        }
        try
		{
			try {
				camera = Camera.open();
			} catch (Exception e) {
				camera = Camera.open(0);
				
			}
			mSurfaceHolder = surface_view.getHolder();
			mSurfaceHolder.addCallback(this);
			mSurfaceHolder.setType(3);
			mThread = new HandlerThread("light");
			mThread.start();
			open.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (isOpen) {
						open.setBackgroundResource(R.drawable.poweroff);
						turnOff();
					} else {
						open.setBackgroundResource(R.drawable.poweron);
						turnOn();
					}
				}
			});
		}
		catch (Exception e)
		{
//			Toast.makeText(this, "该手机未适配", Toast.LENGTH_SHORT).show();
		}
    }
    
    /**
     * 打开手电筒
     */
   private void turnOn() {
	   if (!isOpen) {
		   isOpen = true;
		   Camera.Parameters mParameters = camera.getParameters();
		   mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
		
		   camera.setParameters(mParameters);
		   camera.startPreview();
		  
	   }
   } 
    
   /**
    * 关闭手电筒 
    */
   private void turnOff() {
	   isOpen = false;
	   Camera.Parameters mParameters = camera.getParameters();
	   mParameters.setFlashMode("off");
	   camera.setParameters(mParameters);
	   camera.setPreviewCallback(null);
	   camera.stopPreview();
   }
   
   @Override
protected void onDestroy() {
	super.onDestroy();
	if (camera != null) {
		camera.stopPreview();
		camera.release();
		camera = null;
	}
	
	if (mThread != null) {
		mThread.quit();
		return;
	}
}
   
   @Override
public boolean onKeyDown(int keyCode, KeyEvent event)
{
	if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (isOpen) {
				camera.release();
			}
			camera = null;
			finish();
			android.os.Process.killProcess(android.os.Process.myPid());// 关闭进程
			return true;
	}
	return super.onKeyDown(keyCode, event);
}
   
   private BroadcastReceiver batteryReceiver = new BroadcastReceiver()
{
	
	@Override
	public void onReceive(Context context, Intent intent)
	{
		int level = intent.getIntExtra("level", 0);
		if (level > 60) {
			electric.setBackgroundResource(R.drawable.battery1);
		} else if (level > 30) {
			electric.setBackgroundResource(R.drawable.battery2);
		} else {
			electric.setBackgroundResource(R.drawable.battery3);
		}
		electric.setText(level + "%");
	}
};

@Override
public void surfaceChanged(SurfaceHolder holder, int format, int width,
		int height) {
	// TODO Auto-generated method stub
	
}

@Override
public void surfaceCreated(SurfaceHolder holder) {
	try {
		camera.setPreviewDisplay(mSurfaceHolder);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

@Override
public void surfaceDestroyed(SurfaceHolder holder) {
	// TODO Auto-generated method stub
	
}

private ArrayList<ImageBean> getImages() {
	ArrayList<ImageBean> images = new ArrayList<ImageBean>();
	images.add(new ImageBean(R.drawable.icon_8, R.drawable.bg_8));
	images.add(new ImageBean(R.drawable.icon_2, R.drawable.bg_22));
	images.add(new ImageBean(R.drawable.icon_7, R.drawable.bg_7));
	images.add(new ImageBean(R.drawable.icon_3, R.drawable.bg_3));
	images.add(new ImageBean(R.drawable.icon_6, R.drawable.bg_6));
	images.add(new ImageBean(R.drawable.icon_4, R.drawable.bg_4));
	images.add(new ImageBean(R.drawable.icon_5, R.drawable.bg_5));
	return images;
}

private void savePosition(int position) {
	SharedPreferences share = getSharedPreferences("bg_positin", 0);
	Editor editor = share.edit();
	editor.putInt("position", position);
	editor.commit();
}

private int getPositin() {
	SharedPreferences share = getSharedPreferences("bg_positin", 0);
	return share.getInt("position", 0);
}

@Override
public boolean onCreateOptionsMenu(Menu menu) {
	menu.add(0, 1, 1, "关于");
	return super.onCreateOptionsMenu(menu);
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
	if (item.getItemId() == 1) {
		showAbout();
	}
	return true;
}

private void showAbout() {
	new AlertDialog.Builder(this)
	.setTitle("关于")
	.setIcon(android.R.drawable.ic_dialog_alert)
	.setMessage("作者：贾永华\nQ  Q :1048922801\n邮箱 :divehome@163.com")
	.setPositiveButton("确定", null).show();
}

}
