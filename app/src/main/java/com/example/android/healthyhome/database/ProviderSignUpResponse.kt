package com.example.android.healthyhome.database

data class ProviderSignUpResponse(
    val error: Boolean,
    val error_msg : String,
    val provider: Provider?,
    val uid: Int?
)