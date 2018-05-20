package com.example.eugene.advancedandroid.base;

import android.app.Application;

import com.example.eugene.advancedandroid.di.ActivityInjector;

import javax.inject.Inject;

public class MyApplication extends Application {

    @Inject
    ActivityInjector activityInjector;

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)) //it says deprecated because noone is injectiong this yet
                .build();
        component.inject(this);
    }

    public ActivityInjector getActivityInjector() {
        return activityInjector;
    }
}
