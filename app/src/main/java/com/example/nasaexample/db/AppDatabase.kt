package com.example.nasaexample.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nasaexample.BuildConfig
import com.example.nasaexample.db.dao.ApodDao
import com.example.nasaexample.model.Apod



@Database(
    entities = [
       Apod::class,

    ],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun ApodDao():ApodDao

    companion object {

        @JvmSynthetic
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(ctx: Context) = Room
            .databaseBuilder(ctx, AppDatabase::class.java, "nasa.db")
            .apply { if (BuildConfig.DEBUG) fallbackToDestructiveMigration() }
            .build()
    }

}