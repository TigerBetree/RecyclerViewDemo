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

public class HeaderFooterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public enum ITEM_TYPE {
        ITEM_TYPE_HEADER,
        ITEM_TYPE_CONTENT,
        ITEM_TYPE_FOOTER
    }

    private List<String> mItems;

    protected int mHeaderCount;//头部View个数
    protected int mFooterCount;//底部View个数

    public HeaderFooterAdapter(Activity activity) {
        this.mItems = Datas.getContents(activity);
        mHeaderCount = 1;
        mFooterCount = 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_HEADER.ordinal()) {
            return new HeaderViewHolder((ItemImageBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.item_image, parent, false));
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_FOOTER.ordinal()) {
            return new FooterViewHolder((ItemImageBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.item_image, parent, false));
        } else {
            return new TextViewHolder((ItemTextBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.item_text, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            ((HeaderViewHolder) holder).imageBinding.textView.setText("Header");
        } else if (holder instanceof FooterViewHolder) {
            ((FooterViewHolder) holder).imageBinding.textView.setText("Footer");
        } else {
            ((TextViewHolder) holder).textBinding.textView.setText(mItems.get(position - mHeaderCount));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return ITEM_TYPE.ITEM_TYPE_HEADER.ordinal();
        } else if (isFooterView(position)) {
            return ITEM_TYPE.ITEM_TYPE_FOOTER.ordinal();
        } else {
            return ITEM_TYPE.ITEM_TYPE_CONTENT.ordinal();
        }
    }

    @Override
    public int getItemCount() {
        return mHeaderCount + mFooterCount + getContentItemCount();
    }

    public boolean isHeaderView(int position) {
        return mHeaderCount != 0 && position < mHeaderCount;
    }

    public boolean isFooterView(int position) {
        return mFooterCount != 0 && position >= (mHeaderCount + getContentItemCount());
    }

    private int getContentItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    static class TextViewHolder extends RecyclerView.ViewHolder {

        ItemTextBinding textBinding;

        public TextViewHolder(ItemTextBinding binding) {
            super(binding.getRoot());
            textBinding = binding;
        }
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {

        ItemImageBinding imageBinding;

        public HeaderViewHolder(ItemImageBinding binding) {
            super(binding.getRoot());
            imageBinding = binding;
        }
    }

    static class FooterViewHolder extends RecyclerView.ViewHolder {

        ItemImageBinding imageBinding;

        public FooterViewHolder(ItemImageBinding binding) {
            super(binding.getRoot());
            imageBinding = binding;
        }
    }
}
