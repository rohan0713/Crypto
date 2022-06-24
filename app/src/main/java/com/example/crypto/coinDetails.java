package com.example.crypto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.tradingview.lightweightcharts.api.interfaces.SeriesApi;
import com.tradingview.lightweightcharts.api.options.models.LayoutOptions;
import com.tradingview.lightweightcharts.view.ChartsView;

import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class coinDetails extends AppCompatActivity {

    boolean flag = false;
    String symbol, pair, col;
    TextView coin, price, percnt, high, low;
    RelativeLayout change;
    WebView chartsView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_details);
        ImageView fav = findViewById(R.id.star);
        MaterialButton buy = findViewById(R.id.buy);
        MaterialButton sell = findViewById(R.id.sell);
        chartsView = findViewById(R.id.chart);

        symbol = getIntent().getStringExtra("symbol");
        pair = getIntent().getStringExtra("pair");
        col = getIntent().getStringExtra("col");

        WebSettings webSettings = chartsView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        chartsView.setWebChromeClient(new WebChromeClient());
        getresponse();

        chartsView.loadUrl("https://s.tradingview.com/widgetembed/?frameElementId=tradingview_76d87&symbol=" + symbol + "&interval=1D&theme=Dark");
        ImageView back = findViewById(R.id.back);
        coin = findViewById(R.id.coin_name);
        price = findViewById(R.id.rate);
        percnt = findViewById(R.id.percent);
        change = findViewById(R.id.change_back);
        high = findViewById(R.id.high);
        low = findViewById(R.id.low);

//        getCandles(symbol, pair);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(coinDetails.this);
                View v = getLayoutInflater().inflate(R.layout.buy_layout, null);
                alert.setView(v);
                AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.getWindow().setBackgroundDrawable(
                        new ColorDrawable(Color.TRANSPARENT)
                );

                alertDialog.show();

                alertDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT);
                Window window = alertDialog.getWindow();
                window.setGravity(Gravity.BOTTOM);

                MaterialButton b = v.findViewById(R.id.buy);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(v.getContext(), "Order placed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(coinDetails.this);
                View v = getLayoutInflater().inflate(R.layout.sell_layout, null);
                alert.setView(v);
                AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.getWindow().setBackgroundDrawable(
                        new ColorDrawable(Color.TRANSPARENT)
                );

                alertDialog.show();

                alertDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT);
                Window window = alertDialog.getWindow();
                window.setGravity(Gravity.BOTTOM);

                MaterialButton b = v.findViewById(R.id.sell);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(v.getContext(), "Order placed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!flag){
                    fav.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.buy));
                    flag = true;
                }else{
                    fav.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.base2));
                    flag = false;
                }
            }
        });
    }

    private void getCandles(String symbol, String pair) {

        Call<List<candle_data>> call = Retrofit.getData().candles(pair, "1h");
        call.enqueue(new Callback<List<candle_data>>() {
            @Override
            public void onResponse(Call<List<candle_data>> call, Response<List<candle_data>> response) {

                List<candle_data> list = response.body();
            }

            @Override
            public void onFailure(Call<List<candle_data>> call, Throwable t) {

            }
        });

    }

    private void getresponse() {

        Call<List<response>> call = Retrofit.getServices().getCoinsInfo();
        call.enqueue(new Callback<List<response>>() {

            @Override
            public void onResponse(Call<List<response>> call, Response<List<response>> response) {

                List<response> list = response.body();
                for (int i = 0; i < list.size(); i++){

                    Log.d("hell", String.valueOf(list.size()));
                    if(list.get(i).getMarket().equalsIgnoreCase(pair)){

                        coin.setText(list.get(i).getMarket());
                        DecimalFormat decimalFormat = new DecimalFormat();
                        decimalFormat.setMaximumFractionDigits(3);

                        if(col.equalsIgnoreCase("1")) {
                            price.setText("₹ " + decimalFormat.format(Double.parseDouble(list.get(i).getLast_price())));
                            high.setText("₹ " + decimalFormat.format(Double.parseDouble(list.get(i).getHigh())));
                            low.setText("₹ " + decimalFormat.format(Double.parseDouble(list.get(i).getLow())));
                        }else{
                            price.setText(decimalFormat.format(Double.parseDouble(list.get(i).getLast_price())));
                            high.setText(decimalFormat.format(Double.parseDouble(list.get(i).getHigh())));
                            low.setText(decimalFormat.format(Double.parseDouble(list.get(i).getLow())));
                        }

                        if(list.get(i).getChange_24_hour().charAt(0) == '-'){
                            change.setBackgroundResource(R.color.sell);
                            percnt.setText("↓ " + list.get(i).getChange_24_hour());
                        }else{
                            change.setBackgroundResource(R.color.buy);
                            percnt.setText("↑ " + list.get(i).getChange_24_hour());
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<List<response>> call, Throwable t) {
                Toast.makeText(coinDetails.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

}