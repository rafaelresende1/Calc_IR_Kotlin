package com.example.calcirkotlin.Model.LoginModel

object TokenGetModel {
    private var access_token: String? = null
    private var refresh_token: String? = null
    private var user_id: Int = 0


    fun getAccess_token(): String? {
        return "Bearer $access_token"
    }

    fun setAccess_token(access_token: String?) {
        TokenGetModel.access_token = access_token
    }

    fun getRefresh_token(): String? {
        return refresh_token
    }

    fun setRefresh_token(refresh_token: String?) {
        TokenGetModel.refresh_token = refresh_token
    }

    fun getUser_id(): Int {
        return user_id
    }

    fun setUser_id(user_id: Int?) {
        if (user_id != null) {
            TokenGetModel.user_id = user_id
        }
    }
}