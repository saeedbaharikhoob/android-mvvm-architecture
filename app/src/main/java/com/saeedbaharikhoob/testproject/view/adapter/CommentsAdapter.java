package com.saeedbaharikhoob.testproject.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.saeedbaharikhoob.testproject.R;
import com.saeedbaharikhoob.testproject.databinding.ItemCommentBinding;
import com.saeedbaharikhoob.testproject.model.Comment;


import java.util.ArrayList;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CustomHolder> {

    private ArrayList<Comment> items = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private OnItemClick onItemClick;

    @NonNull
    @Override
    public CustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        ItemCommentBinding itemCommentBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_comment,parent,false);
        return new CustomHolder(itemCommentBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomHolder holder, int position) {

        holder.bind(items.get(position));
        holder.setOnClickListener(onItemClick);
    }
    public void setItems(ArrayList<Comment> comments) {
        this.items.clear();
        this.items.addAll(comments);
        notifyDataSetChanged();
    }
    public void setItem(Comment comment) {
        this.items.add(comment);
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

        private ItemCommentBinding itemCommentBinding;
        private OnItemClick onItemClick;
        private CardView cardView;
        private Comment comment;

        public CustomHolder(ItemCommentBinding itemCommentBinding) {
            super(itemCommentBinding.getRoot());
            this.itemCommentBinding = itemCommentBinding;
            cardView = itemCommentBinding.card;
        }

        private void bind(final Comment comment) {
            this.comment = comment;
            this.itemCommentBinding.setItem(comment);
            this.itemCommentBinding.executePendingBindings();

        }
        public void setOnClickListener(OnItemClick onClick) {
            this.onItemClick = onClick;
        }

    }
    public interface OnItemClick {

        void onClick(Comment comment, View view);
    }
}
