package com.example.eugene.advancedandroid.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.eugene.advancedandroid.database.favorites.FavoriteContributor;
import com.example.eugene.advancedandroid.database.favorites.FavoriteContributorDao;

@Database(entities = FavoriteContributor.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FavoriteContributorDao favoriteContributorDao();
}
