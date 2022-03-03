package com.example.android.healthyhome.database.util;

import com.example.android.healthyhome.database.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IMyAPI {

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> loginUser(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("register.php")
    Call<LoginResponse> registerUser(@Field("name") String name, @Field("email") String email, @Field("password") String password);

}
