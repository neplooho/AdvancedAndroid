package com.example.eugene.advancedandroid.trending;

import com.example.eugene.advancedandroid.data.RepoRepository;
import com.example.eugene.advancedandroid.di.ForScreen;
import com.example.eugene.advancedandroid.di.ScreenScope;
import com.example.eugene.advancedandroid.lifecycle.DisposableManager;
import com.example.eugene.advancedandroid.model.Repo;
import com.example.eugene.advancedandroid.ui.ScreenNavigator;
import com.example.poweradapter.adapter.RecyclerDataSource;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

@ScreenScope
class TrendingReposPresenter {

    private final TrendingReposViewModel viewModel;
    private RepoRepository repoRepository;
    private ScreenNavigator screenNavigator;
    private final DisposableManager disposableManager;
    private final RecyclerDataSource dataSource;

    @Inject
    TrendingReposPresenter(TrendingReposViewModel viewModel,
                           RepoRepository repoRepository,
                           ScreenNavigator screenNavigator,
                           @ForScreen DisposableManager disposableManager,
                           RecyclerDataSource dataSource) {
        this.viewModel = viewModel;
        this.repoRepository = repoRepository;
        this.screenNavigator = screenNavigator;
        this.disposableManager = disposableManager;
        this.dataSource = dataSource;

        loadRepos();
    }

    private void loadRepos(){
        disposableManager.add(repoRepository.getTrendingRepos()
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
                .doOnSuccess(__ -> viewModel.reposUpdated().run())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataSource::setData, viewModel.onError()));
    }

    void onRepoClicked(Repo repo) {
        screenNavigator.goToRepoDetails(repo.owner().login(), repo.name());
    }
}
