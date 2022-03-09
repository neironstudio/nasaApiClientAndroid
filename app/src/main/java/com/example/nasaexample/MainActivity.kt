package com.example.nasaexample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.nasaexample.simplecoinexample.MySimplePresenter
import com.example.nasaexample.viewmodel.NasaViewModel
import org.koin.android.ext.android.inject
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.nasaexample.ui.theme.ApodActivity
import com.example.nasaexample.ui.theme.AsteroidsActivity


class MainActivity : ComponentActivity() {
    // Lazy injected MySimplePresenter
    val firstPresenter: MySimplePresenter by inject()
    lateinit var nasaViewModel: NasaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContent {

            //loadImage()
            // ScrollableColumnDemo()
             Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                apod()
                asteroids()
            }
        }
        Log.d("LOG_TAG", firstPresenter.sayHello())

       /* nasaViewModel.imageLoad.observe(this, Observer {
            setContent {
                loadImageFromUrl(it)
            }
        })*/
    }

    @Composable
    fun loadImageFromUrl(url:String){
        Image(
            painter = rememberImagePainter(url),
            contentDescription = null,
            modifier = Modifier.size(512.dp)
        )
    }
    @Composable
    fun loadImage() {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "Content description for visually impaired"
        )
    }
    @Composable
    fun ScrollableColumnDemo() {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            for (i in 1..100) {
                Text(
                    "User Name $i",
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.padding(10.dp)
                )
                Divider(color = Color.Black, thickness = 5.dp)
            }
        }
    }
    @Composable
    fun apod(){
        OutlinedButton(onClick = {
          this.startActivity(Intent(this,ApodActivity::class.java))
        })
        {
            Text("Astronomy Picture of the Day")
        }
    }
    @Composable
    fun asteroids(){
        OutlinedButton(onClick = {
            this.startActivity(Intent(this,AsteroidsActivity::class.java))
        })
        {
            Text("Asteroids")
        }
    }


}