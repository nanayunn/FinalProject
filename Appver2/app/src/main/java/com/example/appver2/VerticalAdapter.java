package com.example.appver2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class VerticalAdapter extends RecyclerView.Adapter<VerticalViewHolder> {

    private ArrayList<VerticalData> verticalDatas;

    public void setData(ArrayList<VerticalData> list){
        verticalDatas = list;
    }

    @Override
    public VerticalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

// 사용할 아이템의 뷰를 생성해준다.
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleview_item, parent, false);

        VerticalViewHolder holder = new VerticalViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(VerticalViewHolder holder, int position) {
        VerticalData data = verticalDatas.get(position);

        holder.description.setText(data.getText());
        holder.icon.setImageResource(data.getImg());

    }

    @Override
    public int getItemCount() {
        return verticalDatas.size();
    }
}