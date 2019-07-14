package com.xiaoding.Ambled.utils

import android.app.Dialog
import android.content.Context
import com.xiaoding.Ambled.R


class LoadingDialog @JvmOverloads constructor(context: Context, themeResId: Int = R.style.dialog) : Dialog(context, themeResId) {

    init {
        init()
    }

    private fun init() {
        setContentView(R.layout.loading)
    }
}