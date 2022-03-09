package com.example.nasaexample.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nasaexample.model.Apod

@Dao
interface ApodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addApod(apod: Apod)

    @Query("SELECT * FROM apod")
    suspend fun getApods():LiveData<List<Apod>>
}