package com.example.eugene.advancedandroid.test;

import android.content.Intent;

import com.bluelinelabs.conductor.Controller;
import com.example.eugene.advancedandroid.data.RepoRepository;
import com.example.eugene.advancedandroid.data.TestRepoService;
import com.example.eugene.advancedandroid.home.MainActivity;
import com.example.eugene.advancedandroid.ui.TestScreenNavigator;

import org.junit.Rule;

public abstract class ControllerTest {

    @Rule
    public ControllerTestRule<MainActivity> testRule = new ControllerTestRule<>(MainActivity.class);

    protected TestRepoService repoService = testRule.repoService;
    protected RepoRepository repoRepository = testRule.repoRepository;
    protected TestScreenNavigator screenNavigator = testRule.screenNavigator;

    public ControllerTest(){
        screenNavigator.overrideInitialController(controllerToLaunch());
    }

    protected abstract Controller controllerToLaunch();

    protected void launch() {
        launch(null);
    }

    protected void launch(Intent intent) {
        testRule.launchActivity(intent);
    }
}
