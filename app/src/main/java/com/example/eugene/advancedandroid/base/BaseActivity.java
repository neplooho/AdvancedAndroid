package com.example.eugene.advancedandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.eugene.advancedandroid.di.Injector;
import com.example.eugene.advancedandroid.di.ScreenInjector;

import java.util.UUID;

import javax.inject.Inject;

//Our activities will extend this
public abstract class BaseActivity extends AppCompatActivity {

    private static String INSTANCE_ID_KEY = "instance_id";

    @Inject ScreenInjector screenInjector;
    private String instanceId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if(savedInstanceState != null){
            instanceId = savedInstanceState.getString(INSTANCE_ID_KEY);
        }else{
            instanceId = UUID.randomUUID().toString();
        }
        Injector.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(INSTANCE_ID_KEY, instanceId);
    }

    public String getInstanceId(){
        return instanceId;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isFinishing()){
            Injector.clearComponent(this);
        }
    }

    public ScreenInjector getScreenInjector() {
        return screenInjector;
    }
}
