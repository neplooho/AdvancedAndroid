package com.example.eugene.advancedandroid.details;

import com.example.eugene.advancedandroid.data.RepoRepository;
import com.example.eugene.advancedandroid.di.ForScreen;
import com.example.eugene.advancedandroid.di.ScreenScope;
import com.example.eugene.advancedandroid.lifecycle.DisposableManager;
import com.example.poweradapter.adapter.RecyclerDataSource;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.android.schedulers.AndroidSchedulers;

@ScreenScope
class RepoDetailsPresenter {

    @Inject
    RepoDetailsPresenter(
            @Named("repo_owner") String repoOwner,
            @Named("repo_name") String repoName,
            RepoRepository repoRepository,
            RepoDetailsViewModel viewModel,
            @ForScreen DisposableManager disposableManager,
            RecyclerDataSource contributorDataSource) {
            disposableManager.add(repoRepository.getRepo(repoOwner, repoName)
                .doOnSuccess(viewModel.processRepo())
                .doOnError(viewModel.detailsError())
                .flatMap(repo -> repoRepository.getContributors(repo.contributorsUrl())
                    .doOnError(viewModel.contributorsError()))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(contributorDataSource::setData)
                .subscribe(viewModel.contributorsLoaded(), throwable -> {
                    //We handle logging in the view model
                }));
    }
}
