package com.example.crypto;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


public class Retrofit {

    public static String url = "https://api.coindcx.com/";
    public static String url2 = "https://public.coindcx.com/";

    public static services services = null;
    public static services services1 = null;

    public static services getServices(){

        if(services == null){

//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(url)
//                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            services = retrofit.create(services.class);
        }
        return services;
    }

    public static services getData(){

        if(services1 == null){

//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(url2)
//                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            services1 = retrofit.create(services.class);
        }
        return services1;
    }

    public interface services {

        @GET("exchange/ticker")
        Call<List<response>> getCoinsInfo();

        @GET("24hr")
        Call<response> CoinInfo(@Query("symbol") String symbol);

        @GET("exchange/v1/markets_details")
        Call<List<market>> market_details();

        @GET("market_data/candles")
        Call<List<candle_data>> candles(@Query("pair") String pair, @Query("interval") String interval);
    }
}
