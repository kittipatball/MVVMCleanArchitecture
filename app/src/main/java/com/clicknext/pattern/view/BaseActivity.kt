package com.clicknext.pattern.view

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.clicknext.pattern.utils.dialog.ProgressDialog

open class BaseActivity : AppCompatActivity() {

    companion object {
        var mProgressDialog: ProgressDialog? = null
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    open fun removeAllFragment() {
        for (fragment in supportFragmentManager.fragments) {
            supportFragmentManager.beginTransaction().remove(fragment).commit()
        }
    }

    open fun setPagerSnapHelper(recyclerView: RecyclerView){
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
    }

    open fun showLoading() {
        hideLoading()
        mProgressDialog = ProgressDialog(this@BaseActivity)
        mProgressDialog?.show()
    }

    open fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog?.dismiss()
        }
    }

}