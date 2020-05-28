package com.clicknext.pattern.viewmodel

import android.app.Activity
import androidx.lifecycle.*
import com.clicknext.pattern.connection.CheckResponseStatus
import com.clicknext.pattern.connection.Header
import com.clicknext.pattern.model.ResultContact
import com.clicknext.pattern.connection.repository.ContactRepository
import com.clicknext.pattern.utils.SingleLiveEvent
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ContactViewModel (private val contactRepository: ContactRepository): ViewModel(), CoroutineScope {

    var mResultContactLiveData =
        SingleLiveEvent<ResultContact>()

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun callServiceGetContact(activity: Activity?)
    {
        launch {
            val response: ResultContact? = withContext(Dispatchers.IO)
            {
                contactRepository.callServiceGetContact(Header.getHeader(activity))
            }

            if(!CheckResponseStatus.checkResponseStatusError(activity , response?.responseStatus))
            {
                mResultContactLiveData.value = response
            }else{
                mResultContactLiveData.value = null
            }
        }
    }
}