package com.example.eugene.advancedandroid.ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.example.eugene.advancedandroid.details.RepoDetailsController;
import com.example.eugene.advancedandroid.di.ActivityScope;

import javax.inject.Inject;

public class DefaultScreenNavigator implements ScreenNavigator {


    private Router router;

    @Inject
    DefaultScreenNavigator(){

    }

    @Override
    public void initWithRouter(Router router, Controller rootScreen) {
        this.router = router;
        if(!router.hasRootController()){
            router.setRoot(RouterTransaction.with(rootScreen));
        }
    }

    @Override
    public boolean pop() {
        return router != null && router.handleBack();
    }

    @Override
    public void goToRepoDetails(String repoOwner, String repoName) {
        if (router != null){
            router.pushController(RouterTransaction
                    .with(RepoDetailsController.newInstance(repoName, repoOwner))
                    .pushChangeHandler(new FadeChangeHandler())
                    .popChangeHandler(new FadeChangeHandler()));
        }
    }

    @Override
    public void clear() {
        router = null;
    }
}
