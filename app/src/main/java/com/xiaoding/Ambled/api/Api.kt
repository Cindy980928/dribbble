package com.xiaoding.Ambled.api

import com.xiaoding.Ambled.api.entity.TokenRequest
import com.xiaoding.Ambled.api.entity.TokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {
    companion object {
        val BASE_URL = BuildConfig.BASE_URL
        val TAKEN_URL:String = BuildConfig.TAKEN_URL
    }
    @POST("oauth/token")
    fun getToken(@Body request: TokenRequest): Call<TokenResponse>
}