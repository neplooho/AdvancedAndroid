package com.example.eugene.advancedandroid.ui;

import android.support.v7.app.AppCompatActivity;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.example.eugene.advancedandroid.lifecycle.ActivityLifeCycleTask;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TestScreenNavigator extends ActivityLifeCycleTask implements ScreenNavigator {

    private final DefaultScreenNavigator defaultScreenNavigator;
    private Controller overrideController;

    @Inject
    public TestScreenNavigator() {
        this.defaultScreenNavigator = new DefaultScreenNavigator();
    }

    /**
     * Set the Controller to launch when the Activity attaches to the ScreenNavigator. This will
     * be used instead of the Controller provided by {@link RouterProvider#initialScreen()}
     *
     * @param overrideController Controller to launch when Activity starts. Or null to fall back to default.
     */

    public void overrideInitialController(Controller overrideController) {
        this.overrideController = overrideController;
    }

    @Override
    public void onCreate(AppCompatActivity activity) {
        if (!(activity instanceof RouterProvider)) {
            throw new IllegalArgumentException("Activity must be instance of RouterProvider");
        }
        Controller launchController = overrideController == null ? ((RouterProvider) activity).initialScreen() : overrideController;
        defaultScreenNavigator.initWithRouter(((RouterProvider) activity).getRouter(), launchController);
    }

    @Override
    public boolean pop() {
        return defaultScreenNavigator.pop();
    }

    @Override
    public void goToRepoDetails(String repoOwner, String repoName) {
        defaultScreenNavigator.goToRepoDetails(repoOwner, repoName);
    }

    @Override
    public void onDestroy(AppCompatActivity activity) {
        defaultScreenNavigator.onDestroy(activity);
    }
}
