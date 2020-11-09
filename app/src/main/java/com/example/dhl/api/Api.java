package com.example.dhl.api;


import com.example.dhl.LoginResponse;
import com.example.dhl.MemberResponse;
import com.example.dhl.OrderResponse;
import com.example.dhl.Uploadresponse;
import com.example.dhl.model.Members;
import com.example.dhl.model.Order;



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

    //New Member Registration

     @POST("createuser")
     @FormUrlEncoded
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



    @GET("getmember")
    Call<MemberResponse> getMember(@Query("member_number") String member_number);


    @FormUrlEncoded
    @PUT("updatepassword")
    Call<Uploadresponse> updatePassword(
            @Field("currentpassword") String currentpassword,
            @Field("newpassword") String newpassword,
            @Field("id_passport") String id_passport
    );


    @GET("allmembers")
    Call<MemberResponse> getAllMembers();

    @FormUrlEncoded
    @POST("createorder")
    Call<Uploadresponse> createOrder(
            @Field("cards") String cards,
            @Field("quantity") String quantity,
            @Field("order_date") String order_date,
            @Field("created_by") String created_by,
            @Field("status") Integer status


    );


    @GET("allorders")
    Call<OrderResponse> getAllOrders();


    @DELETE("deleteuser/{id}")
    Call<Uploadresponse> deleteUser(@Path("id") int id);






}
