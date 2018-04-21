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
import com.demo.recyclerview.fragment.adapter.MultiTypeAdapter;

public class MultiTypeFragment extends BaseFragment {

    private FragmentNormalBinding fragmentNormalBinding;

    public static MultiTypeFragment newInstance(int type) {
        MultiTypeFragment fragment = new MultiTypeFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentNormalBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_normal, container, false);
        return fragmentNormalBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView.LayoutManager layoutManager;
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
        fragmentNormalBinding.recyclerView.setAdapter(new MultiTypeAdapter(getActivity()));
        fragmentNormalBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
