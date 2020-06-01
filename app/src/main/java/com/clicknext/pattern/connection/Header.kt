package com.clicknext.pattern.connection

import android.content.Context
import com.clicknext.pattern.BuildConfig
import com.clicknext.pattern.sharedPreferences
import com.clicknext.pattern.utils.SecurePreferences
import java.util.HashMap

object Header {

    fun getHeader(): HashMap<String, String?> {

        /* val model = Build.MANUFACTURER + " " + Build.MODEL
         val os = "android" + " " + Build.VERSION.RELEASE*/

        val hashMap = HashMap<String , String?>()
        val securePreferences: SecurePreferences = sharedPreferences
        hashMap["token"] = securePreferences.getStringSharedPreference(SecurePreferences.PREF_TOKEN)
        hashMap["version"] = BuildConfig.VERSION_NAME
        hashMap["language"] = securePreferences.getStringSharedPreference(
            SecurePreferences.PREF_LANGUAGE)
        return hashMap
    }
}