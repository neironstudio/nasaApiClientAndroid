package com.example.nasaexample.network

import com.google.gson.annotations.SerializedName

class NasaApiResponce<T> {
    @SerializedName("result")
    var result:Boolean?=null

    @SerializedName("data")
    var resultData: T? = null

    @SerializedName("error")
    var error:NasaApiError?=null

    fun isSuccessful(): Boolean? {
        val code = result
        return code
    }

    fun getErrorMessages(): String {
        var text = ""
        for (msg in error?.message.orEmpty())
            text += "code: " + msg + "\n"

        return text
    }
}