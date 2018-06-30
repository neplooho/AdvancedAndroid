package com.example.eugene.advancedandroid.details;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.eugene.advancedandroid.R;
import com.example.eugene.advancedandroid.di.ScreenScope;
import com.example.eugene.advancedandroid.lifecycle.ScreenLifecycleTask;
import com.example.eugene.advancedandroid.ui.ScreenNavigator;
import com.example.eugene.advancedandroid.util.ButterKnifeUtils;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@ScreenScope
public class RepoDetailsUiManager extends ScreenLifecycleTask {

    private final String repoName;
    private final ScreenNavigator screenNavigator;
    @BindView(R.id.toolbar) Toolbar toolbar;
    private Unbinder unbinder;

    @Inject
    RepoDetailsUiManager(@Named("repo_name") String repoName, ScreenNavigator screenNavigator) {

        this.repoName = repoName;
        this.screenNavigator = screenNavigator;
    }

    @Override
    public void onEnterScope(View view) {
        unbinder = ButterKnife.bind(this, view);
        toolbar.setTitle(repoName);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setOnClickListener(v -> screenNavigator.pop());
    }

    @Override
    public void onExitScope() {
        toolbar.setNavigationOnClickListener(null);
        ButterKnifeUtils.unbind(unbinder);
    }
}
