package com.xiaoding.Ambled.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.xiaoding.Ambled.api.WebService
import com.xiaoding.Ambled.api.entity.MyshotList
import com.xiaoding.Ambled.api.entity.UserResponse
import com.xiaoding.Ambled.utils.Logger
import com.xiaoding.Ambled.viewmodel.NetworkResource
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Reponsitory{
    fun getUserMessage(usertoken:String):LiveData<NetworkResource<UserResponse>>{
        val result = MutableLiveData<NetworkResource<UserResponse>>()
        WebService.get().getUserMessage(usertoken).enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Logger.d("Repinsity","fail")
                result.postValue(NetworkResource.error(t)as NetworkResource<UserResponse>?)
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                Logger.d("Repinsity","success")
                result.postValue(NetworkResource.success(response.body())as NetworkResource<UserResponse>?)
            }
        })
        return result

    }

    fun getUserShots(usertoken: String):LiveData<NetworkResource<ResponseBody>>{
        val result = MutableLiveData<NetworkResource<ResponseBody>>()
        WebService.get().getUserShots(usertoken).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Logger.d("Repinsity","getUserShotsfail")
                result.postValue(NetworkResource.error(t)as NetworkResource<ResponseBody>?)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Logger.d("Repinsity","getUserShotssuccess")
                result.postValue(NetworkResource.success(response.body())as NetworkResource<ResponseBody>?)
            }
        })
        return result

    }
}