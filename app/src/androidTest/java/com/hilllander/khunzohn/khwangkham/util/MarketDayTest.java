package com.hilllander.khunzohn.khwangkham.util;

import junit.framework.TestCase;

/**
 * Created by khunzohn on 10/1/15.
 */
public class MarketDayTest extends TestCase {
    public static final String[] mDays = new String[]{
            "ဆီဆိုင္ေစ်း", "ဆိုက္ေခါင္ေစ်း", "နမ့္ခုတ္ေစ်း", "ဘန္းယဥ္ေစ်း", "ေနာင္မြန္ေစ်း"
    };

    private static final String[] DAYS_MYA = {"တနဂၤေႏြ", "တနလၤာ", "အဂၤါ", "ဗုဒၶဟူး", "ၾကာသပေတး", "ေသာၾကာ", "စေန"};
    MarketDay market;

    public void setUp() throws Exception {
        super.setUp();
        market = new MarketDay();
    }

    public void testGetMarketDay() throws Exception {
        String mday = mDays[4];
        assertEquals(mday, market.getMarketDay());
    }

    public void testGetMyaDay() throws Exception {
        int
                year = 2015,
                month = 9,
                day = 5,
                dayIndex = 1;
        String mday = DAYS_MYA[dayIndex];
        market.set(year, month, day);
        assertEquals(mday, market.getMyaDay());
    }

    public void testGetEngMonth() throws Exception {
        String month = "October";
        assertEquals(month, market.getEngMonth());
    }
}