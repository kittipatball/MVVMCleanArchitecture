package com.clicknext.pattern.viewmodel

import android.app.Activity
import androidx.lifecycle.*
import com.clicknext.pattern.connection.CheckResponseStatus
import com.clicknext.pattern.connection.Header
import com.clicknext.pattern.model.ResultContact
import com.clicknext.pattern.connection.repository.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContactViewModel (private val contactRepository: ContactRepository): ViewModel() {

    var mResultContactLiveData: MutableLiveData<ResultContact> = MutableLiveData()

    fun callServiceGetContact(activity: Activity?)
    {
        viewModelScope.launch {
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