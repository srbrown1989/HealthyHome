package com.example.android.healthyhome.util

import android.util.Log
import com.example.android.healthyhome.database.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class DatabaseAPI {

     fun getUser(uid : String) : User? {
         var database = FirebaseDatabase.getInstance().reference.child("Users")
         var user : User? = null
         database.child(uid).get().addOnSuccessListener{
           user = it.getValue(User::class.java)!!


        }.addOnFailureListener{
           Log.i("databaseAPI",it.toString())
        }
         return user

    }


}