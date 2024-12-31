package com.example.rickandmortyapiwithjetpackcompose

import android.app.Application
import com.example.rickandmortyapiwithjetpackcompose.data.modules.dataModule
import com.example.rickandmortyapiwithjetpackcompose.ui.module.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(dataModule)
            modules(uiModule)
        }
    }
}