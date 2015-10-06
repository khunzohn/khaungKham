package com.hilllander.khunzohn.khwangkham.util;

/**
 * Created by khunzohn on 10/6/15.
 */
public class PositionToDate {
    public static MarketDay positionToDate(int position) {
        MarketDay cal = new MarketDay();
        long timeMillis = position * 24 * 60 * 60 * 1000;
        cal.setTimeInMillis(timeMillis);
        return cal;
    }
}
