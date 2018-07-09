package com.example.eugene.advancedandroid.trending;

import com.example.eugene.advancedandroid.di.ScreenScope;
import com.example.eugene.advancedandroid.lifecycle.ScreenLifecycleTask;
import com.example.poweradapter.adapter.RecyclerDataSource;
import com.example.poweradapter.item.ItemRenderer;
import com.example.poweradapter.item.RecyclerItem;
import com.example.poweradapter.item.RenderKey;

import java.util.Map;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.IntoSet;

@Module
public abstract class TrendingReposScreenModule {

    @Binds
    @IntoSet
    abstract ScreenLifecycleTask bindUiManager(TrendingReposUiManager uiManager);

    @Binds
    @IntoMap
    @RenderKey("Repo")
    abstract ItemRenderer<? extends RecyclerItem> bindRepoRenderer(RepoRenderer repoRenderer);

    @Provides
    @ScreenScope
    static RecyclerDataSource provideRecyclerDataSource(Map<String, ItemRenderer<? extends RecyclerItem>> renderers) {
        return new RecyclerDataSource(renderers);
    }
}
