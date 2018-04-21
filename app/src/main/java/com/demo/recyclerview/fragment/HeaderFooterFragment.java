package com.demo.recyclerview.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.recyclerview.R;
import com.demo.recyclerview.databinding.FragmentNormalBinding;
import com.demo.recyclerview.fragment.adapter.HeaderFooterAdapter;

public class HeaderFooterFragment extends BaseFragment {
    public static HeaderFooterFragment newInstance(int type) {
        HeaderFooterFragment fragment = new HeaderFooterFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    private FragmentNormalBinding fragmentNormalBinding;
    RecyclerView.LayoutManager layoutManager;
    HeaderFooterAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentNormalBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_normal, container, false);
        return fragmentNormalBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        switch (type) {
            case TYPE_LINEAR_LAYOUT:
                layoutManager = new LinearLayoutManager(getActivity());
                break;
            case TYPE_GRID_LAYOUT:
                layoutManager = new GridLayoutManager(getActivity(), 2);
                break;
            case TYPE_STAGGERED_GRID_LAYOUT:
                layoutManager = new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);
                break;
            default:
                layoutManager = new LinearLayoutManager(getActivity());
                break;
        }

        fragmentNormalBinding.recyclerView.setLayoutManager(layoutManager);

        adapter = new HeaderFooterAdapter(getActivity());
        fragmentNormalBinding.recyclerView.setAdapter(adapter);

        fragmentNormalBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());

        //设置头部及底部View占据整行空间
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (adapter.isHeaderView(position) || adapter.isFooterView(position))
                            ? ((GridLayoutManager) layoutManager).getSpanCount() : 1;
                }
            });
        }
    }
}
