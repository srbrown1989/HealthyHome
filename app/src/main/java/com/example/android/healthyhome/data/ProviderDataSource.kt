package com.example.android.healthyhome.data

import com.example.android.healthyhome.database.Provider

class ProviderDataSource {

    fun loadProviders(): List<Provider>{

        var provider1 = Provider()
        provider1.name = "Paul's Cleaning"
        provider1.bio = "Pauls Popular Cleaning from dover"
        provider1.price = 5

        var provider2 = Provider()
        provider2.name = "Shaun's Cleaning"
        provider2.bio = "Shauns fantastic cleaning in canterbury"
        provider2.price = 12

        var provider3 = Provider()
        provider3.name = "Fluid Cleaning"
        provider3.bio = "I like fluids all day"
        provider3.price = 15

        return listOf(
            provider1,provider2,provider3
        )


    }
}