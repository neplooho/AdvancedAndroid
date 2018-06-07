package com.example.eugene.advancedandroid.ui;

import com.example.eugene.advancedandroid.di.ActivityScope;
import com.example.eugene.advancedandroid.lifecycle.ActivityLifeCycleTask;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;

@Module
public abstract class NavigationModule {

    @Binds
    abstract ScreenNavigator provideScreenNavigator(DefaultScreenNavigator screenNavigator);

    @Binds
    @IntoSet
    abstract ActivityLifeCycleTask bindScreenNavigatorTask(DefaultScreenNavigator screenNavigator);
}
