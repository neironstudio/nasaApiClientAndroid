package com.example.nasaexample.model.asteroids

import com.google.gson.annotations.SerializedName

class ObjectIem {
    @SerializedName("id")
    var id:String?=null
    @SerializedName("name")
    var name:String?=null
    @SerializedName("absolute_magnitude_h")
    var absolute_magnitude_h:Float?=null
    @SerializedName("estimated_diameter")
    var estimatedDiameter:Estimated_diameter?=null
    @SerializedName("is_potentially_hazardous_asteroid")
    var is_potentially_hazardous_asteroid:Boolean?=null

}