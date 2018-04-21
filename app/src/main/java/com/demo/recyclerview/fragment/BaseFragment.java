package com.demo.recyclerview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {

    public static final int TYPE_LINEAR_LAYOUT = 1;
    public static final int TYPE_GRID_LAYOUT = 2;

    // 瀑布流
    public static final int TYPE_STAGGERED_GRID_LAYOUT = 3;

    public static final String KEY_TYPE = "type";

    public int type = TYPE_LINEAR_LAYOUT;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            type = getArguments().getInt(KEY_TYPE, TYPE_LINEAR_LAYOUT);
        }
    }
}
