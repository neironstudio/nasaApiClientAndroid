package com.example.nasaexample.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nasaexample.model.asteroids.AsteroidsResponse
import com.example.nasaexample.repo.NasaRepository
import kotlinx.coroutines.launch
import com.example.nasaexample.network.Result

class NasaViewModel(app: Application) : AndroidViewModel(app) {
private val nasaRepository = NasaRepository()
val imageLoad = MutableLiveData<String>()
val asteroids = MutableLiveData<AsteroidsResponse>()

    fun getApod(){
        viewModelScope.launch {
          val result =  nasaRepository.getApod()
            if (result is Result.Success) {
                imageLoad.postValue(result.data.url)
               val z= result.data

                /*if (result.data.resultData?.size == 0) {

                    errorApi.postValue("  у панели нет секций/сброс настроек ")

                }*/
            }
            if (result is Result.Error) {
               // errorApi.postValue(result.exception.toString())
            }
        }
    }
    fun getAsteroids(){

        viewModelScope.launch {
            val result =  nasaRepository.getAsteroids("2022-03-01","2022-03-04")
            if (result is Result.Success) {
                asteroids.postValue(result.data)
                val z= result.data

                /*if (result.data.resultData?.size == 0) {

                    errorApi.postValue("  у панели нет секций/сброс настроек ")

                }*/
            }
            if (result is Result.Error) {
                // errorApi.postValue(result.exception.toString())
            }
        }
    }
}