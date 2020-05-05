package com.clicknext.pattern.connection.repository

import com.clicknext.pattern.connection.api.Api
import com.clicknext.pattern.connection.RequestResponse
import com.clicknext.pattern.model.ResultContact
import java.util.HashMap

class ContactRepository (private val api: Api) {

    fun callServiceGetContact(header: HashMap<String, String?>): ResultContact? = RequestResponse().request(api.callServiceGetContact(header))
}