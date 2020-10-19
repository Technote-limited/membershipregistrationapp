package com.example.dhl;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private ArrayList<OrderItem> mOrderList;
    private  OnItemClickListener mListener;
    private Context mContext;
    private Cursor mCursor;

    public OrderAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }


    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;

    }



    public static class OrderViewHolder extends RecyclerView.ViewHolder  {
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        public TextView mTextView4;



        public OrderViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            mTextView1 = itemView.findViewById(R.id.tvOrderNumber);
            mTextView2 = itemView.findViewById(R.id.tvItem);
            mTextView3 = itemView.findViewById(R.id.tvQuantity);
            mTextView4 = itemView.findViewById(R.id.tvDate);



           /* itemView.setOnClickListener(v -> {
                if(listener!= null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        listener.onItemClick(position);
                    }
                }
            });*/

        }

    }
    public OrderAdapter(ArrayList<OrderItem> orderList) {
        mOrderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        OrderViewHolder ovh = new OrderViewHolder(v,mListener);
        mContext = parent.getContext();
        return ovh;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {

      /*  String name = mCursor.getString(mCursor.getColumnIndex());
        int amount = mCursor.getInt(mCursor.getColumnIndex(GroceryContract.GroceryEntry.COLUMN_AMOUNT));
        holder.mTextView1.setText(name);
        holder.mTextView2.setText(String.valueOf(amount));*/

        OrderItem orderItem = mOrderList.get(position);

        holder.mTextView1.setText(orderItem.getText1());
        holder.mTextView2.setText(orderItem.getText2());
        holder.mTextView3.setText(orderItem.getText3());
        holder.mTextView4.setText(orderItem.getText4());
    }

    @Override
    public int getItemCount() {
        return mOrderList.size();
    }
}
