package com.clicknext.pattern.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.clicknext.pattern.utils.dialog.OneButtonDialog
import com.clicknext.pattern.utils.dialog.ProgressDialog

open class BaseFragment : Fragment(){

    companion object {
        var mProgressDialog: ProgressDialog? = null
    }

    open fun setPagerSnapHelper(recyclerView: RecyclerView){
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
    }

    fun hideKeyboard() {
        val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view!!.windowToken, 0)
    }

    open fun showLoading(context: Context) {
        hideLoading()
        BaseActivity.mProgressDialog = ProgressDialog(context)
        BaseActivity.mProgressDialog?.show()
    }

    open fun hideLoading() {
        if (BaseActivity.mProgressDialog != null && BaseActivity.mProgressDialog!!.isShowing) {
            BaseActivity.mProgressDialog?.dismiss()
        }
    }

    open fun requestPermissions(requestCode: Int,
                                permissionList: Array<String>,
                                listener: BaseActivity.OnRequestPermissionsListener
    )
    {
        if(BaseActivity.mOnRequestPermissionsListener == null && activity != null)
        {
            BaseActivity.mOnRequestPermissionsListener = listener
            val missingPermissions: MutableList<String> = ArrayList()

            for (permission in permissionList)
            {
                val result = ContextCompat.checkSelfPermission(activity!!, permission)
                if (result != PackageManager.PERMISSION_GRANTED)
                {
                    missingPermissions.add(permission)
                }

                if (shouldShowRequestPermissionRationale( permission )) {
                    // user also CHECKED "never ask again"
                    // you can either enable some fall back,
                    // disable features of your app
                    // or open another dialog explaining
                    // again the permission and directing to
                    // the app setting
                    val dialog = OneButtonDialog(activity!!)
                    dialog.setMessage("Open setting for request permissions. Because selected never ask again.")
                    dialog.onClickButtonListener(View.OnClickListener {
                        dialog.dismiss()
                        displaySettingApp(BaseActivity.REQUEST_CODE_SETTING)
                    })
                    dialog.show()
                    return
                }
            }

            if (missingPermissions.isNotEmpty())
            {
                val permissions = missingPermissions
                    .toTypedArray()
                ActivityCompat.requestPermissions(activity!!, permissions, requestCode)
            }

            BaseActivity.mOnRequestPermissionsListener = null
        }
    }

    open fun displaySettingApp(requestCode: Int){
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri: Uri = Uri.fromParts("package", activity?.packageName, null)
        intent.data = uri
        startActivityForResult(intent, requestCode)
    }

}