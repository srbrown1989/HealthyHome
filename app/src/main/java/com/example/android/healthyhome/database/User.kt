package com.example.android.healthyhome.database

data class User(
    val uid: Int,
    val created_at: String,
    val email: String,
    val isProvider: Int,
    val name: String,
    val updated_at: Any
)