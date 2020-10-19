package com.example.dhl;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class OrderHistoryFragment extends Fragment {

    final ArrayList<OrderItem> orderList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_order_history, container, false);

        RecyclerView recyclerView = v.findViewById(R.id.recyclerViewOrder);

        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        orderList.add(new OrderItem( "Order 1", "Youth Cards","200","20/02/20"));
        //orderList.add(new OrderItem( "Order 2", "Women Cards","500","20/02/20"));
        //orderList.add(new OrderItem( "Order 3", "Disability Cards","200","20/02/20"));
        //orderList.add(new OrderItem( "Order 4", "Youth Cards","300","20/02/20"));
        //orderList.add(new OrderItem( "Order 5", "Youth Cards","100","20/02/20"));
        //orderList.add(new OrderItem( "Order 6", "Women Cards","200","20/02/20"));
        //orderList.add(new OrderItem( "Order 7", "Disability Cards","400","20/02/20"));




        OrderAdapter mAdapter = new OrderAdapter(orderList);
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());




        return v;
    }


}