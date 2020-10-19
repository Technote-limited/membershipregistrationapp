package com.example.dhl;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dhl.Activities.InventoryManagementActivity;
import com.example.dhl.Activities.MainActivity;
import com.example.dhl.Activities.ManageCardsActivity;
import com.example.dhl.Activities.OrderPlacementActivity;
import com.example.dhl.Activities.ProgressActivity;
import com.example.dhl.Activities.SearchMembersActivity;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private ArrayList<ExampleItem> mExampleList;
    private  OnItemClickListener mListener;
    private Context context;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;

    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder  {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public CardView mCardView;

        public ExampleViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
            mCardView = itemView.findViewById(R.id.card_view_agent);
            

           itemView.setOnClickListener(v -> {
                if(listener!= null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        listener.onItemClick(position);
                    }
                }
            });

        }

    }

    public ExampleAdapter(ArrayList<ExampleItem> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v,mListener);
        context = parent.getContext();
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        ExampleItem currentItem = mExampleList.get(position);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());
        holder.mCardView.setOnClickListener(v -> {
            Intent intent;
            switch (position){
                case 0:
                    intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                    break;
                case 1:
                    intent = new Intent(context, SearchMembersActivity.class);
                    context.startActivity(intent);
                    break;
                case 2:
                    intent = new Intent(context, ProgressActivity.class);
                    context.startActivity(intent);
                    break;
                case 3:
                    intent = new Intent(context, ManageCardsActivity.class);
                    context.startActivity(intent);
                    break;
                case 4:
                    intent = new Intent(context, OrderPlacementActivity.class);
                    context.startActivity(intent);
                    break;
                case 5:
                    intent = new Intent(context, InventoryManagementActivity.class);
                    context.startActivity(intent);
                    break;

            }
        });



    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}