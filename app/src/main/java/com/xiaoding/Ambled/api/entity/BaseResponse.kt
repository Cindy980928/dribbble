package com.xiaoding.Ambled.api.entity

open class BaseResponse {    var code: Int = 0
    var msg: String? = null

    val isSuccess: Boolean
        get() = code == 0

    val isTokenExpired: Boolean
        get() = code == -1
}
