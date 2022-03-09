package com.example.nasaexample.model

import com.google.gson.annotations.SerializedName

// Astronomy Picture of the Day
class Apod {
 @SerializedName("date")
 var date:String?=null
 @SerializedName("explanation")
 var explanation:String?=null
 @SerializedName("hdurl")
 var hdurl:String?=null
 @SerializedName("media_type")
 var media_type:String?=null
 @SerializedName("title")
 var title:String?=null
 @SerializedName("url")
 var url:String?=null
}