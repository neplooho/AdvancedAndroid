package com.example.eugene.advancedandroid.base;

import android.app.Application;

public class MyApplication extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)) //it says deprecated because noone is injectiong this yet
                .build();
    }
}
