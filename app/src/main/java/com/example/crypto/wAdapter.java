package com.example.crypto;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class wAdapter extends RecyclerView.Adapter<wAdapter.ViewHolder> {

    Context context;
    List<String> list;
    wAdapter(Context context, List<String> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.wallet_coins, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.Bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), addressActivity.class);
                    view.getContext().startActivity(i);
                }
            });
        }

        public void Bind(String s) {

            ImageView imageView = itemView.findViewById(R.id.coin_image);
            TextView name = itemView.findViewById(R.id.coin_sf);

            name.setText(s);
            Picasso.get().load("https://media.wazirx.com/media/" + s + "/84.png").into(imageView);
        }
    }
}
