package com.xiaoding.Ambled.api


class HError private constructor(val errorCode: Int, val errorMsg: String?) {

    var businessCode: String? = null

    val isTokenExpiredError: Boolean
        get() = errorCode == 100

    companion object {
        val ERROR_NETWORK = -1
        val ERROR_SERVER = -2
        val ERROR_UNKNOWN = -3
        val ERROR_BUSSINESS = -4
        val ERROR_JSON = -5

        fun create(code: Int, msg: String?): HError {
            return HError(code, msg)
        }


        fun create(code: Int, businessCode: String, msg: String?): HError {
            val error = HError(code, msg)
            error.businessCode = businessCode
            return error
        }
    }
}
