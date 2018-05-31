package com.example.eugene.advancedandroid.ui;

import com.example.eugene.advancedandroid.di.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class NavigationModule {

    @Binds
    @ActivityScope
    abstract ScreenNavigator provideScreenNavigator(DefaultScreenNavigator screenNavigator);
}
