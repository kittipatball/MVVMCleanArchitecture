package com.clicknext.pattern

import android.app.Application
import com.clicknext.pattern.di.databaseModule
import com.clicknext.pattern.di.netModule
import com.clicknext.pattern.di.repositoryModule
import com.clicknext.pattern.di.viewModelModule
import com.clicknext.pattern.utils.SecurePreferences2
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

val sharedPreferences : SecurePreferences2 by lazy {
    BaseApplication.prefs!!
}

class BaseApplication : Application() {

    companion object{
        var prefs : SecurePreferences2? = null
    }

    override fun onCreate()
    {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@BaseApplication)
            modules(listOf(viewModelModule, repositoryModule, netModule, databaseModule))

            prefs = SecurePreferences2(this@BaseApplication)
        }
    }
}