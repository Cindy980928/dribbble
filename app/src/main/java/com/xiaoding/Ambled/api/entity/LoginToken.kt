package com.xiaoding.Ambled.api.entity

import com.google.gson.annotations.SerializedName


//{"avatar_url":"https://cdn.dribbble.com/assets/avatar-default-aa2eab7684294781f93bc99ad394a0eb3249c5768c21390163c9f55ea8ef83a4.gif",
//    "bio":"",
//    "can_upload_shot":true,
//    "created_at":"2018-12-12T19:30:02.999-05:00",
//    "followers_count":0,"html_url":"https://dribbble.com/kabyding",
//    "id":2925476,"links":{},
//    "location":"Beijing, China",
//    "login":"kabyding",
//    "name":"丁功春",
//    "pro":false,
//    "type":"User",
//    "teams":[]}
class LoginToken {
    @SerializedName("avatar_url")
    val avatar_url:String?=null

    @SerializedName("bio")
    val bio:String?=null

    @SerializedName("can_upload_shot")
    val can_upload_shot:String?=null

    @SerializedName("created_at")
    val created_at:String?=null

    @SerializedName("followers_count")
    val followers_count:String?=null

    @SerializedName("id")
    val id:String?=null

    @SerializedName("location")
    val location:String?=null

    @SerializedName("login")
    val login:String?=null

    @SerializedName("name")
    val name:String?=null

    @SerializedName("pro")
    val pro:String?=null

    @SerializedName("type")
    val type:String?=null

    @SerializedName("teams")
    val teams:String?=null



}