package com.revature.daggermitchexample.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id:Int,
    @SerializedName("username")
    val userName:String,
    @SerializedName("email")
    val email:String,
    @SerializedName("website")
    val website:String
)