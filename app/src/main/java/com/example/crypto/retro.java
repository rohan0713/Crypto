package com.example.crypto;

import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class retro {

    public static String url = "https://api.shasta.trongrid.io/wallet/";

    public static serve services = null;

    public static serve getServices(){

        if(services == null){

//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(url)
//                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            services = retrofit.create(serve.class);
        }
        return services;
    }


    public interface serve{

        @GET("generateaddress")
        Call<rxsponse> getA();
    }
}
