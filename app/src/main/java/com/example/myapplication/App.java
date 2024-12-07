package com.example.myapplication;

import android.app.Application;
import android.content.Context;

import androidx.work.Configuration;

public class App extends Application implements Configuration.Provider {
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @Override
    public Configuration getWorkManagerConfiguration() {
        return new Configuration.Builder()
                .setMinimumLoggingLevel(android.util.Log.INFO)
                .build();
    }

    public static Context getAppContext() {
        return instance.getApplicationContext();
    }
}
