package com.example.nasaexample.repo

import android.util.Log
import com.example.nasaexample.db.dao.ApodDao
import com.example.nasaexample.di.AppModuleWebServiceNasa
import com.example.nasaexample.model.Apod
import com.example.nasaexample.model.asteroids.AsteroidsResponse
import com.example.nasaexample.network.NasaApiResponce
import com.example.nasaexample.network.NasaWebService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject
import com.example.nasaexample.network.Result

class NasaRepository: KoinComponent {
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    private val appModuleWebServiceNasa :AppModuleWebServiceNasa by inject()
    private val nasaWebService:NasaWebService = appModuleWebServiceNasa.provideWebServiceSecarApi()
    private val apodDao:ApodDao by inject()
    val apiKey = "Oo4xmbM1x4oTRfRVoXpTq1igHydUF4h5IMdjD6DQ"



    suspend fun getApod():Result<Apod> =
    withContext(ioDispatcher)
    {
        try {
           val response = nasaWebService.getPlanetaryApod(apiKey)
            if (!response.isSuccessful) {
                return@withContext Result.Error(Exception(response.errorBody().toString()))
            }
            response.body()?.let {
                apodDao.addApod(it)
                return@withContext Result.Success(it)


            }

        } catch (e: java.lang.Exception) {
            Log.e(this.toString(), e.message.toString())
        }
        return@withContext Result.Error(NullPointerException())
    }

    suspend fun getAsteroids(start_date:String,end_date:String):Result<AsteroidsResponse> =
    withContext(ioDispatcher)
    {
        try {
            val response = nasaWebService.getAsteroids(start_date, end_date,apiKey)
            if (!response.isSuccessful) {
                return@withContext Result.Error(Exception(response.errorBody().toString()))
            }
            response.body()?.let {
                return@withContext Result.Success(it)

            }

        } catch (e: java.lang.Exception) {
            Log.e(this.toString(), e.message.toString())
        }
        return@withContext Result.Error(NullPointerException())
    }
}