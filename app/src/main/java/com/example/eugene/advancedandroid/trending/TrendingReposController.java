package com.example.eugene.advancedandroid.trending;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.eugene.advancedandroid.R;
import com.example.eugene.advancedandroid.base.BaseController;
import com.example.poweradapter.adapter.RecyclerAdapter;
import com.example.poweradapter.adapter.RecyclerDataSource;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class TrendingReposController extends BaseController {

    @Inject TrendingReposPresenter presenter;
    @Inject TrendingReposViewModel viewModel;
    @Inject RecyclerDataSource dataSource;

    @BindView(R.id.repo_list)
    RecyclerView repoList;
    @BindView(R.id.loading_indicator)
    View loadingView;
    @BindView(R.id.tv_error)
    TextView errorText;

    @Override
    protected void onViewBound(View view) {
        repoList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        repoList.setAdapter(new RecyclerAdapter(dataSource));
    }

    @Override
    protected Disposable[] subscriptions() {
        return new Disposable[]{
                viewModel.loading()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loading -> {
                    loadingView.setVisibility(loading ? View.VISIBLE : View.GONE);
                    repoList.setVisibility(loading ? View.GONE : View.VISIBLE);
                    errorText.setVisibility(loading ? View.GONE : errorText.getVisibility());
                }),
                viewModel.error()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(errorRes -> {
                        if (errorRes == -1){
                            errorText.setText(null);
                            errorText.setVisibility(View.GONE);
                        }else{
                            errorText.setVisibility(View.VISIBLE);
                            repoList.setVisibility(View.GONE);
                            errorText.setText(errorRes);
                        }
                })
        };
    }

    @Override
    protected int layoutRes() {
        return R.layout.screen_trending_repos;
    }
}
