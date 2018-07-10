package com.example.eugene.advancedandroid.database.favorites;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface FavoriteContributorDao {

    @Query("SELECT * FROM favoritecontributor")
    Flowable<List<FavoriteContributor>> getFavoritedContributors();

    @Insert
    void addFavorite(FavoriteContributor contributor);

    @Delete
    void deleteFavorite(FavoriteContributor contributor);
}
