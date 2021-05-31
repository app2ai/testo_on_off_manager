package com.example.motilal.utils

import android.app.Activity
import android.app.ProgressDialog
import android.os.Handler

private const val HIDE_PROGRESS_DELAY_MILLIS = 500L

class ProgressPresenter(private val activity: Activity) {

    private var progressViewDialog: ProgressDialog? = null

    val handler by lazy { Handler() }

    fun showProgress() {
        handler.removeCallbacksAndMessages(null)
        if (this.progressViewDialog != null) return

        progressViewDialog = ProgressDialog(activity).apply {
            setCancelable(false)
            setMessage("Loading...")
            show()
        }
    }

    fun hideProgress() {
        progressViewDialog?.let {  dialog ->
            handler.removeCallbacksAndMessages(null)
            handler.postDelayed({
                progressViewDialog = null
            }, HIDE_PROGRESS_DELAY_MILLIS)
            dialog.dismiss()
        }
    }
}