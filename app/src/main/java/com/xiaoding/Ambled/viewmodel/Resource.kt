package com.xiaoding.Ambled.viewmodel

open class Resource<T, E>(var data: T?, var error: E?) {

    val isSuccess: Boolean
        get() = error == null

    companion object {
        fun <T> success(t: T) = Resource(t, null)

        fun <E> error(e: E) = Resource(null, e)
    }
}

