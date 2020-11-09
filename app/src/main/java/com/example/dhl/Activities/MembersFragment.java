package com.example.dhl.Activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dhl.MemberResponse;
import com.example.dhl.R;
import com.example.dhl.adapters.MembersAdapter;
import com.example.dhl.api.Api;
import com.example.dhl.api.ApiClient;
import com.example.dhl.model.Members;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MembersFragment extends Fragment {
    private RecyclerView recyclerView;
    private MembersAdapter adapter;
    private List<Members> memberList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.members_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerViewMembers);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));




        Call<MemberResponse> call = ApiClient.getClient().getAllMembers();

        call.enqueue(new Callback<MemberResponse>() {
            @Override
            public void onResponse(Call<MemberResponse> call, Response<MemberResponse> response) {

                memberList = response.body().getMembers();
                adapter = new MembersAdapter(getActivity(),memberList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<MemberResponse> call, Throwable t) {

            }
        });

    }
}
