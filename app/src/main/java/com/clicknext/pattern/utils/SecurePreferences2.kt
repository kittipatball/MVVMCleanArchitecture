package com.clicknext.pattern.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class SecurePreferences2(val context: Context) {
    private lateinit var sharedPreferences:SharedPreferences

    init {
        val alias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        val fileName = Singleton.Preferences.fileName
        sharedPreferences =  EncryptedSharedPreferences.create(
            fileName,
            alias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun setStringSharedPreference(prefKey: String,value:String) {
        sharedPreferences.edit().putString(prefKey,value).apply()
    }

    fun getStringSharedPreference(prefKey: String): String? {
        return sharedPreferences.getString(prefKey , "")
    }

}