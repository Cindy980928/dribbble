package com.xiaoding.Ambled.api.entity

import retrofit2.http.Field

class TokenRequest {
    var client_id: String? = null
    var client_secret:String? = null
    var code:String? = null

}