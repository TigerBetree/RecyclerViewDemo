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

public class MultiSelectAdapter extends RecyclerView.Adapter<MultiSelectAdapter.SingleSelectViewHolder> {

    protected ArrayList<Integer> mMultiSelectPositions = new ArrayList<>();

    private ArrayList<String> items;

    private boolean isActionModeShow = false;

    private OnActionModeCallBack onActionModeCallBack;

    public interface OnActionModeCallBack {
        void showActionMode();
    }

    public MultiSelectAdapter(Activity activity) {
        items = Datas.getContents(activity);
    }

    @NonNull
    @Override
    public SingleSelectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemSingleSelectBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_single_select, parent, false);

        return new SingleSelectViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final SingleSelectViewHolder holder, final int position) {
        holder.itemBinding.tvName.setText(items.get(position));
        holder.itemBinding.ivCheck.setVisibility(isSelected(position) ? View.VISIBLE : View.GONE);
        holder.itemBinding.flCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelected(holder.getAdapterPosition());
            }
        });
        holder.itemBinding.flCheck.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (isActionModeShow) {//已显示选择模式
                    onSelected(holder.getAdapterPosition());
                } else {
                    if (onActionModeCallBack != null) {
                        onActionModeCallBack.showActionMode();
                    }
                }
                return true;
            }
        });
    }

    void onSelected(int position) {
        if (isActionModeShow) {
            if (isSelected(position)) {//已选中
                removeSelectPosition(position);
            } else {//未选中
                addSelectPosition(position);
            }
            notifyItemChanged(position);
        }
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

    public void setOnActionModeCallBack(OnActionModeCallBack onActionModeCallBack) {
        this.onActionModeCallBack = onActionModeCallBack;
    }

    public void setIsActionModeShow(boolean isActionModeShow) {
        this.isActionModeShow = isActionModeShow;
        if (!isActionModeShow) {
            clearAllSelect();
        }
    }

    /**
     * 是否选中项
     */
    public boolean isSelected(Integer data) {
        return mMultiSelectPositions.contains(data);
    }

    /**
     * 添加选中项
     */
    public void addSelectPosition(Integer data) {
        if (!mMultiSelectPositions.contains(data)) {
            mMultiSelectPositions.add(data);
        }
    }

    /**
     * 删除选中项
     */
    public void removeSelectPosition(Integer data) {
        if (mMultiSelectPositions.contains(data)) {
            mMultiSelectPositions.remove(data);
        }
    }

    /**
     * 清除所有选中项
     */
    public void clearAllSelect() {
        mMultiSelectPositions.clear();
        notifyDataSetChanged();
    }

    public void removeAll(ArrayList<String> deleteItems) {
        items.removeAll(deleteItems);
    }

    public ArrayList<Integer> getMultiSelectPositions() {
        return mMultiSelectPositions;
    }

    public String getItemData(int position) {
        return position < items.size() ? items.get(position) : null;
    }
}
