package com.example.eugene.advancedandroid.database.favorites;

import com.example.eugene.advancedandroid.database.AppDatabase;
import com.example.eugene.advancedandroid.model.Contributor;
import com.jakewharton.rxrelay2.BehaviorRelay;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@Singleton
public class FavoriteService {

    private final BehaviorRelay<Set<Long>> favoritedContributorsIdRelay = BehaviorRelay.createDefault(new HashSet<>());
    private final AppDatabase appDatabase;

    @Inject
    FavoriteService(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
        appDatabase.favoriteContributorDao().getFavoritedContributors()
                .subscribeOn(Schedulers.io())
                .map(favoriteContributors -> {
                    Set<Long> contributorIds = new HashSet<>();
                    for (FavoriteContributor favoriteContributor : favoriteContributors) {
                        contributorIds.add(favoriteContributor.getId());
                    }
                    return contributorIds;
                })
                .subscribe(favoritedContributorsIdRelay, throwable -> Timber.e(throwable, "Error loading favorited " +
                        "contributors from database"));
    }

    public Observable<Set<Long>> favoriteContributorIds() {
        return favoritedContributorsIdRelay;
    }

    public void toggleFavoriteContributor(Contributor contributor) {
        runDbOp(() -> {
            if (favoritedContributorsIdRelay.getValue().contains(contributor.getId())){
                deleteFavoriteContributor(contributor);
            } else {
                addFavoriteContributor(contributor);
            }
        });
    }

    private void addFavoriteContributor(Contributor contributor) {
        appDatabase.favoriteContributorDao().addFavorite(new FavoriteContributor(contributor.id()));
    }

    private void deleteFavoriteContributor(Contributor contributor) {
        appDatabase.favoriteContributorDao().deleteFavorite(new FavoriteContributor(contributor.id()));
    }

    private void runDbOp(Action action) {
        Completable.fromAction(action)
                .subscribeOn(Schedulers.io())
                .subscribe(() -> {}, throwable -> Timber.e(throwable, "Error performing database operation"));
    }
}
