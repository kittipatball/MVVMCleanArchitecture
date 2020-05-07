package com.clicknext.pattern.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.clicknext.pattern.databinding.ActivityMainBinding
import com.clicknext.pattern.viewmodel.ContactViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val mContactViewModel by viewModel<ContactViewModel>()
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initView()
        onClickListener()
        attachObserver()
    }

    private fun onClickListener() {
        mBinding.btnGetContact.setOnClickListener{
            showLoading()
            callServiceGetContact()
        }
    }

    private fun initView() {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mBinding.root
        setContentView(view)
    }

    private fun attachObserver() {
        mContactViewModel.mResultContactLiveData.observe(this , Observer
        {
            if(it?.result != null){
                Toast.makeText(baseContext, it.result?.get(0)?.user, Toast.LENGTH_SHORT).show()
            }
            hideLoading()
        })
    }

    private fun callServiceGetContact() {
        mContactViewModel.callServiceGetContact(this)
    }
}
