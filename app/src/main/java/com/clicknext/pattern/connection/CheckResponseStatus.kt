package com.clicknext.pattern.connection

import android.app.Activity
import android.view.View
import android.widget.Toast
import com.clicknext.pattern.R
import com.clicknext.pattern.model.ResponseStatus
import com.clicknext.pattern.utils.Singleton
import com.clicknext.pattern.utils.dialog.OneButtonDialog

object CheckResponseStatus {

    object Dialog {
        var isShowing = false
    }

    fun checkResponseStatusError(activity: Activity?, responseStatus: ResponseStatus?) : Boolean
    {
        if(responseStatus != null )
        {
            return when (responseStatus.code)
            {
                Singleton.StatusCode.FORCE_UPDATE ->
                {
                    displayOneButtonDialog(activity, responseStatus.clientMessage , responseStatus.code)
                    true
                }

                Singleton.StatusCode.SESSION_TIME_OUT ->
                {
                    displayOneButtonDialog(activity, responseStatus.clientMessage , responseStatus.code)
                    true
                }

                Singleton.StatusCode.SUCCESS ->
                {
                    false
                }

                Singleton.StatusCode.ERROR ->
                {
                    displayOneButtonDialog(activity, responseStatus.clientMessage , responseStatus.code)
                    true
                }

                else ->
                {
                    displayOneButtonDialog(activity, activity?.getString(R.string.unable_to_connect_server) , responseStatus.code)
                    true
                }
            }

        }
        else
        {
            displayOneButtonDialog(activity, activity?.getString(R.string.unable_to_connect_server) , null)
            return true
        }
    }

    private fun displayOneButtonDialog(activity: Activity?, message: String?, statusCode: String?)
    {
        if (activity != null && !Dialog.isShowing)
        {
            val dialog = OneButtonDialog(activity)
            dialog.setMessage(message)
            dialog.setTitleButton(activity.getString(R.string.ok))
            dialog.onClickButtonListener(View.OnClickListener {
                when (statusCode)
                {
                    Singleton.StatusCode.SESSION_TIME_OUT ->
                    {

                    }

                    Singleton.StatusCode.FORCE_UPDATE ->
                    {
                        Toast.makeText(activity, "Waiting Play store...", Toast.LENGTH_SHORT).show()
                    }

                    else ->
                    {
                        dialog.dismiss()
                    }
                }
                Dialog.isShowing = false
                dialog.dismiss()
            })

            Dialog.isShowing = true
            dialog.show()
        }
    }
}