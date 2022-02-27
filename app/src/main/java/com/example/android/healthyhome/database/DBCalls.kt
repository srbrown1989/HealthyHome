package com.example.android.healthyhome.database

import com.github.kittinunf.fuel.Fuel


class DBCalls {
    companion object {
        fun getResponseFromPath(path: String){
            Fuel.get(path)
                .response { request : Any?, response: Any?, result ->
                    val (bytes, error) = result
                    if (bytes != null) {
                        println("[response bytes] ${String(bytes)}")
                    }
                }
        }

    }
}