package com.saeedbaharikhoob.testproject.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.saeedbaharikhoob.testproject.R;
import com.saeedbaharikhoob.testproject.databinding.ItemStateBinding;
import com.saeedbaharikhoob.testproject.model.State;

import java.util.ArrayList;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.CustomHolder> {

    private ArrayList<State> items = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private OnItemClick onItemClick;

    @NonNull
    @Override
    public CustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        ItemStateBinding itemStateBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_state,parent,false);
        return new CustomHolder(itemStateBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomHolder holder, int position) {

        holder.bind(items.get(position));
        holder.setOnClickListener(onItemClick);
    }
    public void setItems(ArrayList<State> states) {
        this.items.addAll(states);
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

        private ItemStateBinding itemStateBinding;
        private OnItemClick onItemClick;
        private CardView cardView;
        private State state;

        public CustomHolder(ItemStateBinding itemStateBinding) {
            super(itemStateBinding.getRoot());
            this.itemStateBinding = itemStateBinding;
            cardView = itemStateBinding.card;
            cardView.setOnClickListener(v -> onItemClick.onClick(state,v));
        }

        private void bind(final State state) {
            this.state = state;
            this.itemStateBinding.setItem(state);
            this.itemStateBinding.executePendingBindings();

        }
        public void setOnClickListener(OnItemClick onClick) {
            this.onItemClick = onClick;
        }

    }
    public interface OnItemClick {

        void onClick(State state, View view);
    }
}
