package com.demo.recyclerview.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.demo.recyclerview.R;
import com.demo.recyclerview.databinding.FragmentNormalBinding;
import com.demo.recyclerview.fragment.adapter.MultiSelectAdapter;
import com.demo.recyclerview.view.DividerItemDecoration;

import java.util.ArrayList;

public class MultiSelectFragment extends Fragment {

    public static MultiSelectFragment newInstance() {
        return new MultiSelectFragment();
    }

    private FragmentNormalBinding fragmentNormalBinding;
    private MultiSelectAdapter multiSelectAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentNormalBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_normal, container, false);
        return fragmentNormalBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentNormalBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//这里用线性显示 类似于list view
        multiSelectAdapter = new MultiSelectAdapter(getActivity());
        fragmentNormalBinding.recyclerView.setAdapter(multiSelectAdapter);
        fragmentNormalBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        fragmentNormalBinding.recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));

        multiSelectAdapter.setOnActionModeCallBack(new MultiSelectAdapter.OnActionModeCallBack() {
            @Override
            public void showActionMode() {
                multiSelectAdapter.setIsActionModeShow(true);
                AppCompatActivity activity = (AppCompatActivity) getActivity();
                activity.startSupportActionMode(mDeleteMode);
            }
        });
    }

    private ActionMode.Callback mDeleteMode = new ActionMode.Callback() {
        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            multiSelectAdapter.setIsActionModeShow(false);
        }

        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            getActivity().getMenuInflater().inflate(R.menu.menu_delete, menu);
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_delete:
                    onDeleteItems();
                    actionMode.finish();
                    return true;
                default:
                    break;
            }
            return false;
        }
    };

    private void onDeleteItems() {
        ArrayList<String> deleteItems = new ArrayList<>();
        for (Integer integer : multiSelectAdapter.getMultiSelectPositions()) {
            deleteItems.add(multiSelectAdapter.getItemData(integer));
            multiSelectAdapter.notifyItemRemoved(integer);
        }
        if (deleteItems.size() > 0) {
            multiSelectAdapter.removeAll(deleteItems);
        }
    }
}
