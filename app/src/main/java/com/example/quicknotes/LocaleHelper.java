package com.example.quicknotes;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import androidx.annotation.RequiresApi;
import java.util.Locale;

public class LocaleHelper {

    private static final String PREFS_NAME = "Settings";
    private static final String LANG_KEY = "My_Lang";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void setLocale(Activity activity, String languageCode) {
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());

        // Dil kodunu SharedPreferences'e kaydet
        SharedPreferences.Editor editor = activity.getSharedPreferences(PREFS_NAME, Activity.MODE_PRIVATE).edit();
        editor.putString(LANG_KEY, languageCode);
        editor.apply();
    }

    public static void loadLocale(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences(PREFS_NAME, Activity.MODE_PRIVATE);
        String languageCode = prefs.getString(LANG_KEY, "");
        setLocale(activity, languageCode);
    }
}
