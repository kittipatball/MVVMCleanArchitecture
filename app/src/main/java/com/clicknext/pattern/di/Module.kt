package com.clicknext.pattern.di

import android.app.Application
import androidx.room.Room
import com.clicknext.pattern.connection.api.Api
import com.clicknext.pattern.database.AppDatabase
import com.clicknext.pattern.database.dao.ContactDao
import com.clicknext.pattern.connection.repository.ContactRepository
import com.clicknext.pattern.utils.Singleton
import com.clicknext.pattern.viewmodel.ContactViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val viewModelModule = module{
    single{ ContactViewModel(get()) }
}

val databaseModule = module {

    fun provideDatabase(application: Application): AppDatabase
    {
        return Room.databaseBuilder(application, AppDatabase::class.java, Singleton.DataBase.name)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }


    fun provideDao(database: AppDatabase): ContactDao
    {
        return database.contactDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
}

val repositoryModule = module {

    fun provideContactRepository(api: Api): ContactRepository
    {
        return ContactRepository(api)
    }
    single { provideContactRepository(get()) }

}