package com.example.eugene.advancedandroid.base;

import com.example.eugene.advancedandroid.data.RepoRepository;
import com.example.eugene.advancedandroid.data.TestRepoService;
import com.example.eugene.advancedandroid.data.TestRepoServiceModule;
import com.example.eugene.advancedandroid.networking.ServiceModule;
import com.example.eugene.advancedandroid.trending.TrendingReposControllerTest;
import com.example.eugene.advancedandroid.ui.NavigationModule;
import com.example.eugene.advancedandroid.ui.TestActivityViewInterceptorModule;
import com.example.eugene.advancedandroid.ui.TestNavigationModule;
import com.example.eugene.advancedandroid.ui.TestScreenNavigator;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        TestActivityBindingModule.class,
        TestRepoServiceModule.class,
        ServiceModule.class,
        TestNavigationModule.class,
        TestActivityViewInterceptorModule.class,
})
public interface TestApplicationComponent extends ApplicationComponent {


    void inject(TrendingReposControllerTest trendingReposControllerTest);

    TestScreenNavigator screenNavigator();

    TestRepoService repoService();

    RepoRepository repoRepository();
}
