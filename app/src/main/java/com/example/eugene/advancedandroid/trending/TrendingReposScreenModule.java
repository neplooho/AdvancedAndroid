package com.example.eugene.advancedandroid.trending;

import com.example.eugene.advancedandroid.lifecycle.ScreenLifecycleTask;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

@Module
public abstract class TrendingReposScreenModule {

    @Binds
    @IntoSet
    abstract ScreenLifecycleTask bindUiManager(TrendingReposUiManager uiManager);
}
