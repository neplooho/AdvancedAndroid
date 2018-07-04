package com.example.eugene.advancedandroid.trending;

import com.example.eugene.advancedandroid.base.ScreenModule;
import com.example.eugene.advancedandroid.di.ScreenComponent;
import com.example.eugene.advancedandroid.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent(modules = {
        ScreenModule.class,
        TrendingReposScreenModule.class,
})
public interface TrendingReposComponent extends ScreenComponent<TrendingReposController> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<TrendingReposController>{

    }
}
