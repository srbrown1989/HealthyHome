package com.example.android.healthyhome.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: GetProviderApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://raptor.kent.ac.uk/proj/comp6000/project/14/ProvidersByService.php?service=")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetProviderApi::class.java)

    }
}