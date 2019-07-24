package com.xiaoding.Ambled.api.entity

import com.google.gson.annotations.SerializedName

class UserResponse:BaseResponse() {
    @SerializedName("avatar_url")
    val avatarUrl:String?=null//头像地址
    @SerializedName("id")
    val userId:String?=null
    @SerializedName("location")
    val location:String?=null
    @SerializedName("name")
    val userName:String?=null

}