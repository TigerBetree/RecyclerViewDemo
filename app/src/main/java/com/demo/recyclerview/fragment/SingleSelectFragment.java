package com.demo.recyclerview.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.demo.recyclerview.R;
import com.demo.recyclerview.databinding.FragmentNormalBinding;
import com.demo.recyclerview.fragment.adapter.SingleSelectAdapter;
import com.demo.recyclerview.view.DividerItemDecoration;

public class SingleSelectFragment extends Fragment {

    public static SingleSelectFragment newInstance() {
        SingleSelectFragment fragment = new SingleSelectFragment();
        return fragment;
    }

    private FragmentNormalBinding fragmentNormalBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentNormalBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_normal, container, false);
        return fragmentNormalBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        fragmentNormalBinding.recyclerView.setLayoutManager(layoutManager);
        fragmentNormalBinding.recyclerView.setAdapter(new SingleSelectAdapter(getActivity()));
        fragmentNormalBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        fragmentNormalBinding.recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
    }
}
