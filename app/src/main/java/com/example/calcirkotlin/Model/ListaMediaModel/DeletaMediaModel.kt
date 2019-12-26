package com.example.calcirkotlin.Model.ListaMediaModel

import android.os.Parcel
import android.os.Parcelable

class DeletaMediaModel(private val user_id: Int, private val id_result: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(user_id)
        parcel.writeInt(id_result)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DeletaMediaModel> {
        override fun createFromParcel(parcel: Parcel): DeletaMediaModel {
            return DeletaMediaModel(parcel)
        }

        override fun newArray(size: Int): Array<DeletaMediaModel?> {
            return arrayOfNulls(size)
        }
    }

}