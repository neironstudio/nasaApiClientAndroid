package com.example.nasaexample.network

import com.google.gson.annotations.SerializedName

class NasaApiError {
    @SerializedName("timestamp")
    var timestamp:Long?=null

    @SerializedName("message")
    var message:String?=null

    @SerializedName("code")
    var code:Int?=null
}