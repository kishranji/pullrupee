package com.pullrueepe;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;


public class BaseApplication extends Application {


    private static Context context;


    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();

        context = this;
    }
}
