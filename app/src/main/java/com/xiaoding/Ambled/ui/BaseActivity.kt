package com.xiaoding.Ambled.ui

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.WindowManager
import com.xiaoding.Ambled.App
import com.xiaoding.Ambled.utils.LoadingDialog

abstract class BaseActivity : FragmentActivity() {

    private var loadingDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.get().activityStack!!.push(this)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    override fun onDestroy() {
        super.onDestroy()
        App.get().activityStack!!.remove(this)
    }

    fun startActivity(cls: Class<*>) {
        val intent = Intent(this, cls)
        startActivity(intent)
    }

    fun showLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(this)
        }

        if (!loadingDialog!!.isShowing) {
            loadingDialog!!.show()
        }
    }

    fun closeLoadingDialog() {
        if (loadingDialog != null && loadingDialog!!.isShowing) {
            loadingDialog!!.dismiss()
            loadingDialog = null
        }
    }
}
