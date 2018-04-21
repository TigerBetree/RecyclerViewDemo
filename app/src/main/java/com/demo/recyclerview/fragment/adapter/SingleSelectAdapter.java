package com.demo.recyclerview.fragment.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.recyclerview.Datas;
import com.demo.recyclerview.R;
import com.demo.recyclerview.databinding.ItemSingleSelectBinding;

import java.util.ArrayList;

public class SingleSelectAdapter extends RecyclerView.Adapter<SingleSelectAdapter.SingleSelectViewHolder> {

    protected int mCurrentSelect = -1;

    private ArrayList<String> items;

    public SingleSelectAdapter(Activity activity) {
        items = Datas.getContents(activity);
    }

    public void setCurrentSelect(int currentSelect) {
        if (mCurrentSelect > 0 && mCurrentSelect < getItemCount()) {
            notifyItemChanged(mCurrentSelect);
        }
        this.mCurrentSelect = currentSelect;
        notifyItemChanged(mCurrentSelect);
    }

    @NonNull
    @Override
    public SingleSelectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemSingleSelectBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_single_select, parent, false);

        return new SingleSelectViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleSelectViewHolder holder, final int position) {
        holder.itemBinding.tvName.setText(items.get(position));
        holder.itemBinding.ivCheck.setVisibility((position == mCurrentSelect) ? View.VISIBLE : View.GONE);
        holder.itemBinding.flCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentSelect(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    static class SingleSelectViewHolder extends RecyclerView.ViewHolder {

        public ItemSingleSelectBinding itemBinding;

        public SingleSelectViewHolder(ItemSingleSelectBinding binding) {
            super(binding.getRoot());
            itemBinding = binding;
        }
    }
}
