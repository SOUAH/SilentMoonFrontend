package com.nistruct.meditation.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("_id")//get _id from json and put it in the id var I created
    @Expose
    var id : String,
    @SerializedName("userName")
    @Expose
    var userName : String,
    @SerializedName("email")
    @Expose
    var email : String,
    @SerializedName("notificationDays")
    @Expose
    var notificationDays : Array<String>,
    @SerializedName("notificationTime")
    @Expose
    var notificationTime : String,
    @SerializedName("favoriteTopic")
    @Expose
    var favoriteTopic : String,
)
