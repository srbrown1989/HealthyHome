package com.example.android.healthyhome.database

import com.google.firebase.database.Exclude


data class Bookings (
    @get: Exclude

    var booking_id : String? = null,
    var uid : String? = null,
    var customer_id : String? = null,
    var provider_id : String? = null,
    var cFirstName : String? = null,
    var pFirstName : String? = null,
    var service : String? = null,
    var time : String? = null,
    var address : String? = null,
    var price : String? = null
)