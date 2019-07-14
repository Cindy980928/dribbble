package com.xiaoding.Ambled.utils

import android.widget.Toast
import com.xiaoding.Ambled.App

object Toaster {

    fun shortToast(msg: String) {
        val t = Toast.makeText(App.get(), null, Toast.LENGTH_SHORT)
        t.setText(msg)
        t.show()
    }

    fun shortToast(msg: Int) {
        val t = Toast.makeText(App.get(), null, Toast.LENGTH_SHORT)
        t.setText(msg)
        t.show()
    }

    fun longToast(msg: String) {
        val t = Toast.makeText(App.get(), null, Toast.LENGTH_LONG)
        t.setText(msg)
        t.show()
    }

    fun longToast(msg: Int) {
        val t = Toast.makeText(App.get(), null, Toast.LENGTH_LONG)
        t.setText(msg)
        t.show()
    }

}
