package com.clicknext.pattern.database.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.clicknext.pattern.database.entity.Entity

@Dao
interface ContactDao {

    @Query("SELECT * FROM contact")
    fun queryAll(): LiveData<List<Entity.ContactEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(contacts: List<Entity.ContactEntity>)

}