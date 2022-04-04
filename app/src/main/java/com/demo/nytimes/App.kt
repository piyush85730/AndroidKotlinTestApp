package com.demo.nytimes

import android.app.Application
import com.demo.nytimes.di.repositoryModule
import com.demo.nytimes.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin() {
            androidContext(this@App)
            modules(
                listOf(viewModelModule, repositoryModule)
            )
        }
    }
}