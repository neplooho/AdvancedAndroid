package com.example.eugene.advancedandroid.details;

import com.example.eugene.advancedandroid.lifecycle.ScreenLifecycleTask;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

@Module
public abstract class RepoDetailsScreenModule {

    @Binds
    @IntoSet
    abstract ScreenLifecycleTask bindUiManagerTask(RepoDetailsUiManager uiManager);
}
