package com.example.calcirkotlin.Model.LoginModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class TokenModel {
    @SerializedName("access_token")
    @Expose
    var accessToken: String? = null
    @SerializedName("refresh_token")
    @Expose
    var refreshToken: String? = null
    @SerializedName("user_id")
    @Expose
    var userId: Int? = null

}