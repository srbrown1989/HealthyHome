package com.example.android.healthyhome.database

data class APIResponse(
    val error: Boolean,
    val uid: Int,
    val user: UserX,
    val error_msg: String
)