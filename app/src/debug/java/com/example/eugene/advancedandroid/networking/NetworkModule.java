package com.example.eugene.advancedandroid.networking;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Call;
import okhttp3.OkHttpClient;

@Module
public abstract class NetworkModule {

    @Provides
    @Singleton
    static Call.Factory provideOkHttp(MockInterceptor mockInterceptor){
        return new OkHttpClient.Builder()
                .addInterceptor(mockInterceptor)
                .build();
    }

    @Provides
    @Named("base_url")
    static String provideBaseUrl(){
        return "https://api.github.com/";
    }
}
