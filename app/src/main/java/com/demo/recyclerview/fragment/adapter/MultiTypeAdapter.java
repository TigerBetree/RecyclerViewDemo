package com.demo.recyclerview.fragment.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.demo.recyclerview.Datas;
import com.demo.recyclerview.R;
import com.demo.recyclerview.databinding.ItemImageBinding;
import com.demo.recyclerview.databinding.ItemTextBinding;

import java.util.List;

public class MultiTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public enum ITEM_TYPE {
        ITEM_TYPE_IMAGE,
        ITEM_TYPE_TEXT
    }

    private List<String> mItems;

    public MultiTypeAdapter(Activity activity) {
        this.mItems = Datas.getContents(activity);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_TEXT.ordinal()) {
            return new TextViewHolder((ItemTextBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.item_text, parent, false));
        } else {
            return new ImageViewHolder((ItemImageBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.item_image, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ImageViewHolder) {
            ((ImageViewHolder) holder).imageBinding.textView.setText(mItems.get(position));
        } else {
            ((TextViewHolder) holder).textBinding.textView.setText(mItems.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal() : ITEM_TYPE.ITEM_TYPE_TEXT.ordinal();
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    static class TextViewHolder extends RecyclerView.ViewHolder {

        ItemTextBinding textBinding;

        public TextViewHolder(ItemTextBinding binding) {
            super(binding.getRoot());
            textBinding = binding;
        }
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {

        ItemImageBinding imageBinding;

        public ImageViewHolder(ItemImageBinding binding) {
            super(binding.getRoot());
            imageBinding = binding;
        }
    }
}
