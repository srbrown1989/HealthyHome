package com.example.android.healthyhome.database

import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DBCalls {
    companion object {
        //Base API URL
        var baseURL = "https://raptor.kent.ac.uk/proj/comp6000/project/14/"

        //php scripts
        var servicesList = "getProviderExtras.php?pid="

        /**
         * Make an API call and have it returned in a specified return function
         * @param path Full url path of API call
         * @param responseFunction A lambda function that returns a string of the JSON response from the API call
         */
        fun getJSONFromPath(path: String, responseFunction: (String) -> Unit){
            //doAsync is a work around with the async nature of Fuel http requests that mean the thread its running on isnt
            //the same as the main UI Thread so we have to bring it back over to the UI Thread in order to interact with it
            doAsync {
                val (req, res, result) = path.httpGet().response()
                val (bytes, error) = result
                uiThread {
                    if (bytes != null) {
                        var medium = String(bytes)
                        medium = medium.substring(1, medium.length - 1)
                        responseFunction(medium)
                    } else {
                        println("API ERROR :DBCalls.getJSONFromPath")
                        println(error)
                        responseFunction("")
                    }
                }
            }
        }

        /**
         * Uses API to return the provided services of a provider, given the provider ID
         * @param providerID pID of provider you wish to query
         * @param responseFunction A lambda function that returns MutableList<String> of the services
         */
        fun getServicesListFromProvider(providerID: String, responseFunction: (MutableList<String>) -> Unit){
                getJSONFromPath(baseURL + servicesList + providerID){rJson ->
                    if(rJson != ""){
                        println(rJson)
                        //Type that you want to convert the json to
                        val typeExtras = object : TypeToken<ExtrasType>() {}.type
                        val gson = Gson()
                        //Conversion
                        var extrasList: ExtrasType = gson.fromJson(rJson, typeExtras)
                        if(extrasList.extras == null || extrasList.extras == ""){
                            responseFunction(mutableListOf())
                        }else{
                            var servList: MutableList<String> = extrasList.extras.split(",").toMutableList()
                            responseFunction(servList)
                        }
                    }else{

                    }
                }
        }

    }
}