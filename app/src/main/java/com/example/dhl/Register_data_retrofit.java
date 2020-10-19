package com.example.dhl;

import com.example.dhl.api.Api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register_data_retrofit {
    private static final String BASE_URL = "https://pakinterns.com/testing_api/";
    private static Register_data_retrofit mInstance;
    private Retrofit retrofit;
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public Register_data_retrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static synchronized Register_data_retrofit getInstance() {
        if (mInstance == null) {
            mInstance = new Register_data_retrofit();
        }
        return mInstance;
    }


    public Api geteditprofile() { return retrofit.create(Api.class); }

}
