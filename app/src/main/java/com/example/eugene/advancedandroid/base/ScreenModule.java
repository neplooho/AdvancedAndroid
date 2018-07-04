package com.example.eugene.advancedandroid.base;

import com.example.eugene.advancedandroid.di.ForScreen;
import com.example.eugene.advancedandroid.di.ScreenScope;
import com.example.eugene.advancedandroid.lifecycle.DisposableManager;
import com.example.eugene.advancedandroid.lifecycle.ScreenLifecycleTask;

import java.util.Set;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.Multibinds;

@Module
public abstract class ScreenModule {

    @Provides
    @ScreenScope
    @ForScreen
    static DisposableManager provideDisposableManager() {
        return new DisposableManager();
    }

    @Multibinds
    abstract Set<ScreenLifecycleTask> screenLifecycleTasks();
}
