package com.asuka.androidopensourcelibrarystudydemo.vlayout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.asuka.androidopensourcelibrarystudydemo.R;

import java.util.ArrayList;

public class MyJavaAdapter extends DelegateAdapter.Adapter<MyJavaAdapter.AdapterHolder> {

    private ArrayList<String> listItem;
    // 用于存放数据列表
    private Context context;
    private LayoutHelper layoutHelper;
    private RecyclerView.LayoutParams layoutParams;
    private int count = 0;


    public MyJavaAdapter(Context context, LayoutHelper layoutHelper, int count, ArrayList<String> listItem) {
        this(context, layoutHelper, count, new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300), listItem);
    }

    public MyJavaAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull RecyclerView.LayoutParams layoutParams, ArrayList<String> listItem){
        this.context = context;
        this.layoutHelper = layoutHelper;
        this.count = count;
        this.layoutParams = layoutParams;
        this.listItem = listItem;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }

    @NonNull
    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterHolder(LayoutInflater.from(context).inflate(R.layout.recycle_view_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return count;
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}
