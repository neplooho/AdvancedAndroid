package com.example.eugene.advancedandroid.details;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.eugene.advancedandroid.R;
import com.example.eugene.advancedandroid.model.Contributor;
import com.example.poweradapter.item.ItemRenderer;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContributorRenderer implements ItemRenderer<Contributor> {

    @Inject ContributorRenderer() {

    }

    @Override
    public int layoutRes() {
        return R.layout.view_user_list_item;
    }

    @Override
    public View createView(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutRes(), parent, false);
        view.setTag(new ViewBinder(view));
        return view;
    }

    @Override
    public void render(@NonNull View itemView, @NonNull Contributor item) {
        ((ViewBinder) itemView.getTag()).bind(item);
    }

    static class ViewBinder {

        @BindView(R.id.tv_user_name) TextView usernameText;
        @BindView(R.id.iv_avatar) ImageView avatarImageView;

        ViewBinder(View itemView) {
            ButterKnife.bind(this, itemView);
        }

        void bind(Contributor contributor) {
            usernameText.setText(contributor.login());
            Glide.with(avatarImageView.getContext())
                    .load(contributor.avatarUrl())
                    .into(avatarImageView);
        }
    }
}
