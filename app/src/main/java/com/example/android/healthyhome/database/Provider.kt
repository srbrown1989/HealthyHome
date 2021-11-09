package com.example.android.healthyhome.database

import com.google.firebase.database.Exclude

data class Provider(
    //@get: Exclude
    //var provider_id : String? = null,
    var uid : String? = null,

    //actual fields.
    var name : String? = null,
    var contactNumber: String? = null,
    var bio : String? = null,
    var service : String? = null,
    var creditCardNumber : String? = null


)
