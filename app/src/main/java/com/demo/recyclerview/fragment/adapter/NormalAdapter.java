package com.demo.recyclerview.fragment.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.demo.recyclerview.Datas;
import com.demo.recyclerview.R;
import com.demo.recyclerview.databinding.ItemTextBinding;

import java.util.List;

public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.NormalViewHolder> {

    private List<String> mItems;

    public NormalAdapter(Activity activity) {
        this.mItems = Datas.getContents(activity);
    }

    static class NormalViewHolder extends RecyclerView.ViewHolder {

        ItemTextBinding itemTextBinding;

        public NormalViewHolder(ItemTextBinding binding) {
            super(binding.getRoot());
            this.itemTextBinding = binding;
        }
    }

    @NonNull
    @Override
    public NormalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemTextBinding itemTextBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_text, parent, false);

        return new NormalViewHolder(itemTextBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NormalViewHolder holder, int position) {
        holder.itemTextBinding.textView.setText(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        if (mItems != null) {
            return mItems.size();
        }
        return 0;
    }
}
