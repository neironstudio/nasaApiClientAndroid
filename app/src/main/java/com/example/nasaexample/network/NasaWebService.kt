package com.example.nasaexample.network

import com.example.nasaexample.model.Apod
import com.example.nasaexample.model.asteroids.AsteroidsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaWebService {


@GET("planetary/apod")
suspend fun getPlanetaryApod(@Query ("api_key")api_key:String):Response<Apod>

@GET("neo/rest/v1/feed")
suspend fun getAsteroids(@Query("start_date")start_date:String,@Query("end_date")end_date:String,@Query ("api_key")api_key:String):Response<AsteroidsResponse>
}