package com.example.calcirkotlin.Model.LoginModel

import android.os.Parcel
import android.os.Parcelable

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
class TokenParcModel(var access_token: String,
                     var refresh_token: String,
                     var user_id: Int = 0) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(access_token)
        parcel.writeString(refresh_token)
        parcel.writeInt(user_id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TokenParcModel> {
        override fun createFromParcel(parcel: Parcel): TokenParcModel {
            return TokenParcModel(parcel)
        }

        override fun newArray(size: Int): Array<TokenParcModel?> {
            return arrayOfNulls(size)
        }
    }


}