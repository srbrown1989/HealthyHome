package com.example.android.healthyhome.database

import com.google.firebase.database.Exclude

data class Provider(
    //
    //@get: Exclude
    //var provider_id : String? = null,
    var uid : String? = null,

    //actual fields.
    var bio : String? = null,
    var serviceType: String? = null,
    var providerName: String? = null,
    var providerEmail: String? = null,
    var phoneNumber: String? = null,
    var offers: String? = null,
    var rating : Int? = 3,
    var price : Int? = 0


)
