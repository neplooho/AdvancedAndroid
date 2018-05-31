package com.example.eugene.advancedandroid.test;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;

import com.example.eugene.advancedandroid.base.TestApplication;
import com.example.eugene.advancedandroid.data.RepoRepository;
import com.example.eugene.advancedandroid.data.TestRepoService;
import com.example.eugene.advancedandroid.ui.TestScreenNavigator;

public class ControllerTestRule<T extends Activity> extends ActivityTestRule<T> {

    public final TestScreenNavigator screenNavigator;
    public final TestRepoService repoService;
    public final RepoRepository repoRepository;

    public ControllerTestRule(Class<T> activityClass) {
        super(activityClass, true, false);
        screenNavigator = TestApplication.getComponent().screenNavigator();
        repoService = TestApplication.getComponent().repoService();
        repoRepository = TestApplication.getComponent().repoRepository();
    }

    public void clearState() {
        repoRepository.clearCache();
    }
}
