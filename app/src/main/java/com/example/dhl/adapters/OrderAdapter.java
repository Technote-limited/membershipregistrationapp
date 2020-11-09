package com.example.dhl.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dhl.R;
import com.example.dhl.model.Order;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    Context mCtx;
    private List<Order> orderList;

    public OrderAdapter(Context mCtx, List<Order> orderList) {
        this.mCtx = mCtx;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.order_item, parent, false);

        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);

        holder.mOrderNumber.setText(Integer.toString(order.getOrderNumber()));
        holder.mCardType.setText(order.getCards());
        holder.mQuantity.setText(order.getQuantity());
        holder.mOrderDate.setText(order.getOrderDate());

    }
    @Override
    public int getItemCount() {
        return orderList.size();
    }


    class OrderViewHolder extends RecyclerView.ViewHolder  {
         TextView mOrderNumber;
         TextView mCardType;
         TextView mQuantity;
         TextView mOrderDate;



        private OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            mOrderNumber = itemView.findViewById(R.id.tvOrderNumber);
            mCardType = itemView.findViewById(R.id.tvItem);
            mQuantity = itemView.findViewById(R.id.tvQuantity);
            mOrderDate= itemView.findViewById(R.id.tvDate);



        }

    }




}
