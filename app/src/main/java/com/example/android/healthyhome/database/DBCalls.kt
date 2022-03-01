package com.example.android.healthyhome.database

import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DBCalls {
    companion object {
        //Base API URL
        var baseURL = "https://api2.binance.com" //Obviously temporary

        //php scripts
        var servicesList = "/api/v3/ticker/price?symbol=" //Obviously temporary

        /**
         * Make an API call and have it returned in a specified return function
         * @param path Full url path of API call
         * @param responseFunction A lambda function that returns a string of the JSON response from the API call
         */
        fun getJSONFromPath(path: String, responseFunction: (String) -> Unit){
            Fuel.get(path)
                .response { req : Any?, res: Any?, result ->
                    val (bytes, error) = result
                    if (bytes != null) {
                        responseFunction(String(bytes))
                    }else{
                        println("API ERROR :DBCalls.getJSONFromPath")
                        println(error)
                        responseFunction("")
                    }
                }
        }

        /**
         * Uses API to return the provided services of a provider, given the provider ID
         * @param providerID pID of provider you wish to query
         * @param responseFunction A lambda function that returns MutableList<String> of the services
         */
        fun getServicesListFromProvider(providerID: String, responseFunction: (TestType) -> Unit){
                getJSONFromPath(baseURL + servicesList + providerID){rJson ->
                    if(rJson != ""){
                        //Type that you want to convert the json to
                        val typeStringList = object : TypeToken<TestType>() {}.type
                        //val typeStringList = object : TypeToken<MutableList<String>>() {}.type
                        val gson = Gson()
                        //Conversion
                        //var servicesList: MutableList<String> = gson.fromJson(rJson, typeStringList)
                        var servicesList: TestType = gson.fromJson(rJson, typeStringList)
                        responseFunction(servicesList)
                    }else{

                    }
                }
        }

    }
}