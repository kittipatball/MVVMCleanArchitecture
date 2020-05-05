package com.clicknext.pattern.model

import com.google.gson.annotations.SerializedName

data class ResponseStatus(
    @SerializedName("clientMessage")
    var clientMessage: String?,
    @SerializedName("code")
    var code: String?,
    @SerializedName("operation")
    var operation: String?,
    @SerializedName("status")
    var status: String?
)