package com.hilllander.khunzohn.khwangkham;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.hilllander.khunzohn.khwangkham.util.MarketDay;

/**
 * Created by khunzohn on 10/1/15.
 */
public class MyWidgetProvider extends AppWidgetProvider {

    MarketDay mMarketDay = new MarketDay();

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        ComponentName thisWidget = new ComponentName(context, MyWidgetProvider.class);
        //get all ids
        int[] ids = appWidgetManager.getAppWidgetIds(thisWidget);
        for (int widgetId : ids) {
            String marketDay = mMarketDay.getMarketDay();
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

            remoteViews.setTextViewText(R.id.widget_text, marketDay);

            Intent intent = new Intent(context, MyWidgetProvider.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);


            PendingIntent pending = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.widget_text, pending);

            appWidgetManager.updateAppWidget(widgetId, remoteViews);

        }
    }
}
