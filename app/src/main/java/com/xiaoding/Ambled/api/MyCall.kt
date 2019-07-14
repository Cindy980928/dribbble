package com.xiaoding.Ambled.api

import java.io.IOException

interface MyCall<T> {
    fun cancel()

    @Throws(IOException::class)
    fun execute(): T?

    fun enqueue(callback: ApiCallBack<T>?)

    fun clone(): MyCall<T>
}