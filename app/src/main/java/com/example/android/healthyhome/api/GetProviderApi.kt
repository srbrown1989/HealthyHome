package com.example.android.healthyhome.api

import com.example.android.healthyhome.dataClasses.Provider
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetProviderApi {
    //these will be the http request methods for the provider screen

    @GET("cleaning")
    suspend fun getProviderByService(@Query("serviceType") serviceType: String): Response<List<Provider>>
}