package com.example.dhl;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dhl.adapters.OrderAdapter;
import com.example.dhl.api.Api;
import com.example.dhl.api.ApiClient;
import com.example.dhl.model.Order;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderHistoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private OrderAdapter adapter;
    private List<Order> orderList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_order_history, container, false);



        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerViewOrder);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Api client = ApiClient.getClient();

        Call<OrderResponse> call = client.getAllOrders();

        call.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {

                orderList = response.body().getOrders();
                adapter = new OrderAdapter(getContext(), orderList);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {

            }
        });



    }


}