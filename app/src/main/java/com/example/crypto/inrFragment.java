package com.example.crypto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class inrFragment extends Fragment {

    RecyclerView recyclerView;
    View view;
    HashMap<String, String> map = new HashMap<>();
    HashMap<String, String> pair = new HashMap<>();
    SpinKitView progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_inr2, container, false);
        recyclerView = view.findViewById(R.id.cRView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        progress = view.findViewById(R.id.progress_bar);
        progress.animate();
        getmarket();
        return view;
    }

    private void getmarket() {

        Call<List<market>> listCall = Retrofit.getServices().market_details();
        listCall.enqueue(new Callback<List<market>>() {
            @Override
            public void onResponse(Call<List<market>> call, Response<List<market>> response) {

                List<market> list = response.body();
                for (int i = 0; i<list.size(); i++){
                    map.put(list.get(i).getCoindcx_name(), list.get(i).getTarget_currency_name());
                    pair.put(list.get(i).getCoindcx_name(), list.get(i).getPair());
                }
                getresponse();
            }

            @Override
            public void onFailure(Call<List<market>> call, Throwable t) {

            }
        });

    }

    private void getresponse() {

        List<response> responseList = new ArrayList<>();
        Call<List<response>> call = Retrofit.getServices().getCoinsInfo();
        call.enqueue(new Callback<List<response>>() {
            @Override
            public void onResponse(Call<List<response>> call, Response<List<response>> response) {
                progress.setVisibility(View.GONE);
                Toast.makeText(view.getContext(), "success", Toast.LENGTH_SHORT).show();
                List<response> list = response.body();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getMarket().substring(list.get(i).getMarket().length() - 3).equalsIgnoreCase("INR")) {
                        responseList.add(list.get(i));
                    }
                }
                recyclerView.setAdapter(new iAdapter(view.getContext(), responseList, map, pair, 1));
            }

            @Override
            public void onFailure(Call<List<response>> call, Throwable t) {
                Toast.makeText(view.getContext(), "failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}