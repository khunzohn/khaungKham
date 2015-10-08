package com.hilllander.khunzohn.khwangkham.service;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import com.hilllander.khunzohn.khwangkham.MyWidgetProvider;
import com.hilllander.khunzohn.khwangkham.R;
import com.hilllander.khunzohn.khwangkham.WelcomeActivity;
import com.hilllander.khunzohn.khwangkham.util.MarketDay;

import java.util.Calendar;
import java.util.Random;

public class UpdateService extends Service {

    public UpdateService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        update();
        return super.onStartCommand(intent, flags, startId);
    }

    private void update() {
        MarketDay cal = new MarketDay();
        String marketDay = cal.getMarketDay();
        String year = String.valueOf(cal.get(Calendar.YEAR));
        String dayAndMonth = cal.getDayNMonth();
        String myaDay = cal.getMyaDay();

        int index = new Random().nextInt(11);
        int backgroundId = getBackgroundID(index);

        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.widget_layout);
        remoteViews.setTextViewText(R.id.text_market_day, marketDay);
        remoteViews.setTextViewText(R.id.text_year, year);
        remoteViews.setTextViewText(R.id.text_day_month, dayAndMonth);
        remoteViews.setTextViewText(R.id.text_mya_day, myaDay);

        remoteViews.setInt(R.id.widget_layout, "setBackgroundResource", backgroundId);
        Intent i = new Intent(this, WelcomeActivity.class);
        PendingIntent startWelcome = PendingIntent.getActivity(this, 0, i, 0);
        remoteViews.setOnClickPendingIntent(R.id.client_logo, startWelcome);

        ComponentName thisWidget = new ComponentName(this, MyWidgetProvider.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(this);

        int[] ids = manager.getAppWidgetIds(thisWidget);

        Intent intent = new Intent(this, MyWidgetProvider.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);


        PendingIntent pending = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.widget_layout, pending);


        manager.updateAppWidget(thisWidget, remoteViews);
    }

    private int getBackgroundID(int index) {
        int resId;
        switch (index) {
            case 0:
                resId = R.drawable.back_0;
                break;
            case 1:
                resId = R.drawable.back_1;
                break;
            case 2:
                resId = R.drawable.back_2;
                break;
            case 3:
                resId = R.drawable.back_3;
                break;
            case 4:
                resId = R.drawable.back_4;
                break;
            case 5:
                resId = R.drawable.back_5;
                break;
            case 6:
                resId = R.drawable.back_6;
                break;
            case 7:
                resId = R.drawable.back_7;
                break;
            case 8:
                resId = R.drawable.back_8;
                break;
            case 9:
                resId = R.drawable.back_9;
                break;
            case 10:
                resId = R.drawable.back_10;
                break;
            case 11:
                resId = R.drawable.back_11;
                break;
            default:
                resId = R.drawable.back_default;
        }
        return resId;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
