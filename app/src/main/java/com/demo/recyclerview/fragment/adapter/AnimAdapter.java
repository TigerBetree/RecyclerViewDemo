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
import com.demo.recyclerview.databinding.ItemTextBinding;

import java.util.ArrayList;

public class AnimAdapter extends RecyclerView.Adapter<AnimAdapter.NormalViewHolder> {

    private ArrayList<String> mItems;

    public AnimAdapter(Activity activity) {
        this.mItems = Datas.getContents(activity);
        notifyItemRangeInserted(0, mItems.size());
    }

    public void addItem(int position) {
        mItems.add(position, "Add Item");
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public NormalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemTextBinding itemTextBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_text, parent, false);

        return new NormalViewHolder(itemTextBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final NormalViewHolder holder, int position) {
        holder.itemTextBinding.textView.setText(mItems.get(position));
        holder.itemTextBinding.cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int realPos = holder.getAdapterPosition();
                if (realPos % 2 == 1) {
                    addItem(realPos);
                } else {
                    removeItem(realPos);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mItems != null) {
            return mItems.size();
        }
        return 0;
    }

    static class NormalViewHolder extends RecyclerView.ViewHolder {

        ItemTextBinding itemTextBinding;

        public NormalViewHolder(ItemTextBinding binding) {
            super(binding.getRoot());
            this.itemTextBinding = binding;
        }
    }
}
