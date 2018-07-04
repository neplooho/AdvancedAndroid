package com.example.eugene.advancedandroid.di;

import com.example.eugene.advancedandroid.lifecycle.DisposableManager;

import dagger.android.AndroidInjector;

public interface ScreenComponent<T> extends AndroidInjector<T> {

    @ForScreen DisposableManager disposableManager();
}
