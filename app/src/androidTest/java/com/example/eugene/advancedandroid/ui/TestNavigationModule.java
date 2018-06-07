package com.example.eugene.advancedandroid.ui;

import com.example.eugene.advancedandroid.lifecycle.ActivityLifeCycleTask;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

@Module
public abstract class TestNavigationModule {

    @Binds
    abstract ScreenNavigator bindScreenNavigator(TestScreenNavigator screenNavigator);

    @Binds
    @IntoSet
    abstract ActivityLifeCycleTask bindScreenNavigatorTask(TestScreenNavigator screenNavigator);
}
