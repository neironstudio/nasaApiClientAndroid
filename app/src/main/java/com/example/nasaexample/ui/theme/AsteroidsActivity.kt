package com.example.nasaexample.ui.theme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nasaexample.model.asteroids.ObjectIem
import com.example.nasaexample.viewmodel.NasaViewModel

class AsteroidsActivity : AppCompatActivity() {
    lateinit var nasaViewModel: NasaViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        getAsteroids()
        nasaViewModel.asteroids.observe(this, Observer {
            setContent {
                it.near_earth_objects?.listItemPerDate?.let { asteroids -> asteroidsContent(asteroids) }
            }
        })
    }
    fun initViewModel(){
        nasaViewModel = ViewModelProvider(this).get(NasaViewModel::class.java)
    }
    fun getAsteroids(){
        nasaViewModel.getAsteroids()
    }
    @Composable
    fun asteroidsContent(asteroids:List<ObjectIem>) {
        val asteroids = remember { asteroids }
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(

                items = asteroids,
                itemContent = {
                    asteroidListItem(asteroidsItem = it)

                })
        }
    }
    @Composable
    fun asteroidListItem(asteroidsItem: ObjectIem) {
        Card(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth(),
            elevation = 2.dp,
            backgroundColor = Color.White,
            shape = RoundedCornerShape(corner = CornerSize(16.dp))
        ) {
            Row {
                //asterImage(asteroidsItem)
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                )
                {
                    Text(text = "asteroid ID: "+asteroidsItem.id.toString(), style = typography.h6)
                    Text(text = "asteroids Name: "+asteroidsItem.name.toString(), style = typography.h6)
                    Text(text = "VIEW DETAIL", style = typography.caption)
                }
            }
        }
    }
  /*  @Composable
    private fun asterImage(asteroidsItem: ObjectIem) {
        Image(
            painter = painterResource(id = asteroidsItem.ImageId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(84.dp)
                .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
        )
    }*/
}