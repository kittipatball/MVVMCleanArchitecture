package com.clicknext.pattern.view.example

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.clicknext.pattern.connection.CheckResponseStatus
import com.clicknext.pattern.connection.Header
import com.clicknext.pattern.databinding.ActivityExampleBinding
import com.clicknext.pattern.view.BaseActivity
import com.clicknext.pattern.view.example.adapter.ExampleAdapter
import com.clicknext.pattern.view.example.model.ExampleModel
import com.clicknext.pattern.viewmodel.ContactViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ExampleActivity : BaseActivity() {

    private val mContactViewModel by viewModel<ContactViewModel>()
    private lateinit var mBinding: ActivityExampleBinding

    private var mExampleMenuList = ArrayList<ExampleModel>()
    private var mExampleAdapter: ExampleAdapter? = null

    companion object {
        const val PERMISSION_REQUEST_CODE = 123
        const val SERVICE = 1
        const val REQUEST_PERMISSION = 2
        const val CHECK_PERMISSION = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        addExample(ExampleModel(SERVICE , "CallService"))
        addExample(ExampleModel(REQUEST_PERMISSION , "RequestPermission"))
        addExample(ExampleModel(CHECK_PERMISSION , "CheckPermission"))
        setExampleAdapter()
        attachObserver()
    }

    private fun initView() {
        mBinding = ActivityExampleBinding.inflate(layoutInflater)
        val view = mBinding.root
        setContentView(view)
    }

    private fun setExampleAdapter() {
        mExampleAdapter = ExampleAdapter(this@ExampleActivity ,
            mExampleMenuList ,
            object : ExampleAdapter.OnItemClickListener {
                override fun onItemClickListener(position: Int) {
                    when (mExampleMenuList[position].id) {
                        SERVICE -> {
                            callServiceGetContact()
                        }

                        REQUEST_PERMISSION -> {
                            requestPermission()
                        }

                        CHECK_PERMISSION -> {

                        }
                    }
                }
            })
        val layoutManager = LinearLayoutManager(this@ExampleActivity
            , LinearLayoutManager.VERTICAL, false)
        mBinding.rcvExampleActivity.layoutManager = layoutManager
        mBinding.rcvExampleActivity.adapter = mExampleAdapter
        mExampleAdapter?.notifyDataSetChanged()
    }

    private fun addExample(result: ExampleModel?) {
        if(result != null){
            mExampleMenuList.add(result)
        }
    }

    private fun requestPermission() {
        val permissionList = arrayOf<String>(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_CALENDAR
        )

        requestPermissions(PERMISSION_REQUEST_CODE ,
            permissionList ,
            object : OnRequestPermissionsListener{
                override fun onPermissionGranted() {
                    Toast.makeText(this@ExampleActivity , "PermissionGranted" , Toast.LENGTH_SHORT).show()
                }
                override fun onPermissionDenied(message: String?) {
                    Toast.makeText(this@ExampleActivity , message , Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun attachObserver() {
        mContactViewModel.mResultContactLiveData.observe(this, Observer
        {

            if(!CheckResponseStatus.checkResponseStatusError(this@ExampleActivity , it?.responseStatus))
            {
                Toast.makeText(this@ExampleActivity , it?.result?.get(0)?.user , Toast.LENGTH_SHORT).show()
            }
            hideLoading()
        })
    }

    private fun callServiceGetContact() {
        showLoading()
        mContactViewModel.callServiceGetContact()
    }
}
