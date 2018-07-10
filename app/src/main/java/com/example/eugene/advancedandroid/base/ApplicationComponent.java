package com.example.eugene.advancedandroid.base;

import com.example.eugene.advancedandroid.data.RepoServiceModule;
import com.example.eugene.advancedandroid.database.DatabaseModule;
import com.example.eugene.advancedandroid.networking.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
        ServiceModule.class,
        RepoServiceModule.class,
        DatabaseModule.class,
})
public interface ApplicationComponent {
    void inject(MyApplication myApplication);
}
