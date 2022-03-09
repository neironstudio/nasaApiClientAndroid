package com.example.nasaexample.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// Astronomy Picture of the Day
@Entity
class Apod {
 @PrimaryKey(autoGenerate = true)
 var id:Int?=null
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