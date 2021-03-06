package com.hilllander.khunzohn.khwangkham.util;

import java.util.GregorianCalendar;

/**
 * Created by khunzohn on 10/1/15.
 */
public class MarketDay extends GregorianCalendar {
    private static final String[] DAYS_MYA = {"တနဂၤေႏြ", "တနလၤာ", "အဂၤါ", "ဗုဒၶဟူး", "ၾကာသပေတး", "ေသာၾကာ", "စေန"};
    private static final String[] marketDays = new String[]{
            "ဆီဆိုင္ေစ်း", "ဆိုက္ေခါင္ေစ်း", "နမ့္ခုတ္ေစ်း", "ဘန္းယဥ္ေစ်း", "ေနာင္မြန္ေစ်း"
    };
    private static final String[] ENG_MONTHS = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
    private final int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30,
            31, 30, 31};

    public String getMarketDay() {
        int dayIndexInYear = getDayIndexInYear();
        int marketDayIndex = dayIndexInYear % 5;
        return marketDays[marketDayIndex];
    }

    private int getDayIndexInYear() {
        int dayIndexInYear = 0;
        int mMonth = get(MONTH) - 1;
        for (int i = 0; i < mMonth; i++)
            dayIndexInYear += daysInMonths[i]; //adding previous months' days
        dayIndexInYear += get(DAY_OF_MONTH);                    //adding present month's days

        if (isLeapYear(get(YEAR)) && mMonth > 1) {
            dayIndexInYear += 1;                //adding extra 1 day for months later than feb in leap year
        }

        return dayIndexInYear;
    }

    public String getMyaDay() {
        return DAYS_MYA[get(DAY_OF_WEEK) - 1];
    }

    private String getEngMonth() {
        return ENG_MONTHS[get(MONTH)];
    }

    public String getDayNMonth() {
        return getEngMonth() + " " + get(DAY_OF_MONTH);
    }
}
