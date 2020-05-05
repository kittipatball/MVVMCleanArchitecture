package com.clicknext.pattern

import android.app.Application
import android.content.Context
import com.clicknext.pattern.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {

    override fun onCreate()
    {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@BaseApplication)
            modules(listOf(viewModelModule, repositoryModule, netModule, databaseModule))
        }
    }
}