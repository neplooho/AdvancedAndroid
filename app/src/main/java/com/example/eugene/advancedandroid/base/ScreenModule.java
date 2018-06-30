package com.example.eugene.advancedandroid.base;

import com.example.eugene.advancedandroid.lifecycle.ScreenLifecycleTask;

import java.util.Set;

import dagger.Module;
import dagger.multibindings.Multibinds;

@Module
public abstract class ScreenModule {

    @Multibinds
    abstract Set<ScreenLifecycleTask> screenLifecycleTasks();
}
