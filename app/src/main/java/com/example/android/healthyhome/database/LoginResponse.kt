package com.example.android.healthyhome.database

data class LoginResponse(
    val error: Boolean,
    val uid: Int,
    val user: UserX,
    val error_msg: String
)