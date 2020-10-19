package com.example.dhl.api;




import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {


    private static final String BASE_URL = "http://192.168.1.61/MyRestApi/public/";
    private static final String LOGIN_URL = "http://192.168.1.61/MyRestApi/public/";

    private static ApiClient mInstance;
    private Retrofit retrofit , loginRetrofit;



    private ApiClient() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();




        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        loginRetrofit = new Retrofit.Builder()
                .baseUrl(LOGIN_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();




    }

    public static synchronized ApiClient getInstance() {
        if (mInstance == null) {
            mInstance = new ApiClient();
        }
        return mInstance;
    }

    public Api getApi() {
        return retrofit.create(Api.class);
    }

    public  Api getLoginApi(){
        return loginRetrofit.create(Api.class);
    }
}
