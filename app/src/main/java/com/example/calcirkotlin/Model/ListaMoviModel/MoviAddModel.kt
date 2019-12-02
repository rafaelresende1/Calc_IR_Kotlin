package com.example.calcirkotlin.Model.ListaMoviModel

import android.os.Parcel
import android.os.Parcelable


class MoviAddModel() : Parcelable {

    var user_id: Int? = 0
    var acoes_id: Int? = 0
    var acoes: String? = ""
    var Double: Double? = 0.0
    var compra_venda: String? = ""
    var data_op: String? = ""
    var quantidade: Int? = 0

    constructor(parcel: Parcel) : this() {
        user_id = parcel.readValue(Int::class.java.classLoader) as? Int
        acoes_id = parcel.readValue(Int::class.java.classLoader) as? Int
        acoes = parcel.readString()
        Double = parcel.readValue(Int::class.java.classLoader) as? Double
        compra_venda = parcel.readString()
        data_op = parcel.readString()
        quantidade = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(user_id)
        parcel.writeValue(acoes_id)
        parcel.writeString(acoes)
        parcel.writeValue(Double)
        parcel.writeString(compra_venda)
        parcel.writeString(data_op)
        parcel.writeValue(quantidade)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MoviAddModel> {
        override fun createFromParcel(parcel: Parcel): MoviAddModel {
            return MoviAddModel(parcel)
        }

        override fun newArray(size: Int): Array<MoviAddModel?> {
            return arrayOfNulls(size)
        }
    }
}