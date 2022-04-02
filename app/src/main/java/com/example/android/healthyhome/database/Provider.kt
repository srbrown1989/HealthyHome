package com.example.android.healthyhome.database

import com.google.firebase.database.Exclude

data class Provider(
    val address: String,
    val bio: String,
    val contact: String,
    val email: String,
    val extras: String,
    val name: String,
    //
    val pid: Int,
    val postcode: String,
    val price: String,
    //
    val rating: Int,
    val service: String


)
