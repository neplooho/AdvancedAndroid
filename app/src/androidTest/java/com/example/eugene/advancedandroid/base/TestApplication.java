package com.example.eugene.advancedandroid.base;

public class TestApplication extends MyApplication {

    @Override
    protected ApplicationComponent initComponent() {
        return DaggerTestApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)) //it says deprecated because noone is injectiong this yet
                .build();
    }
}
