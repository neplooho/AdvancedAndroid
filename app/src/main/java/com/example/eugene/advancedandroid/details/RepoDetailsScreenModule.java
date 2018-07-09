package com.example.eugene.advancedandroid.details;

import com.example.eugene.advancedandroid.di.ScreenScope;
import com.example.eugene.advancedandroid.lifecycle.ScreenLifecycleTask;
import com.example.eugene.advancedandroid.model.Contributor;
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
public abstract class RepoDetailsScreenModule {

    @Binds
    @IntoSet
    abstract ScreenLifecycleTask bindUiManagerTask(RepoDetailsUiManager uiManager);

    @Binds
    @IntoMap
    @RenderKey("Contributor")
    abstract ItemRenderer<? extends RecyclerItem> bindContributorRenderer(ContributorRenderer renderer);

    @Provides
    @ScreenScope
    static RecyclerDataSource provideDataSource(Map<String, ItemRenderer<? extends RecyclerItem>> renderers) {
        return new RecyclerDataSource(renderers);
    }
}
