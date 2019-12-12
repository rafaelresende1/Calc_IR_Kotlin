package com.example.calcirkotlin.Model.ListaMoviModel

import android.os.Parcel
import android.os.Parcelable


class DeleteMoviModel(private val user_id: Int, private val acoes_id: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(user_id)
        parcel.writeInt(acoes_id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DeleteMoviModel> {
        override fun createFromParcel(parcel: Parcel): DeleteMoviModel {
            return DeleteMoviModel(parcel)
        }

        override fun newArray(size: Int): Array<DeleteMoviModel?> {
            return arrayOfNulls(size)
        }
    }
}