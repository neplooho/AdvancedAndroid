package com.example.eugene.advancedandroid.data;

import javax.inject.Inject;

import io.reactivex.Single;

public class TestRepoService implements RepoService {

    @Inject
    TestRepoService() {

    }
    @Override
    public Single<TrendingReposResponse> getTrendingRepos() {
        return null;
    }
}
