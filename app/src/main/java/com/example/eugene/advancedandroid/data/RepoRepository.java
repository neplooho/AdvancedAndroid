package com.example.eugene.advancedandroid.data;

import com.example.eugene.advancedandroid.model.Contributor;
import com.example.eugene.advancedandroid.model.Repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;

import io.reactivex.Maybe;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class RepoRepository {


    private final Provider<RepoRequester> repoRequesterProvider;
    private Scheduler scheduler;
    private final List<Repo> cachedTrendingRepos = new ArrayList<>();
    private final Map<String, List<Contributor>> cachedContributors = new HashMap<>();

    @Inject
    RepoRepository(Provider<RepoRequester> repoRequesterProvider,
                   @Named("network_scheduler") Scheduler scheduler) {
        this.repoRequesterProvider = repoRequesterProvider;
        this.scheduler = scheduler;
    }

    public Single<List<Repo>> getTrendingRepos(){
        return Maybe.concat(cachedTrendingRepos(), apiTrendingRepos())
                .firstOrError()
                .subscribeOn(scheduler);
    }

    public Single<Repo> getRepo(String repoOwner, String repoName){
        return Maybe.concat(cachedRepo(repoOwner, repoName), apiRepo(repoOwner, repoName))
                .firstOrError()
                .subscribeOn(scheduler);
    }

    public void clearCache() {
        cachedTrendingRepos.clear();
        cachedContributors.clear();
    }

    public Single<List<Contributor>> getContributors(String url) {
        return Maybe.concat(cachedContributors(url), apiContributors(url))
                .firstOrError()
                .subscribeOn(scheduler);
    }

    private Maybe<List<Contributor>> cachedContributors(String url){
        return Maybe.create(e -> {
           if (cachedContributors.containsKey(url)){
               e.onSuccess(cachedContributors.get(url));
           }
           e.onComplete();
        });
    }

    private Maybe<List<Contributor>> apiContributors(String url) {
        return repoRequesterProvider.get().getContributors(url)
                .doOnSuccess(contributors -> cachedContributors.put(url, contributors))
                .toMaybe();
    }

    private Maybe<Repo> cachedRepo(String repoOwner, String repoName){
        return Maybe.create(e -> {
            for (Repo cachedRepo : cachedTrendingRepos) {
                if(cachedRepo.owner().login().equals(repoOwner)&&
                        cachedRepo.name().equals(repoName)){
                    e.onSuccess(cachedRepo);
                    break;
                }
            }
            e.onComplete();
        });
    }

    private Maybe<Repo> apiRepo(String repoOwner, String repoName) {
        return repoRequesterProvider.get().getRepo(repoOwner, repoName)
                .toMaybe();
    }

    private Maybe<List<Repo>> cachedTrendingRepos(){
        return Maybe.create(e -> {
            if (!cachedTrendingRepos.isEmpty()){
                e.onSuccess(cachedTrendingRepos);
            }
            e.onComplete();
        });
    }

    private Maybe<List<Repo>> apiTrendingRepos(){
        return repoRequesterProvider.get().getTrendingRepos()
                .doOnSuccess(repos -> {
                    cachedTrendingRepos.clear();
                    cachedTrendingRepos.addAll(repos);
                })
                .toMaybe();
    }


}
