package com.example.nasaexample.ui.theme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.compose.rememberImagePainter
import com.example.nasaexample.viewmodel.NasaViewModel

class ApodActivity : AppCompatActivity() {
    lateinit var nasaViewModel: NasaViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        getApod()
        nasaViewModel.imageLoad.observe(this, Observer {
            setContent {
                loadImageFromUrl(it)
            }
        })
    }
    @Composable
    fun loadImageFromUrl(url:String){
        Image(
            painter = rememberImagePainter(url),
            contentDescription = null,
            modifier = Modifier.size(512.dp)
        )
    }
    fun initViewModel(){
        nasaViewModel = ViewModelProvider(this).get(NasaViewModel::class.java)
    }
    fun getApod(){
        nasaViewModel.getApod()
    }
}