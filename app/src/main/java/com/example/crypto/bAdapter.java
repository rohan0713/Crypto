package com.example.crypto;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

public class bAdapter extends RecyclerView.Adapter<bAdapter.ViewHolder> {

    Context context;
    List<response> list;
    static HashMap<String, String> map;
    static HashMap<String, String> pair;
    static int viewType;

    bAdapter(Context context, List<response> list, HashMap<String, String> map,
             HashMap<String, String> pair, int viewType) {
        this.context = context;
        this.list = list;
        this.map = map;
        this.pair = pair;
        this.viewType = viewType;
    }

    @NonNull
    @Override
    public bAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.coin_layout, parent, false);
        return new bAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull bAdapter.ViewHolder holder, int position) {

        holder.Bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void Bind(response coins) {

            TextView name = itemView.findViewById(R.id.coin_name);
            TextView price = itemView.findViewById(R.id.rate);
            TextView percnt = itemView.findViewById(R.id.change);
            TextView coin = itemView.findViewById(R.id.coin);
            ImageView imageView = itemView.findViewById(R.id.coin_image);

            RelativeLayout col = itemView.findViewById(R.id.backG);

            String n = coins.getMarket().substring(0, coins.getMarket().length() - 3);

            Picasso.get().load("https://media.wazirx.com/media/" + n.toLowerCase() + "/84.png")
                    .placeholder(R.drawable.profile)
                    .into(imageView);

            coin.setText(map.get(coins.getMarket()));
//            double p = Double.parseDouble(coins.getLastPrice()) - Double.parseDouble(coins.getOpenPrice());
//            p = Math.abs(p / Double.parseDouble(coins.getOpenPrice()));
//            p = p * 100;
//
            DecimalFormat decimalFormat = new DecimalFormat();
            decimalFormat.setMaximumFractionDigits(4);

            name.setText(coins.getMarket().substring(0, coins.getMarket().length() - 3) + " / BTC");
            price.setText(coins.getLast_price());


//            if(Double.parseDouble(coins.getLastPrice()) >= Double.parseDouble(coins.getOpenPrice())){
//                col.setBackgroundResource(R.color.buy);
//                percnt.setText("↑ " + decimalFormat.format(p));
//            }else{
//
//                col.setBackgroundResource(R.color.sell);
//                percnt.setText("↓ " + decimalFormat.format(p));
//            }

            if (coins.getChange_24_hour().charAt(0) == '-') {
                col.setBackgroundResource(R.color.sell);
                percnt.setText("↓ " + coins.getChange_24_hour());
            } else {
                col.setBackgroundResource(R.color.buy);
                percnt.setText("↑ " + coins.getChange_24_hour());
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), coinDetails.class);

                    i.putExtra("symbol", coins.getMarket().substring(0, coins.getMarket().length() - 3) + "USD");
                    i.putExtra("pair", coins.getMarket());
                    i.putExtra("col", "3");
                    view.getContext().startActivity(i);
                }
            });
        }
    }
}
