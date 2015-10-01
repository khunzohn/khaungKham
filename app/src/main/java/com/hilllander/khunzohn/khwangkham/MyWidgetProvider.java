package com.hilllander.khunzohn.khwangkham;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.hilllander.khunzohn.khwangkham.util.MarketDay;

import java.util.Calendar;

/**
 * Created by khunzohn on 10/1/15.
 */
public class MyWidgetProvider extends AppWidgetProvider {

    private MarketDay mMarketDay = new MarketDay();
    private String marketDay;
    private String year;
    private String dayAndMonth;
    private String myaDay;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        ComponentName thisWidget = new ComponentName(context, MyWidgetProvider.class);
        //get all ids
        int[] ids = appWidgetManager.getAppWidgetIds(thisWidget);
        for (int widgetId : ids) {
            marketDay = mMarketDay.getMarketDay();
            year = String.valueOf(mMarketDay.get(Calendar.YEAR));
            dayAndMonth = mMarketDay.getDayNMonth();
            myaDay = mMarketDay.getMyaDay();

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

            remoteViews.setTextViewText(R.id.text_market_day, marketDay);
            remoteViews.setTextViewText(R.id.text_year, year);
            remoteViews.setTextViewText(R.id.text_day_month, dayAndMonth);
            remoteViews.setTextViewText(R.id.text_mya_day, myaDay);

            Intent intent = new Intent(context, WelcomeActivity.class);
            PendingIntent pending = PendingIntent.getActivity(context, 0, intent, 0);
            remoteViews.setOnClickPendingIntent(R.id.widget_layout, pending);
//            Intent intent = new Intent(context, MyWidgetProvider.class);
//            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
//            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
//
//
//            PendingIntent pending = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//            remoteViews.setOnClickPendingIntent(R.id.text_market_day, pending);

            appWidgetManager.updateAppWidget(widgetId, remoteViews);

        }
    }
}
