package com.xiaoding.Ambled.api

import com.xiaoding.Ambled.App
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class WebService private constructor() {

    companion object {
        var api: Api? = null
            get() {
                if (field == null) {
                    field = createApi(Api.BASE_URL)
                }
                return field
            }

        fun get(): Api {
            return api!!
        }


        var getTokenApi:Api?=null
            get() {
                if(field==null){
                    field= createApi(Api.TAKEN_URL)
                }
                return field
            }

        fun getToken():Api{
            return getTokenApi!!
        }


        private fun createApi(baseUrl: String): Api {
            val retrofit = Retrofit.Builder()
                    .client(createHttpClient())
                    .baseUrl(baseUrl)
//                    .addCallAdapterFactory(MyCallAdapterFactory())//想把返回值定义为Observable对象
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            // 返回网络请求接口的实例
            return retrofit.create(Api::class.java)
        }


        private fun createHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .addInterceptor(ParameterInterceptor())
                    .build()
        }
    }


    private class ParameterInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            val appToken = App.get().userToken
            if (appToken != null) {
                request = request.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Authorization", appToken)
                        .build()
            }
            return chain.proceed(request)
        }
    }
}
