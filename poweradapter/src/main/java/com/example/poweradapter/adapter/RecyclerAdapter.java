package com.example.poweradapter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private final RecyclerDataSource dataSource;

    public RecyclerAdapter(RecyclerDataSource dataSource) {
        this.dataSource = dataSource;
        dataSource.attachToAdapter(this);
        setHasStableIds(true);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(parent, dataSource.rendererForType(viewType));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.bind(dataSource.getItem(position));
    }

    @Override
    public int getItemCount() {
        return dataSource.getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return dataSource.viewResourceForPosition(position);
    }

    @Override
    public long getItemId(int position) {
        return dataSource.getItem(position).getId();
    }
}
