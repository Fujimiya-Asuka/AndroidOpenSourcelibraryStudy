package com.asuka.androidopensourcelibrarystudydemo.vlayout.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityAndroidAsyncBinding;

import java.util.ArrayList;
import java.util.List;

public abstract class MyAdapter2<T,V extends ViewBinding> extends RecyclerView.Adapter<MyAdapter2.MyViewHolder2<V>> {
    private ArrayList<T> dataList = new ArrayList<>();

    MyAdapter2 myAdapter2= new MyAdapter2<String,ActivityAndroidAsyncBinding>(){
        @Override
        protected ActivityAndroidAsyncBinding onCreateViewBinding(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
            return null;
        }

        @Override
        protected void onBindViewHolder(ActivityAndroidAsyncBinding binding, int position) {

        }
    };




    protected abstract V onCreateViewBinding(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent);

    protected abstract void onBindViewHolder(V binding, int position);

    @NonNull
    @Override
    public MyViewHolder2<V> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder2<>(onCreateViewBinding(LayoutInflater.from(parent.getContext()),parent));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2<V> holder, int position) {
        onBindViewHolder(holder.getBinding(),position);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class MyViewHolder2<T extends ViewBinding> extends RecyclerView.ViewHolder {

        private final T binding;

        public MyViewHolder2(T viewBind) {
            super(viewBind.getRoot());
            binding = viewBind;
        }

        public T getBinding() {
            return binding;
        }
    }
}
