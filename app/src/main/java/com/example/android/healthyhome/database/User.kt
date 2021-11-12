package com.example.android.healthyhome.database

data class User(
    var uid : String? = null,

    //actual fields.
    var name : String? = null,
    var email : String? = null,
    var isProvider : Boolean? = null

)
