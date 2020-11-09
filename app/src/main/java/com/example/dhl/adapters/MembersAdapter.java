package com.example.dhl.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dhl.R;
import com.example.dhl.model.Members;


import java.util.List;

public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.MemberAdapterVH> {

    private Context mCtx;
    private List<Members> memberList;

    public MembersAdapter(Context mCtx, List<Members> memberList) {
        this.mCtx = mCtx;
        this.memberList = memberList;
    }



    @NonNull
    @Override
    public MemberAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.activity_display__member, parent, false);
        return new MemberAdapterVH(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MemberAdapterVH holder, int position) {
        Members members = memberList.get(position);

        holder.firstname.setText(members.getFirstName());
        holder.middlename.setText(members.getMiddleName());
        holder.surname.setText(members.getSurname());
        holder.membernumber.setText(members.getMemberNumber());
        holder.phonenumber.setText(members.getPhoneNumber());
        holder.idpass.setText(members.getIdPassport());
        holder.county.setText(members.getCounty());
        holder.constituency.setText(members.getConstituency());
        holder.ward.setText(members.getWard());
        holder.gender.setText(members.getGender());
        holder.dob.setText(members.getDob());



    }



    @Override
    public int getItemCount() {
        return memberList.size();
    }


    public class MemberAdapterVH extends RecyclerView.ViewHolder {

        EditText firstname,middlename,surname,membernumber,phonenumber,county,constituency,ward;
        TextView idpass,dob,gender;
        ImageView memberpic;


        public MemberAdapterVH(@NonNull View itemView) {
            super(itemView);
            firstname = itemView.findViewById(R.id.first_name);
            middlename = itemView.findViewById(R.id.middle_name);
            surname = itemView.findViewById(R.id.surname);
            membernumber = itemView.findViewById(R.id.member_number);
            phonenumber = itemView.findViewById(R.id.phone_number);
            county = itemView.findViewById(R.id.memberCounty);
            constituency= itemView.findViewById(R.id.constituency);
            ward = itemView.findViewById(R.id.ward);
            idpass = itemView.findViewById(R.id.id_passport);
            dob= itemView.findViewById(R.id.dob);
            gender = itemView.findViewById(R.id.gender);
            memberpic = itemView.findViewById(R.id.member_profile_image);




        }
    }


}
