package com.hilllander.khunzohn.khwangkham;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

import com.hilllander.khunzohn.khwangkham.service.UpdateService;

import java.util.Calendar;

/**
 * Created by khunzohn on 10/1/15.
 */
public class MyWidgetProvider extends AppWidgetProvider {

    private static final int SEC_INTERVAL = 15 * 60;
    private PendingIntent service = null;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        final Calendar time = Calendar.getInstance();
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MILLISECOND, 0);

        final Intent intent = new Intent(context, UpdateService.class);

        if (service == null) {
            service = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        }
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time.getTime().getTime(), SEC_INTERVAL * 1000, service);

    }

    @Override
    public void onDisabled(Context context) {
        final AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(service);
    }
}
