package com.asuka.mqttlibrary.view.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asuka.mqttlibrary.R;
import com.asuka.mqttlibrary.modle.event.Data;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Data> dateList = new ArrayList<Data>();

    public MyAdapter(List<Data> dateList) {
        this.dateList = dateList;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_recycleview,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyAdapter.MyViewHolder holder, int position) {
        holder.textViewName.setText(dateList.get(position).getName());
        holder.textViewData.setText(dateList.get(position).getData());
    }

    @Override
    public int getItemCount() {
        //返回recycleView Item的个数
        return dateList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewData;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textViewName=itemView.findViewById(R.id.textViewName);
            textViewData=itemView.findViewById(R.id.textViewData);
        }
    }
}