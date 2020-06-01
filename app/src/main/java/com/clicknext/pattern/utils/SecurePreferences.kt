package com.clicknext.pattern.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.google.gson.Gson

class SecurePreferences(context: Context) {

    private var securePreferences: SharedPreferences

    companion object {
        const val PREF_TOKEN = "F88DC12A38CE273FAE31FFC22F8B8867F176A3FA86BCF9550E047F2B37E8CD60"
        const val PREF_LANGUAGE = "8F1DC7902AE169FF21C354DA2D879A382FBEBB992428C61EB5FF4C2473A7074B"
    }

    init {
        val alias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        val fileName = Singleton.Preferences.fileName
        securePreferences =  EncryptedSharedPreferences.create(
            fileName,
            alias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun commitIntSharedPreference(prefKey: String, value: Int) {
        securePreferences.edit {
            putInt(prefKey , value)
        }
    }

    fun getIntSharedPreference(prefKey: String): Int? {
        return securePreferences.getInt(prefKey , -1)
    }

    fun commitBooleanSharedPreference(prefKey: String, value: Boolean) {
        securePreferences.edit {
            putBoolean(prefKey , value)
        }
    }

    fun getBooleanSharedPreference(prefKey: String): Boolean? {
        return securePreferences.getBoolean(prefKey , false)
    }

    fun commitStringSharedPreference(prefKey: String, value: String?) {
        securePreferences.edit {
            putString(prefKey , value)
        }
    }

    fun getStringSharedPreference(prefKey: String): String? {
        return securePreferences.getString(prefKey , "")
    }

    fun commitLongSharedPreference(prefKey: String, value: Long) {
        securePreferences.edit {
            putLong(prefKey , value)
        }
    }

    fun getLongSharedPreference(prefKey: String): Long? {
        return securePreferences.getLong(prefKey , -1)
    }

    fun commitArrayStringSharedPreference(prefKey: String, value: ArrayList<*>){
        val json = Gson().toJson(value)
        securePreferences.edit {
            putString(prefKey , json)
        }
    }

    fun getArrayStringSharedPreference(prefKey: String): ArrayList<*>?{
        val json = securePreferences.getString(prefKey , "")
        return Gson().fromJson(json , ArrayList::class.java) as ArrayList<*>
    }

    fun removeSharePreferenceByPrefKey(prefKey: String){
        securePreferences.edit {
            remove(prefKey)
        }
    }

    fun getAllKeyPreference(): MutableMap<String, *>? {
        return securePreferences.all
    }
}