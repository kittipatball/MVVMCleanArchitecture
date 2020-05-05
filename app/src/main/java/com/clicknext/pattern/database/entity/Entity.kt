package com.clicknext.pattern.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

class Entity {

    @Entity(tableName = "contact")
    data class ContactEntity(@PrimaryKey(autoGenerate = true) val id: Long,
                             @ColumnInfo(name = "name") var name: String? = null,
                             @ColumnInfo(name = "imageUrl") var imageUrl: String? = null)

}