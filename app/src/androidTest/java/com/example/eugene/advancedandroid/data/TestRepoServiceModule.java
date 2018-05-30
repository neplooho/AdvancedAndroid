package com.example.eugene.advancedandroid.data;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

@Module
public abstract class TestRepoServiceModule {

    @Binds
    abstract RepoService bindRepoService(TestRepoService repoService);

    @Provides
    @Named("network_scheduler")
    static Scheduler provideNetworkScheduler() {
        return Schedulers.trampoline();
    }
}
