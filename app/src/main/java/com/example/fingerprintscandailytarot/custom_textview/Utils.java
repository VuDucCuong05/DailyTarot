package com.example.fingerprintscandailytarot.custom_textview;

import android.content.Context;
import android.graphics.Typeface;

public class Utils {

    private static Typeface restotaTypeface;
    private static Typeface restotaNeueExtraBoldTypeface;

    public static Typeface getRestotaTypeface(Context context) {
        if(restotaTypeface == null){
            restotaTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Restora Neue Regular.ttf");
        }
        return restotaTypeface;
    }
    public static Typeface getRestotaNeueExtraBoldTypeface(Context context) {
        if(restotaNeueExtraBoldTypeface == null){
            restotaNeueExtraBoldTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Restora Neue SemiBold.ttf");
        }
        return restotaNeueExtraBoldTypeface;
    }
}
