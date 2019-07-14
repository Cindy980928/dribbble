package com.xiaoding.Ambled.api

interface ApiCallBack<T> {
    fun onSuccess(response: T)

    fun onError(error: HError)
    fun enqueue(apiCallBack: ApiCallBack<T>) {

    }
}
