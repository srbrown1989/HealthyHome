package com.example.android.healthyhome.database

import com.google.firebase.database.Exclude

data class Customer (
    @get: Exclude

    var customer_id : String? = null,
    var uid : String? = null,
    var cFirstName : String? = null,
    var cLastName : String? = null,
    var address : String? = null,
    var phone : String? = null,
    var email : String? = null,
    var password : String? = null
)
