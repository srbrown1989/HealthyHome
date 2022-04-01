package com.example.android.healthyhome.database.util;

import com.example.android.healthyhome.database.LoginResponse;
import com.example.android.healthyhome.database.ProviderSignUpResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IMyAPI {

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> loginUser(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("register.php")
    Call<LoginResponse> registerUser(@Field("name") String name, @Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("providerSignUp.php")
    Call<ProviderSignUpResponse> registerProvider(
            @Field("uid") int uid,
            @Field("address") String address,
            @Field("postcode")String postcode,
            @Field("companyName") String companyName,
            @Field("contact") String contact,
            @Field("companyEmail") String companyEmail,
            @Field("Bio") String bio,
            @Field("service") String service,
            @Field("extras") String extras,
            @Field("rating") int rating,
            @Field("price") String price
    );

    @FormUrlEncoded
    @POST("providerChangeBio.php")
    Call<ProviderSignUpResponse> updateProvider(
            @Field("uid") int uid,
            @Field("address") String address,
            @Field("postcode")String postcode,
            @Field("contact") String contact,
            @Field("companyEmail") String companyEmail,
            @Field("Bio") String bio,
            @Field("rating") int rating,
            @Field("price") String price
    );

    @GET("getBookings.php")
    Call<Bookings> getAllBookings();


}
