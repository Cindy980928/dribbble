package com.xiaoding.Ambled.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Layout
import android.text.TextUtils
import android.view.TextureView
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.xiaoding.Ambled.App
import com.xiaoding.Ambled.R
import com.xiaoding.Ambled.api.Api
import com.xiaoding.Ambled.api.WebService
import com.xiaoding.Ambled.utils.Logger
import com.xiaoding.Ambled.utils.Toaster
import com.xiaoding.Ambled.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity :BaseActivity(),View.OnClickListener{
    private var userViewModel:UserViewModel?=null
    var mywebview: WebView? = null
    var loginBtn:Button?=null
    var loginImg:ImageView?=null
    private val AUTH_URL = "https://dribbble.com/oauth/authorize"
    private val TOKEN_URL = "https://dribbble.com/oauth/token"
    private val CLIENT_ID = "6abed99d3ab15a77c049cbe9c5a34112966ae4bb2b277235b103a78e3854164f"
    private val CLIENT_SECRET = "70b25d83ea3d99b613c8879f584836b72f1fa8374da204eb5260571e7ce24a6e"
    private val SCOPE = "public+write+comment"
    private var code: String? = null
    private val token: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.get().activityStack!!.finishAllExpect(this)//结束除此之外的所有Activity

        setContentView(R.layout.activity_login)
        findViewById<View>(R.id.button_login).setOnClickListener(this)

        mywebview = findViewById<WebView>(R.id.mWebView)
        loginImg=findViewById<ImageView>(R.id.loginimg)
        loginBtn=findViewById<Button>(R.id.button_login)

        mywebview!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                closeLoadingDialog()
                if (url != null) {
                    if (url.contains("code")) {
                        //重定向网页，参数中包含了code
                        code = url.substring(url.lastIndexOf("=") + 1);
                        mywebview!!.setVisibility(View.GONE);
                        Logger.d("code=", code!!)
                        getToken()
                        return false;

                    }
                }
                view?.loadUrl(url)
                return true
            }
        }
    }

    private fun getToken() {
        if (userViewModel == null) {
            userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        }

        code?.let {
            Logger.d("code====",it)
            userViewModel!!.getToken(CLIENT_ID,CLIENT_SECRET, it).observe(this, Observer { resource ->
            if (resource!!.isSuccess) {
                startActivity(MainActivity::class.java)
                finish()
            } else {
                val msg = resources.getString(R.string.login_fail)
                Toaster.shortToast(msg)
            }
        })
        }
    }

    override fun onClick(v: View) {
        if(v.id==R.id.button_login){
            login()
        }
    }

    fun login(){
        showLoadingDialog()
        loginImg!!.setVisibility(View.GONE)
        loginBtn!!.setVisibility(View.GONE)
        mywebview!!.setVisibility(View.VISIBLE);
        mywebview!!.loadUrl("$AUTH_URL?client_id=$CLIENT_ID&scope=$SCOPE")
    }
}


