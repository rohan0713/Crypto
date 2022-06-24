package com.example.crypto;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class fundsFragment extends Fragment {

    View view;
    EditText et;
    MaterialButton save;
    AlertDialog alertDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_funds, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.wRView);
        List<String> list = new ArrayList<>();
        list.add("btc");
        list.add("eth");
        list.add("trx");
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new wAdapter(view.getContext(), list));

        SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        if((Boolean) userHelperClass.get(view.getContext(), "keys", false)){
            Log.d("hell", "address" +
                    String.valueOf(userHelperClass.get(view.getContext(), "ad", "ad")));
        }else {
            getIt();
        }
        return view;
    }

    private void getIt() {

        Call<rxsponse> call = retro.getServices().getA();
        call.enqueue(new Callback<rxsponse>() {
            @Override
            public void onResponse(Call<rxsponse> call, Response<rxsponse> response) {
                Toast.makeText(view.getContext(), "got", Toast.LENGTH_SHORT).show();
                Log.d("hell", "private" + response.body().getPrivateKey());
                Log.d("hell", "address" + response.body().getAddress());
                Log.d("hell", "hex" + response.body().getHexAddress());
                userHelperClass.put(view.getContext(), "keys", true);
                userHelperClass.put(view.getContext(), "ad", response.body().getAddress());

                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                View v = getLayoutInflater().inflate(R.layout.key_layout, null);
                alert.setView(v);
                alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(false);

                et = v.findViewById(R.id.key_txt);
                save = v.findViewById(R.id.save);

                alertDialog.show();
                et.setText(response.body().getPrivateKey());

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
            }

            @Override
            public void onFailure(Call<rxsponse> call, Throwable t) {

                Toast.makeText(view.getContext(), "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}