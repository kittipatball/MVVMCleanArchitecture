package com.clicknext.pattern.connection.api

import com.clicknext.pattern.model.ResultContact
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import java.util.HashMap

interface Api {

    @GET("v2/5eb1262c3300006000c69279")
    fun callServiceGetContact(@HeaderMap header: HashMap<String, String?>): Call<ResultContact>
}