package com.example.dhl;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api {
    String BASE_URL = "http://dhlcompany.com/DHLApis/";

    @POST("add-member")
    Call<List<Members>> addMember();

    @POST("Member-details")
    Call<List<Members>> fetchMember();



    @Multipart
    @POST("crud.php")
    Call<Uploadresponse> uploadData(
            @Part("tag") RequestBody tag1,
            @Part("passport_number") RequestBody idPassport11,
            @Part("surname") RequestBody surname1,
            @Part ("first_name") RequestBody firstName11,
            @Part ("middle_name") RequestBody middleName11,
            @Part ("phone_number") RequestBody phoneNumber11,
            @Part ("dob") RequestBody d_o_b11,
            @Part ("gender") RequestBody maleRadioButton11,
            @Part ("country") RequestBody member_county11,
            @Part ("constituency") RequestBody member_constituency11,
            @Part ("ward") RequestBody member_ward11,
            @Part ("member_id") RequestBody membershipNo11,
         //   @Part MultipartBody.Part file,
            @Part("picture") RequestBody image

    );


}
