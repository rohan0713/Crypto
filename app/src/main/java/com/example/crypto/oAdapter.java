package com.example.crypto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class oAdapter extends RecyclerView.Adapter<oAdapter.ViewHolder> {

    Context context;
    int ViewType;
    int sell = 0;
    int buy = 1;

    public oAdapter(Context context, int ViewType){
        this.context = context;
        this.ViewType = ViewType;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == sell) {
            View view = LayoutInflater.from(context).inflate(R.layout.open_order, parent, false);
            return new ViewHolder(view);
        }else{
            View view = LayoutInflater.from(context).inflate(R.layout.history_order, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {

        if(ViewType == 1){
            return buy;
        }
        return sell;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
