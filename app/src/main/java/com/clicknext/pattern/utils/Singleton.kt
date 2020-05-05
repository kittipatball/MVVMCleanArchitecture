package com.clicknext.pattern.utils

object Singleton {

    object DataBase
    {
        val name = "awesome_database"
    }

    object StatusCode
    {
        var SUCCESS = "00000"
        var SESSION_TIME_OUT = "20002"
        var FORCE_UPDATE = "20003"
        var ERROR = "99999"
    }

    object Preferences
    {
        const val fileName = "secure_prefs"
    }
}