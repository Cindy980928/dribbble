package com.xiaoding.Ambled.api

import com.xiaoding.Ambled.api.entity.TokenRequest
import com.xiaoding.Ambled.api.entity.TokenResponse
import com.xiaoding.Ambled.api.entity.UserResponse
import com.xiaoding.Ambled.api.entity.MyshotList
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {
    companion object {
        val BASE_URL = BuildConfig.BASE_URL
        val TAKEN_URL:String = BuildConfig.TAKEN_URL
    }
    @POST("oauth/token")
    fun getToken(@Body request: TokenRequest): Call<TokenResponse>

    @GET("user")
    fun getUserMessage(@Query("access_token") accessToken:String):Call<UserResponse>

    @GET("user/shots")
    fun getUserShots(@Query("access_token") accessToken:String):Call<ResponseBody>

}