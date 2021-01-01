package com.example.mocktest.utils;

import android.content.Context;
import android.content.res.Configuration;

import java.util.Locale;

public class StaticMethods {

    public static void setDefaultLanguage(Context context, String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config,
                context.getResources().getDisplayMetrics());
    }
}
