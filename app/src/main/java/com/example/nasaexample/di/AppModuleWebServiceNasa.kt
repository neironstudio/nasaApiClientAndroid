package com.example.nasaexample.di

import android.content.Context
import com.example.nasaexample.BuildConfig
import com.example.nasaexample.R
import com.example.nasaexample.network.NasaWebService
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class AppModuleWebServiceNasa (context_: Context) {
    var context:Context = context_

    fun provideWebServiceSecarApi(): NasaWebService {
        val okHttpClient = OkHttpClient()
            .newBuilder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val builder = originalRequest
                    .newBuilder()
                    /*.header(
                        "Authorization",
                        Credentials.basic(
                            "1","1"
                            *//*context.getString(R.string.apiUserName),
                            context.getString(R.string.apiPass)*//*
                        )

                    )*/
                val newRequest = builder.build()
                chain.proceed(newRequest)
            }
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(context.getString(R.string.baseUrl))
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(NasaWebService::class.java)

    }
    private fun loggingInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            //HttpLoggingInterceptor.Level.NONE
             HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
}