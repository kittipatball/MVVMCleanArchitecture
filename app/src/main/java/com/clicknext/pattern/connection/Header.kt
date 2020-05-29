package com.clicknext.pattern.connection

import android.content.Context
import com.clicknext.pattern.BuildConfig
import com.clicknext.pattern.utils.SecurePreferences
import java.util.HashMap

object Header {

    fun getHeader(context : Context?): HashMap<String, String?> {

        /* val model = Build.MANUFACTURER + " " + Build.MODEL
         val os = "android" + " " + Build.VERSION.RELEASE*/

        val hashMap = HashMap<String , String?>()
        if(context != null)
        {
            val securePreferences: SecurePreferences = SecurePreferences(context)
            hashMap["token"] = securePreferences.getStringSharedPreference(context, SecurePreferences.PREF_TOKEN)
            hashMap["version"] = BuildConfig.VERSION_NAME
            hashMap["language"] = securePreferences.getStringSharedPreference(
                context,
                SecurePreferences.PREF_LANGUAGE)
        }

        return hashMap
    }
}