package com.example.dhl.api;


import com.example.dhl.LoginResponse;
import com.example.dhl.MemberResponse;
import com.example.dhl.Uploadresponse;
import com.example.dhl.model.Members;
import com.example.dhl.model.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {


    @GET("get_members")
    Call<List<Members>> getMembers();

    //New Member Registration
    @FormUrlEncoded
     @POST("createuser")
    Call<Uploadresponse> createUser(
            @Field("id_passport") String id_passport,
            @Field("member_number") String member_number,
            @Field("surname") String surname,
            @Field("first_name") String first_name,
            @Field("middle_name") String middle_name,
            @Field("phone_number") String phone_number,
            @Field("gender")String gender,
            @Field("dob") String dob,
            @Field("county") String county,
            @Field("constituency") String constituency,
            @Field("ward") String ward,
            @Field("member_picture") String member_picture
    );

//agent logging in
    @FormUrlEncoded
  @POST ("agentlogin")
    Call<LoginResponse>agentLogin(
            @Field("id_passport") String id_passport,
            @Field("password") String password
    );

    @FormUrlEncoded
    @PUT("updateagent/{id_passport}")
    Call<LoginResponse> updateAgent(
            @Path("id_passport") String id_passport,
            @Field("agentName") String agentName,
            @Field("member_number") String member_number,
            @Field("phone_number") String phone_number,
            @Field("county") String county,
            @Field("constituency") String constituency,
            @Field("ward") String ward

    );

   /* @GET
    Call<MemberResponse>getMember(@Query(encoded = false,member_number));*/

  /*  @GET("/getmember")
   Call<MemberResponse> getMember() ;
    (
            @Path("member_number") String member_number,
            @Field("first_name") String first_name,
            @Field("middle_name") String middle_name,
            @Field("surname") String surname,
            @Field("id_passport") String id_passport,
            @Field("phone_number") String phone_number,
            @Field("county") String county,
            @Field("constituency") String constituency,
            @Field("ward") String ward

    );*/

    @FormUrlEncoded
    @PUT("updatepassword")
    Call<Uploadresponse> updatePassword(
            @Field("currentpassword") String currentpassword,
            @Field("newpassword") String newpassword,
            @Field("id_passport") String id_passport
    );


   // @GET("getmember")
   // Call<List<Members>> fetchByMemberNumber();


    @DELETE("deleteuser/{id}")
    Call<Uploadresponse> deleteUser(@Path("id") int id);

    @POST("get_order.php")
    Call<List<Order>> getOrder();




}
