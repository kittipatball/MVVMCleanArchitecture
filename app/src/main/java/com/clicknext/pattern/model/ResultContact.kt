package com.clicknext.pattern.model


import com.google.gson.annotations.SerializedName

data class ResultContact(
    @SerializedName("responseStatus")
    var responseStatus: ResponseStatus?,
    @SerializedName("result")
    var result: ArrayList<Result?>?
) {

    data class Result(
        @SerializedName("id")
        var id: String?,
        @SerializedName("user")
        var user: String?
    )
}