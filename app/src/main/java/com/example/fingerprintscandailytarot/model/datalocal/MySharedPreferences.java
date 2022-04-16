package com.example.fingerprintscandailytarot.model.datalocal;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class MySharedPreferences {
    private static final String MY_SHARED_PERENCES = "MY_SHARED_PERENCES";
    private Context mContext;

    public MySharedPreferences(Context mContext) {
        this.mContext = mContext;
    }

    public void putStringValues(String key,String values){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MY_SHARED_PERENCES,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,values);
        editor.apply();
    }
    public String getString(String key){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MY_SHARED_PERENCES,
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");
    }

}
