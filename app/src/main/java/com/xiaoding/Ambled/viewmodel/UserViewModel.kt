package com.xiaoding.Ambled.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.xiaoding.Ambled.App
import com.xiaoding.Ambled.api.ApiCallBack
import com.xiaoding.Ambled.api.HError
import com.xiaoding.Ambled.api.WebService
import com.xiaoding.Ambled.api.entity.TokenRequest
import com.xiaoding.Ambled.api.entity.TokenResponse
import com.xiaoding.Ambled.utils.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    fun getToken(client_id: String, client_secret: String,code:String): LiveData<NetworkResource<TokenResponse>> {
        val result = MutableLiveData<NetworkResource<TokenResponse>>()
        val request = TokenRequest()
        request.client_id = client_id
        request.client_secret = client_secret
        request.code=code
        Logger.d("request",request.code.toString())
        WebService.getToken().getToken(request).enqueue(object : Callback<TokenResponse> {
//            override fun onSuccess(response: TokenResponse) {
//                Logger.d("UserViewModel","success")
//                result.postValue(NetworkResource.success(response) as NetworkResource<TokenResponse>?)
//            }
//
//            override fun onError(error: HError) {
//                Logger.d("UserViewModel","errot:"+error.errorMsg.toString()+error.errorCode+error.isTokenExpiredError)
//                result.postValue(NetworkResource.error(error, null) as NetworkResource<TokenResponse>?)
//            }
            override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                result.postValue(NetworkResource.error(t) as NetworkResource<TokenResponse>?)
            }

            override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {
                Logger.d("access_token",response.body()!!.accesstoken.toString())
                App.get().saveUserToken(response.body()!!.accesstoken.toString())
                result.postValue(NetworkResource.success(response.body()) as NetworkResource<TokenResponse>?)
            }


        })
        return result
    }
}
