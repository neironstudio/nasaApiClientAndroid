package com.example.nasaexample.di

import com.example.nasaexample.simplecoinexample.HelloRepository
import com.example.nasaexample.simplecoinexample.HelloRepositoryImpl
import com.example.nasaexample.simplecoinexample.MySimplePresenter
import com.example.nasaexample.util.SharedPrefenceHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
//single (одиночный объект) —single при каждом запросе возвращает один и тот же экземпляр,
//factory (фабрика объектов) — каждый раз возвращает новый экземпляр.
//scoped (объект в области) — создается объект, который сохраняется в рамках периода существования связанной временной области.

val applicationModule = module(override = true) {

    single { SharedPrefenceHelper(androidContext()) }
    single {AppModuleWebServiceNasa(androidContext())}
   // single { AppModuleWebServiceSecarApi(androidContext()) }
   // single { AppDatabase.getInstance(androidContext()).cesarModelDao() }

   // single instance of HelloRepository
   single<HelloRepository> { HelloRepositoryImpl() }

   // Simple Presenter Factory
   factory { MySimplePresenter(get()) }
}
