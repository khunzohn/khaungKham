package com.hilllander.khunzohn.khwangkham.util;

import java.util.GregorianCalendar;

/**
 * Created by khunzohn on 10/1/15.
 */
public class MarketDay extends GregorianCalendar {

    private static final String[] DAYS_ENG = {"Sunday", "Monday",
            "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday"};
    private static final String[] DAYS_MYA = {"တနဂၤေႏြ", "တနလၤာ", "အဂၤါ", "ဗုဒၶဟူး", "ၾကာသပေတး", "ေသာၾကာ", "စေန"};

    private static final String[] marketDays = new String[]{
            "ဆီဆိုင္ေစ်း", "ဆိုက္ေခါင္ေစ်း", "နမ့္ခုတ္ေစ်း", "ဘန္းယဥ္ေစ်း", "ေနာင္မြန္ေစ်း"
    };

    private static final String[] ENG_MONTHS = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};

    public String getMarketDay() {
        int dayIndex = (int) getNumberOfDays() % 5;
        return marketDays[dayIndex];
    }

    private long getNumberOfDays() {
        return (getTimeInMillis() / (1000 * 60 * 60 * 24));
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
