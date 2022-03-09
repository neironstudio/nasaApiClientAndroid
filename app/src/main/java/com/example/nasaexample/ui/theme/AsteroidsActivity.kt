package com.example.nasaexample.ui.theme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nasaexample.viewmodel.NasaViewModel

class AsteroidsActivity : AppCompatActivity() {
    lateinit var nasaViewModel: NasaViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        getAsteroids()
        nasaViewModel.asteroids.observe(this, Observer {
            val z=1
        })
    }
    fun initViewModel(){
        nasaViewModel = ViewModelProvider(this).get(NasaViewModel::class.java)
    }
    fun getAsteroids(){
        nasaViewModel.getAsteroids()
    }
}