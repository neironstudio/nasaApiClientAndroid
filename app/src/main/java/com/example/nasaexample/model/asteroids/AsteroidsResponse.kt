package com.example.nasaexample.model.asteroids

import com.google.gson.annotations.SerializedName

class AsteroidsResponse {
    @SerializedName("element_count")
    var element_count:Int?=null
    @SerializedName("near_earth_objects")
    var near_earth_objects:ItemPerDate?=null
}