package com.example.appcentflickr

import android.app.Application
import com.example.appcentflickr.view_models.viewModelFactory
import com.ozzy.kukaf1.service.serviceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AppCentApp : Application() {

    override fun onCreate() {
        super.onCreate()


        startKoin{
            androidLogger(Level.DEBUG)
            androidContext(this@AppCentApp)
            modules(listOf(serviceModule, viewModelFactory))
        }
    }
}