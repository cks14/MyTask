package com.mytask.webservices;

import android.app.Application;
import android.content.Context;


public class MyApp extends Application {
    private static Context context;
    public static final String TAG = MyApp.class
            .getSimpleName();

    private static MyApp mInstance;


    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        context = this.getApplicationContext();
        RestClient.getInst().setup();


    }
    public static Context getContext() {
        return context;
    }
}

