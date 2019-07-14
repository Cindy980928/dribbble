package com.xiaoding.Ambled.viewmodel

import com.xiaoding.Ambled.api.HError

class NetworkResource<T> private constructor(t: T?, hError: HError?) : Resource<T, HError>(t, hError) {

    companion object {

        fun <T> success(t: T) = NetworkResource(t, null)

        fun <T> error(e: HError, t: T?) = NetworkResource(t, e)

        fun<T> error(t:T?)=NetworkResource(t,null)
    }

}
