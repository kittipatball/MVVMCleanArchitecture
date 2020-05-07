package com.clicknext.pattern.view

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
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

}