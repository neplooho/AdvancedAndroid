package com.example.eugene.advancedandroid.home;

import com.example.eugene.advancedandroid.di.ActivityScope;
import com.example.eugene.advancedandroid.ui.ActivityViewInterceptor;
import com.example.eugene.advancedandroid.ui.ActivityViewInterceptorModule;
import com.example.eugene.advancedandroid.ui.NavigationModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScope
@Subcomponent(modules = {
        MainScreenBindingModule.class,
        NavigationModule.class,
        ActivityViewInterceptorModule.class,
})
public interface MainActivityComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{

        @Override
        public void seedInstance(MainActivity instance) {

        }
    }
}
