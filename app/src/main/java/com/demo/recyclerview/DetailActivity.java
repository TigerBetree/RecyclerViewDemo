package com.demo.recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.demo.recyclerview.databinding.ActivityDetailBinding;
import com.demo.recyclerview.fragment.AnimFragment;
import com.demo.recyclerview.fragment.BaseFragment;
import com.demo.recyclerview.fragment.HeaderFooterFragment;
import com.demo.recyclerview.fragment.MultiSelectFragment;
import com.demo.recyclerview.fragment.MultiTypeFragment;
import com.demo.recyclerview.fragment.NormalFragment;
import com.demo.recyclerview.fragment.SingleSelectFragment;

public class DetailActivity extends AppCompatActivity {

    private static final String KEY_CATEGORY = "category";

    private String mCategory;

    private ActivityDetailBinding activityDetailBinding;

    public static void gotoActivity(Activity activity, String category) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(KEY_CATEGORY, category);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        mCategory = getIntent().getStringExtra(KEY_CATEGORY);
        setTitle(mCategory);

        showCatetory();
    }

    private void showCatetory() {
        if (mCategory.equals("Normal List")) {
            showNormalFragment(BaseFragment.TYPE_LINEAR_LAYOUT);
        } else if (mCategory.equals("Normal Grid")) {
            showNormalFragment(BaseFragment.TYPE_GRID_LAYOUT);
        } else if (mCategory.equals("Normal Staggered")) {
            showNormalFragment(BaseFragment.TYPE_STAGGERED_GRID_LAYOUT);
        } else if (mCategory.equals("Multiple List")) {
            showMultipleTypeFragment(BaseFragment.TYPE_LINEAR_LAYOUT);
        } else if (mCategory.equals("Multiple Grid")) {
            showMultipleTypeFragment(BaseFragment.TYPE_GRID_LAYOUT);
        } else if (mCategory.equals("Multiple Staggered")) {
            showMultipleTypeFragment(BaseFragment.TYPE_STAGGERED_GRID_LAYOUT);
        } else if (mCategory.equals("Header Footer List")) {
            showHeaderFooterFragment(BaseFragment.TYPE_LINEAR_LAYOUT);
        } else if (mCategory.equals("Header Footer Grid")) {
            showHeaderFooterFragment(BaseFragment.TYPE_GRID_LAYOUT);
        } else if (mCategory.equals("Header Footer Staggered")) {
            showHeaderFooterFragment(BaseFragment.TYPE_STAGGERED_GRID_LAYOUT);
        } else if (mCategory.equals("Anim List")) {
            showAnimFragment(BaseFragment.TYPE_LINEAR_LAYOUT);
        } else if (mCategory.equals("Anim Grid")) {
            showAnimFragment(BaseFragment.TYPE_GRID_LAYOUT);
        } else if (mCategory.equals("Anim Staggered")) {
            showAnimFragment(BaseFragment.TYPE_STAGGERED_GRID_LAYOUT);
        } else if (mCategory.equals("Single Select")) {
            showSingleSelectFragment();
        } else if (mCategory.equals("Multiple Select")) {
            showMultiSelectFragment();
        }
    }

    private void showNormalFragment(int type) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(activityDetailBinding.container.getId(), NormalFragment.newInstance(type))
                .commit();
    }

    public void showMultipleTypeFragment(int type) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(activityDetailBinding.container.getId(), MultiTypeFragment.newInstance(type))
                .commit();
    }

    public void showHeaderFooterFragment(int type) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(activityDetailBinding.container.getId(), HeaderFooterFragment.newInstance(type))
                .commit();
    }

    public void showAnimFragment(int type) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(activityDetailBinding.container.getId(), AnimFragment.newInstance(type))
                .commit();
    }

    public void showSingleSelectFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(activityDetailBinding.container.getId(), SingleSelectFragment.newInstance())
                .commit();
    }

    public void showMultiSelectFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(activityDetailBinding.container.getId(), MultiSelectFragment.newInstance())
                .commit();
    }
}
