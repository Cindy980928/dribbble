package com.xiaoding.Ambled

import android.content.Intent
import android.content.SharedPreferences
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import android.support.multidex.MultiDexApplication
import com.xiaoding.Ambled.ui.LoginActivity
import com.xiaoding.Ambled.utils.ActivityStack

const val KEY_USER_TOKEN = "KEY_USER_TOKEN"

class App : MultiDexApplication() {
    var userToken: String? = null
        private set
    var mainHandler: Handler? = null
        private set
    var activityStack: ActivityStack? = null
        private set
    private lateinit var defaultSp: SharedPreferences

    private var percentMap = HashMap<String, Int>()

    override fun onCreate() {
        super.onCreate()
        activityStack = ActivityStack()
        mainHandler = Handler(Looper.getMainLooper())
        defaultSp = PreferenceManager.getDefaultSharedPreferences(this)
        userToken = defaultSp.getString(KEY_USER_TOKEN, null)
        app = this
    }

    fun saveUserToken(token: String) {
        this.userToken = token
        defaultSp.edit().putString(KEY_USER_TOKEN, token).apply()
    }

    fun clearUserToken() {
        this.userToken = null
        defaultSp.edit().remove(KEY_USER_TOKEN).apply()
//        clearServerToken()
    }

    fun setPercent(district: String, percent: Int) {
        percentMap[district] = percent
    }

    fun getPercent(district: String): Int? {
        return percentMap[district]
    }


//    private fun clearServerToken() {
//        sendToken("")
//    }
//
//    fun onUserTokenExpired() {
//        clearUserToken()
//        val intent = Intent(this, LoginActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        startActivity(intent)
//    }

//    fun uploadFireBaseMsgToken() {
//        val instanceId = FirebaseInstanceId.getInstance()
//        val task = instanceId.instanceId
//        task.addOnCompleteListener(OnCompleteListener { task ->
//            if (!task.isSuccessful) {
//                returnicon@OnCompleteListener
//            }
//            // Get new Instance ID userToken
//            if (task.result != null) {
//                val token = task.result!!.token
//                sendToken(token)
//            }
//        })
//    }
//
//    fun sendToken(msgToken: String) {
//        if (userToken == null) {
//            returnicon
//        }
//        val request = UploadFCMTokenRequest()
//        request.token = msgToken
//        WebService.get().uploadFCMToken(request).enqueue(null)
//    }

    companion object {
        private lateinit var app: App

        fun get(): App {
            return app
        }
    }

}
