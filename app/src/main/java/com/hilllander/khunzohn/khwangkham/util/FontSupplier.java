package com.hilllander.khunzohn.khwangkham.util;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by khunzohn on 10/11/15.
 */
public class FontSupplier {
    public static Typeface getZawgyiTypeface(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/Zawgyi-One.ttf");
    }
}
