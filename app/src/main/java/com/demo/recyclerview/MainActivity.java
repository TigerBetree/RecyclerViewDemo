package com.demo.recyclerview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.recyclerview.databinding.ActivityMainBinding;
import com.demo.recyclerview.databinding.ItemTextBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mainBinding.recyclerView.setAdapter(new MyAdapter(Datas.getCats(MainActivity.this)));
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener {

        private List<String> items;

        public MyAdapter(List<String> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ItemTextBinding itemTextBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.item_text, parent, false);
            return new ViewHolder(itemTextBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            String itemName = items.get(position);
            holder.itemTextBinding.textView.setText(itemName);
            holder.itemTextBinding.cvItem.setTag(itemName);
            holder.itemTextBinding.cvItem.setOnClickListener(this);
        }

        @Override
        public int getItemCount() {
            if (items != null) {
                return items.size();
            }
            return 0;
        }

        @Override
        public void onClick(View v) {
            String tag = (String) v.getTag();

            DetailActivity.gotoActivity(MainActivity.this, tag);
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            ItemTextBinding itemTextBinding;

            public ViewHolder(ItemTextBinding binding) {
                super(binding.getRoot());
                itemTextBinding = binding;
            }
        }
    }
}
