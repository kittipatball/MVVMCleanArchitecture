package com.clicknext.pattern.utils.dialog

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.clicknext.pattern.R
import com.clicknext.pattern.databinding.DialogOneButtonBinding

class OneButtonDialog(mContext: Context) : Dialog(mContext) {

    private var mBinding: DialogOneButtonBinding

    init {
        window!!.requestFeature(android.view.Window.FEATURE_NO_TITLE)
        mBinding = DialogOneButtonBinding.inflate(layoutInflater)
        val view = mBinding.root
        setContentView(view)
        window!!.setBackgroundDrawable(android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT))
        window!!.setLayout(android.view.WindowManager.LayoutParams.MATCH_PARENT
            , android.view.WindowManager.LayoutParams.WRAP_CONTENT)
        setCancelable(false)
    }

    fun setMessage(title: String?){
        mBinding.txvMessageOneButtonDialog.text = title
    }

    fun setTitleButton(title: String?){
        mBinding.txvTitleButtonOneButtonDialog.text = title
    }

    fun onClickButtonListener(listener: View.OnClickListener) {
        mBinding.rlyButtonOneButtonDialog.setOnClickListener(listener)
    }
}