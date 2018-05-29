package com.example.eugene.advancedandroid.data;

import com.example.eugene.advancedandroid.model.Repo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import io.reactivex.Maybe;
import io.reactivex.Single;

@Singleton
public class RepoRepository {


    private final Provider<RepoRequester> repoRequesterProvider;
    private final List<Repo> cachedTrendingRepos = new ArrayList<>();

    @Inject
    RepoRepository(Provider<RepoRequester> repoRequesterProvider) {
        this.repoRequesterProvider = repoRequesterProvider;
    }

    public Single<List<Repo>> getTrendingRepos(){
        return Maybe.concat(cachedTrendingRepos(), apiTrendingRepos())
                .firstOrError();
    }

    public Single<Repo> getRepo(String repoOwner, String repoName){
        return Maybe.concat(cachedRepo(repoOwner, repoName), apiRepo(repoOwner, repoName))
                .firstOrError();
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

    private Maybe<List<Repo>> cachedTrendingRepos(){
        return Maybe.create(e -> {
            if (!cachedTrendingRepos.isEmpty()){
                e.onSuccess(cachedTrendingRepos);
            }
            e.onComplete();
        });
    }

    private Maybe<Repo> apiRepo(String repoOwner, String repoName) {
        return repoRequesterProvider.get().getRepo(repoOwner, repoName)
                .toMaybe();
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
