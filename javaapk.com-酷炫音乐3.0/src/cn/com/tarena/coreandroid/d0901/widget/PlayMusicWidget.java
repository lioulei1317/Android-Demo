package cn.com.tarena.coreandroid.d0901.widget;


import cn.com.tarena.coreandroid.d0901.R;
import cn.com.tarena.coreandroid.d0901.utils.ApplicationConsts;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;


public class PlayMusicWidget extends AppWidgetProvider implements ApplicationConsts{

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		
		RemoteViews views=new RemoteViews(context.getPackageName(), R.layout.play_music_widget);;
		PendingIntent pendingIntent;
		// 下一首按钮的点击
				pendingIntent = PendingIntent.getBroadcast(context, 0, new Intent(ACTIVITY_NEXT_BUTTON_CLICK), 0);
				views.setOnClickPendingIntent(R.id.ib_widget_next, pendingIntent);
		// 上一首按钮的点击
				pendingIntent = PendingIntent.getBroadcast(context, 0, new Intent(ACTIVITY_PREVIOUS_BUTTON_CLICK), 0);
				views.setOnClickPendingIntent(R.id.ib_widget_previous, pendingIntent);
		// 播放按钮的点击
				pendingIntent = PendingIntent.getBroadcast(context, 0, new Intent(ACTIVITY_PLAY_BUTTON_CLICK), 0);
				views.setOnClickPendingIntent(R.id.ib_widget_play, pendingIntent);
		// 图片的点击
				pendingIntent = PendingIntent.getBroadcast(context, 0, new Intent(ACTIVITY_START_MAIN), 0);
				views.setOnClickPendingIntent(R.id.iv_widget_image, pendingIntent);
				
				
				appWidgetManager.updateAppWidget(appWidgetIds, views);
		
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	
	}
	
	
	
}
