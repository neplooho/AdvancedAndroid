package com.example.eugene.advancedandroid.details;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.eugene.advancedandroid.R;
import com.example.eugene.advancedandroid.database.favorites.FavoriteService;
import com.example.eugene.advancedandroid.model.Contributor;
import com.example.poweradapter.item.ItemRenderer;
import com.jakewharton.rxbinding2.view.RxView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnLongClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class ContributorRenderer implements ItemRenderer<Contributor> {

    private final FavoriteService favoriteService;

    @Inject ContributorRenderer(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @Override
    public int layoutRes() {
        return R.layout.view_user_list_item;
    }

    @Override
    public View createView(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutRes(), parent, false);
        view.setTag(new ViewBinder(view, favoriteService));
        return view;
    }

    @Override
    public void render(@NonNull View itemView, @NonNull Contributor item) {
        ((ViewBinder) itemView.getTag()).bind(item);
    }

    static class ViewBinder {

        @BindView(R.id.tv_user_name) TextView usernameText;
        @BindView(R.id.iv_avatar) ImageView avatarImageView;
        @BindView(R.id.parent_view) View parentView;

        private final FavoriteService favoriteService;

        private Contributor contributor;
        private Disposable favoriteDisposable;

        ViewBinder(View itemView, FavoriteService favoriteService) {
            this.favoriteService = favoriteService;
            ButterKnife.bind(this, itemView);
            RxView.attachEvents(parentView)
                    .subscribe(event -> {
                        if (event.view().isAttachedToWindow()) {
                            listenForFavoriteChanges();
                        } else {
                            if (favoriteDisposable != null) {
                                favoriteDisposable.dispose();
                                favoriteDisposable = null;
                            }
                        }
                    });
        }

        @OnLongClick(R.id.parent_view) boolean toggleFavorite() {
            if (contributor != null) {
                favoriteService.toggleFavoriteContributor(contributor);
            }
            return true;
        }

        private void listenForFavoriteChanges() {
            favoriteDisposable = favoriteService.favoriteContributorIds()
                    .filter(__ -> contributor != null)
                    .map(favoriteIds -> favoriteIds.contains(contributor.id()))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(isFavorite -> parentView.setBackgroundColor(isFavorite ? Color.YELLOW : Color.TRANSPARENT));
        }

        void bind(Contributor contributor) {
            this.contributor = contributor;
            usernameText.setText(contributor.login());
            Glide.with(avatarImageView.getContext())
                    .load(contributor.avatarUrl())
                    .into(avatarImageView);
        }
    }
}
