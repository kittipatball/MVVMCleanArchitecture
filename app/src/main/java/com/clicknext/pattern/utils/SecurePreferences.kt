package com.clicknext.pattern.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.google.gson.Gson
import java.security.MessageDigest

class SecurePreferences(context: Context) {

    private lateinit var securePreferences: SharedPreferences

    companion object {
        const val PREF_TOKEN = "F88DC12A38CE273FAE31FFC22F8B8867F176A3FA86BCF9550E047F2B37E8CD60"
        const val PREF_LANGUAGE = "8F1DC7902AE169FF21C354DA2D879A382FBEBB992428C61EB5FF4C2473A7074B"
        const val PREF_TEMP_2 = "6DA31C59141C9A2CE67218FB3DB4F4D6F231A08E015D5113E4B58BF5CBA71BE1"
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

    fun commitIntSharedPreference(context: Context, prefKey: String, value: Int) {
        securePreferences.edit {
            putInt(prefKey , value)
        }
    }

    fun getIntSharedPreference(context: Context, prefKey: String): Int? {
        return securePreferences.getInt(prefKey , -1)
    }

    fun commitBooleanSharedPreference(context: Context, prefKey: String, value: Boolean) {
        securePreferences.edit {
            putBoolean(prefKey , value)
        }
    }

    fun getBooleanSharedPreference(context: Context, prefKey: String): Boolean? {
        return securePreferences.getBoolean(prefKey , false)
    }

    fun commitStringSharedPreference(context: Context, prefKey: String, value: String?) {
        securePreferences.edit {
            putString(prefKey , value)
        }
    }

    fun getStringSharedPreference(context: Context, prefKey: String): String? {
        return securePreferences.getString(prefKey , "")
    }

    fun commitLongSharedPreference(context: Context, prefKey: String, value: Long) {
        securePreferences.edit {
            putLong(prefKey , value)
        }
    }

    fun getLongSharedPreference(context: Context, prefKey: String): Long? {
        return securePreferences.getLong(prefKey , -1)
    }

    fun commitArrayStringSharedPreference(context: Context, prefKey: String, value: ArrayList<*>){
        val json = Gson().toJson(value)
        securePreferences.edit {
            putString(prefKey , json)
        }
    }

    fun getArrayStringSharedPreference(context: Context, prefKey: String): ArrayList<*>?{
        val json = securePreferences.getString(prefKey , "")
        return Gson().fromJson(json , ArrayList::class.java) as ArrayList<*>
    }

    fun removeSharePreferenceByPrefKey(context: Context, prefKey: String){
        securePreferences.edit {
            remove(prefKey)
        }
    }

    fun getAllKeyPreference(context: Context): MutableMap<String, *>? {
        return securePreferences.all
    }
}