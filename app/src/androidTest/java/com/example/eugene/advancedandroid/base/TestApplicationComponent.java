package com.example.eugene.advancedandroid.base;

import com.example.eugene.advancedandroid.data.TestRepoServiceModule;
import com.example.eugene.advancedandroid.networking.ServiceModule;
import com.example.eugene.advancedandroid.ui.NavigationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        TestActivityBindingModule.class,
        TestRepoServiceModule.class,
        ServiceModule.class,
        NavigationModule.class,
})
public interface TestApplicationComponent extends ApplicationComponent {


}
