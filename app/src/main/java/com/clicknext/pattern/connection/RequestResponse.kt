package com.clicknext.pattern.connection

import retrofit2.Call

class RequestResponse {

    fun <T> request(call: Call<T>?): T?
    {
        return try
        {
            val response = call?.execute()
            when(response != null && response.isSuccessful)
            {
                true -> response.body()
                false -> null
            }
        }
        catch (exception : Throwable)
        {
            null
        }
    }


}