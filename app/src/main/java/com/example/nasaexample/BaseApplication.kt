package com.example.nasaexample

import android.app.Application
import com.example.nasaexample.di.applicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication :Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@BaseApplication)
            modules(applicationModule)
        }
    }
}