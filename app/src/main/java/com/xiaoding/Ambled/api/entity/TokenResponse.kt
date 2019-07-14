package com.xiaoding.Ambled.api.entity

import com.google.gson.annotations.SerializedName


//"access_token":"0f186021842d097d3e96414894ca6cd5ee4742ae594bdb0d2927162fb53da61a","token_type":"Bearer","scope":"public upload","created_at":1562243951

class TokenResponse {
    @SerializedName("access_token")
    val accesstoken:String?=null

    @SerializedName("token_type")
    val tokentype:String?=null

    @SerializedName("scope")
    val scope:String?=null

//    val created_at:String?=null

}