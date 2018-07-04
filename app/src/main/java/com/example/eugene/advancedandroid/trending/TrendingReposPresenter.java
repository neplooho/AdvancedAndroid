package com.example.eugene.advancedandroid.trending;

import com.example.eugene.advancedandroid.data.RepoRepository;
import com.example.eugene.advancedandroid.di.ForScreen;
import com.example.eugene.advancedandroid.di.ScreenScope;
import com.example.eugene.advancedandroid.lifecycle.DisposableManager;
import com.example.eugene.advancedandroid.model.Repo;
import com.example.eugene.advancedandroid.ui.ScreenNavigator;

import javax.inject.Inject;

@ScreenScope
class TrendingReposPresenter implements RepoAdapter.RepoClickedListener {

    private final TrendingReposViewModel viewModel;
    private RepoRepository repoRepository;
    private ScreenNavigator screenNavigator;
    private final DisposableManager disposableManager;

    @Inject
    TrendingReposPresenter(TrendingReposViewModel viewModel,
                           RepoRepository repoRepository,
                           ScreenNavigator screenNavigator,
                           @ForScreen DisposableManager disposableManager) {
        this.viewModel = viewModel;
        this.repoRepository = repoRepository;
        this.screenNavigator = screenNavigator;
        this.disposableManager = disposableManager;

        loadRepos();
    }

    private void loadRepos(){
        disposableManager.add(repoRepository.getTrendingRepos()
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.reposUpdated(), viewModel.onError()));
    }

    @Override
    public void onRepoClicked(Repo repo) {
        screenNavigator.goToRepoDetails(repo.owner().login(), repo.name());
    }
}
