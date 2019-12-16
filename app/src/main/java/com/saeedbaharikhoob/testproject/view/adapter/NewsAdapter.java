package com.saeedbaharikhoob.testproject.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.saeedbaharikhoob.testproject.R;

import com.saeedbaharikhoob.testproject.databinding.ItemPostBinding;
import com.saeedbaharikhoob.testproject.model.Post;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.CustomHolder> {

    private ArrayList<Post> items = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private OnItemClick onItemClick;

    @NonNull
    @Override
    public CustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        ItemPostBinding itemProductBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_post,parent,false);
        return new CustomHolder(itemProductBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomHolder holder, int position) {

        holder.bind(items.get(position));
        holder.setOnClickListener(onItemClick);
    }
    public void setItems(ArrayList<Post> posts) {
        this.items.addAll(posts);
        notifyDataSetChanged();
    }
    public void setOnClickListener(OnItemClick onClick) {
        this.onItemClick = onClick;
    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    class CustomHolder extends RecyclerView.ViewHolder {

        private ItemPostBinding itemPostBinding;
        private OnItemClick onItemClick;
        private CardView cardView;
        private Post post;

        public CustomHolder(ItemPostBinding itemPostBinding) {
            super(itemPostBinding.getRoot());
            this.itemPostBinding = itemPostBinding;
            cardView = itemPostBinding.card;
            cardView.setOnClickListener(v -> onItemClick.onClick(post,v));
        }

        private void bind(final Post post) {
            this.post = post;
            this.itemPostBinding.setItem(post);
            this.itemPostBinding.executePendingBindings();

        }
        public void setOnClickListener(OnItemClick onClick) {
            this.onItemClick = onClick;
        }

    }
    public interface OnItemClick {

        void onClick(Post post, View view);
    }
}
