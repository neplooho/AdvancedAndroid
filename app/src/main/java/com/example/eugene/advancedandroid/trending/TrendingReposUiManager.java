package com.example.eugene.advancedandroid.trending;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.eugene.advancedandroid.R;
import com.example.eugene.advancedandroid.di.ScreenScope;
import com.example.eugene.advancedandroid.lifecycle.ScreenLifecycleTask;
import com.example.eugene.advancedandroid.util.ButterKnifeUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@ScreenScope
public class TrendingReposUiManager extends ScreenLifecycleTask {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Unbinder unbinder;

    @Inject
    TrendingReposUiManager() {

    }

    @Override
    public void onEnterScope(View view) {
        unbinder = ButterKnife.bind(this, view);
        toolbar.setTitle(R.string.screen_title_trending);
    }

    @Override
    public void onExitScope() {
        ButterKnifeUtils.unbind(unbinder);
    }
}
