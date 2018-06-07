package com.example.eugene.advancedandroid.home;

import com.bluelinelabs.conductor.Controller;
import com.example.eugene.advancedandroid.R;
import com.example.eugene.advancedandroid.base.BaseActivity;
import com.example.eugene.advancedandroid.trending.TrendingReposController;

public class MainActivity extends BaseActivity {
    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public Controller initialScreen() {
        return new TrendingReposController();
    }
}
