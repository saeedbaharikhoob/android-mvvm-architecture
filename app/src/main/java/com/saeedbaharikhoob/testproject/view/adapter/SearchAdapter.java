package com.saeedbaharikhoob.testproject.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.saeedbaharikhoob.testproject.R;
import com.saeedbaharikhoob.testproject.databinding.ItemSearchBinding;
import com.saeedbaharikhoob.testproject.model.Post;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.CustomHolder> {

    private ArrayList<Post> items = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private OnItemClick onItemClick;

    @NonNull
    @Override
    public CustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        ItemSearchBinding itemSearchBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_search, parent, false);
        return new CustomHolder(itemSearchBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomHolder holder, int position) {

        holder.bind(items.get(position));
        holder.setOnClickListener(onItemClick);
    }

    public void setItems(ArrayList<Post> posts) {
        this.items = posts;
        notifyDataSetChanged();
    }
    public void clearItems() {
        this.items.clear();
    }
    public void setOnClickListener(OnItemClick onClick) {
        this.onItemClick = onClick;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class CustomHolder extends RecyclerView.ViewHolder {

        private ItemSearchBinding itemSearchBinding;
        private OnItemClick onItemClick;
        private CardView cardView;
        private Post post;

        public CustomHolder(ItemSearchBinding itemSearchBinding) {
            super(itemSearchBinding.getRoot());
            this.itemSearchBinding = itemSearchBinding;
            cardView = itemSearchBinding.card;
            cardView.setOnClickListener(v -> onItemClick.onClick(post, v));
        }

        private void bind(final Post post) {
            this.post = post;
            this.itemSearchBinding.setItem(post);
            this.itemSearchBinding.executePendingBindings();

        }

        public void setOnClickListener(OnItemClick onClick) {
            this.onItemClick = onClick;
        }

    }

    public interface OnItemClick {

        void onClick(Post post, View view);
    }
}
