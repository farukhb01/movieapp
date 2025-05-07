package com.example.movieapp

import android.app.Application
import com.example.movieapp.di.AppContainer

class MovieApp: Application() {

    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
    }
}