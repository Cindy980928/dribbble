package com.xiaoding.Ambled.api.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MyshotList:BaseResponse() {

        @SerializedName("images")
        val images:ShotImg?=null

        @SerializedName("published_at")
        val publishedAt:String?=null

        @SerializedName("title")
        val title:String?=null

        @SerializedName("id")
        val id:String?=null

        @SerializedName("description")
        val description:String?=null


}