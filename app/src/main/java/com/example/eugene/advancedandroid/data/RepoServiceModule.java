package com.example.eugene.advancedandroid.data;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

@Module
public abstract class RepoServiceModule {

    @Provides
    @Singleton
    static RepoService provideRepoService(Retrofit retrofit){
        return retrofit.create(RepoService.class);
    }

    @Provides
    @Named("network_scheduler")
    static Scheduler provideNetworkScheduler() {
        return Schedulers.io();
    }
}
