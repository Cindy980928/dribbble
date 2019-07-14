package com.xiaoding.Ambled.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.support.annotation.Nullable
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Window
import android.view.WindowManager
import com.xiaoding.Ambled.App
import com.xiaoding.Ambled.R
import com.xiaoding.Ambled.utils.Logger
import com.xiaoding.Ambled.ui.MainActivity

class SplashActivity: BaseActivity(){//BaseActivity里关于Activity的管理
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.d("hello","hello app")

    requestWindowFeature(Window.FEATURE_NO_TITLE)
        //全屏
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)

        App.get().mainHandler!!.postDelayed({
            if(App.get().userToken!=null){
                startActivity(MainActivity::class.java)
            }
            else {
                Logger.d("hello","hello app")
                startActivity(LoginActivity::class.java)
            }
            finish()
        },2000)
    }
}