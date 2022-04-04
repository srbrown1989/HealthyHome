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
        var makeBooking = "makeBooking.php?"
        var postJob = "postJob.php?"
        var postJobResponse = "postJobResponse.php?"
        var custNameByID = "getCustomerNameByID.php?cid="
        var provNameByID = "getProviderNameByID.php?pid="
        var availByPID = "getProviderAvailability.php?pid="
        var bookingByPID = "getBookingsByPIDAndDate.php?"
        var emailByUID = "getEmailByUID.php?uid="
        var cidFromUID = "getCIDFromUID.php?uid="

        /**
         * Make an API call and have it returned in a specified return function
         * @param path Full url path of API call
         * @param responseFunction A lambda function that returns a string of the JSON response from the API call
         */
        fun getJSONFromPath(path: String, removeArray: Boolean, responseFunction: (String) -> Unit){
            //doAsync is a work around with the async nature of Fuel http requests that mean the thread its running on isnt
            //the same as the main UI Thread so we have to bring it back over to the UI Thread in order to interact with it
            doAsync {
                val (req, res, result) = path.httpGet().response()
                val (bytes, error) = result
                uiThread {
                    if (bytes != null) {
                        var medium = String(bytes)
                        if(medium[0] != '['){
                            println("API ERROR :Json format not received")
                            println(medium)
                            return@uiThread
                        }
                        println("JSON Received :")
                        println(medium)
                        if(removeArray) {
                            medium = medium.substring(1, medium.length - 1)
                        }
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
            getJSONFromPath(baseURL + servicesList + providerID, true){rJson ->
                if(rJson != ""){
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

        /**
         * Uses API to make a booking
         * @param cid ID of customer making booking
         * @param pid ID of provider being booked
         * @param date Date of when the booking will take place in the format YYYY/MM/DD
         * @param time Time of when the booking will take place in the format HHmm
         * @param extras String of extras selected, comma seperated no spaces
         */
        fun insertBooking(cid: String, pid: String, date: String, time: String, extras: String, halfHoursreq: Int){
            println("cid=${cid}&pid=${pid}&date=\"${date}\"&time=\"${time}\"&extras=\"${extras}\"&halfHoursreq=${halfHoursreq}")
            val variableString = "cid=${cid}&pid=${pid}&date=\"${date}\"&time=\"${time}\"&extras=\"${extras}\"&halfHoursreq=${halfHoursreq}"
            val fullURL = baseURL + makeBooking + variableString
            getJSONFromPath(fullURL, false){}
        }

        /**
         * Uses API to retrieve the customers full name as a String
         * @param customerID ID of customer for lookup
         * @param responseFunction A lambda function that returns String of the customers full name
         */
        fun getCustomerFullNameByID(customerID: String, responseFunction: (String) -> Unit){
            getJSONFromPath(baseURL + custNameByID + customerID, true){ rJson ->
                if(rJson != "" && rJson != "{}"){
                    val typeCustName = object : TypeToken<CustomerNameType>() {}.type
                    val gson = Gson()
                    val custNameObj: CustomerNameType = gson.fromJson(rJson, typeCustName)

                    responseFunction("${custNameObj.firstName} ${custNameObj.lastName}")
                }else{
                    responseFunction("Customer doesn't exist")
                }
            }
        }

        /**
         * Uses API to retrieve the E-Mail address of a user given by the UID
         * @param userID ID of the user for lookup
         * @param responseFunction A lambda function that returns String of the user E-Mail
         */
        fun getEmailByUID(userID: String, responseFunction: (String) -> Unit){
            getJSONFromPath(baseURL + emailByUID + userID, true){ rJson ->
                if(rJson != "" && rJson != "{}"){
                    val typeEmail = object : TypeToken<EmailType>() {}.type
                    val gson = Gson()
                    val uidEmail: EmailType = gson.fromJson(rJson, typeEmail)

                    responseFunction("${uidEmail.email}")
                }else{
                    println("UID has no email")
                }
            }
        }

        /**
         * Uses API to retrieve the providers company name as a String
         * @param providerID ID of provider for lookup
         * @param responseFunction A lambda function that returns String of the provider's company name
         */
        fun getProviderNameByID(providerID: String, responseFunction: (String) -> Unit){
            getJSONFromPath(baseURL + provNameByID + providerID, true){ rJson ->
                if(rJson != "" && rJson != "{}"){
                    val typeProvName = object : TypeToken<ProviderNameType>() {}.type
                    val gson = Gson()
                    val provNameObj: ProviderNameType = gson.fromJson(rJson, typeProvName)

                    responseFunction("${provNameObj.companyName}")
                }else{
                    responseFunction("Provider doesn't exist")
                }
            }
        }

        /**
         * Returns the availability of a provider
         * @param providerID ID of provider for lookup
         * @param responseFunction A lambda function that returns AvailabilityType with the providers availability
         */
        fun getAvailabilityByPID(providerID: String, responseFunction: (AvailabilityType) -> Unit){
            getJSONFromPath(baseURL + availByPID + providerID, true){ rJson ->
                if(rJson != "" && rJson != "{}"){
                    val typeAvail = object : TypeToken<AvailabilityType>() {}.type
                    val gson = Gson()
                    var provAvailability: AvailabilityType = gson.fromJson(rJson, typeAvail)
                    provAvailability.set = true

                    responseFunction(provAvailability)
                }else{
                    val unSet = AvailabilityType("","","","","","","", false)
                    responseFunction(unSet)
                }
            }
        }

        /**
         * Returns the CID of the user provided
         * @param uid UID of User for lookup
         * @param responseFunction A lambda function that returns String of CID
         */
        fun getCIDFromUID(uid: String, responseFunction: (String) -> Unit){
            getJSONFromPath(baseURL + cidFromUID + uid, true){ rJson ->
                if(rJson != "" && rJson != "{}"){
                    val typeCID = object : TypeToken<CIDType>() {}.type
                    val gson = Gson()
                    val cidResponse: CIDType = gson.fromJson(rJson, typeCID)
                    responseFunction(cidResponse.cid)
                }else{
                    println("Signed in user isnt a customer. Error")
                }
            }
        }

        /**
         * Posts a job response to the database
         * @param pid ProviderID of the provider responding
         * @param jid JobID of the job being responded to
         * @param msg Text contents of the response
         */
        fun postJobResponse(pid: String, jid: String, msg: String){
            val variableString = "pid=${pid}&jid=${jid}&msg=\"${msg}\""
            getJSONFromPath(baseURL + postJobResponse + variableString, false){}
        }

        /**
         * Posts a job listing to the database
         * @param cid CustomerID of the poster of the job
         * @param service Service String
         * @param msg Optional message
         */
        fun postJob(cid: String, service: String, msg: String){
            val variableString = "cid=${cid}&service=\"${service}\"&msg=\"${msg}\""
            getJSONFromPath(baseURL + postJob + variableString, false){}
        }

        /**
         * Returns a list of all bookings after current date for a particular provider
         * @param providerID ID of provider for lookup
         * @param curDate Date to lookup in format YYYY/MM/DD
         * @param responseFunction A lambda function that returns List<BookingType>
         */
        fun getBookingsByPIDAndDate(providerID: String, date: String, responseFunction: (List<BookingType>) -> Unit){
            val variableString = "pid=${providerID}&date=${date}"
            getJSONFromPath(baseURL + bookingByPID + variableString, false){ rJson ->
                val typeBookingList = object : TypeToken<List<BookingType>>() {}.type
                val gson = Gson()
                var bookingList: List<BookingType> = gson.fromJson(rJson, typeBookingList)

                responseFunction(bookingList)
            }
        }

    }
}