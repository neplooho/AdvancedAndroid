package com.example.eugene.advancedandroid.base;

import android.app.Application;

import com.example.eugene.advancedandroid.di.ActivityInjector;

import javax.inject.Inject;

import timber.log.BuildConfig;
import timber.log.Timber;

public class MyApplication extends Application {

    @Inject
    ActivityInjector activityInjector;

    protected ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = initComponent();
        component.inject(this);

        if (BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
    }

    protected ApplicationComponent initComponent(){
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)) //it says deprecated because noone is injectiong this yet
                .build();
    }

    public ActivityInjector getActivityInjector() {
        return activityInjector;
    }
}
