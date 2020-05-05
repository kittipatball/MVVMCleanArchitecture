package com.clicknext.pattern.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.clicknext.pattern.database.dao.ContactDao
import com.clicknext.pattern.database.entity.Entity
import com.clicknext.pattern.utils.Singleton

@Database(entities = [Entity.ContactEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase()
{
    abstract val contactDao: ContactDao
}