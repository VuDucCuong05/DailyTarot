package com.example.fingerprintscandailytarot.locale;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Locale;

public class LocaleHelper {
    private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";

    private Context mContext;
    private SharedPreferences sharedPreferencesLocale;

    public LocaleHelper(Context context) {
        mContext = context;
        sharedPreferencesLocale = mContext.getSharedPreferences(SELECTED_LANGUAGE, Context.MODE_PRIVATE);
    }

    public void updateResources(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources resources = mContext.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        setLang(language);

        String str = sharedPreferencesLocale.getString("lang", "cuong");
        Log.e("prox", "lang" + str);
    }

    public String getLang() {
        return sharedPreferencesLocale.getString("lang", "en");
    }

    public void setLang(String code) {
        SharedPreferences.Editor editor = sharedPreferencesLocale.edit();
        editor.putString("lang", code);
        editor.commit();
    }
}
