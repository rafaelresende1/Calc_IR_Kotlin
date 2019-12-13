package com.example.calcirkotlin.Model.ListaMoviModel

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MoviAddServiceModel(var user_id: Int,
                          var acoes_id: Int?,
                          var acoes: String,
                          var valor_unidade: Double,
                          var compra_venda: String,
                          var data_op: String,
                          var quantidade: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(user_id)
        parcel.writeValue(acoes_id)
        parcel.writeString(acoes)
        parcel.writeDouble(valor_unidade)
        parcel.writeString(compra_venda)
        parcel.writeString(data_op)
        parcel.writeInt(quantidade)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MoviAddServiceModel> {
        override fun createFromParcel(parcel: Parcel): MoviAddServiceModel {
            return MoviAddServiceModel(parcel)
        }

        override fun newArray(size: Int): Array<MoviAddServiceModel?> {
            return arrayOfNulls(size)
        }
    }
}

class MoviAddModel() : Parcelable {

    var user_id: Int? = 0
    var acoes_id: Int? = 0
    var acoes: String? = ""
    var valor_unidade: Double? = 0.0
    var compra_venda: String? = ""
    var data_op: String? = ""
    var quantidade: Int? = 0


    constructor(parcel: Parcel) : this() {
        user_id = parcel.readValue(Int::class.java.classLoader) as? Int
        acoes_id = parcel.readValue(Int::class.java.classLoader) as? Int
        acoes = parcel.readString()
        valor_unidade = parcel.readValue(Int::class.java.classLoader) as? Double
        compra_venda = parcel.readString()
        data_op = parcel.readString()
        quantidade = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(user_id)
        parcel.writeValue(acoes_id)
        parcel.writeString(acoes)
        parcel.writeValue(valor_unidade)
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

class MoviAddServiceSerializedModel {
    @SerializedName("user_id")
    @Expose
    var userId: Int? = null
    @SerializedName("acoes_id")
    @Expose
    var acoesId: Int? = null
    @SerializedName("acoes")
    @Expose
    var acoes: String? = null
    @SerializedName("valor_unidade")
    @Expose
    var valorUnidade: Double? = null
    @SerializedName("compra_venda")
    @Expose
    var compraVenda: String? = null
    @SerializedName("data_op")
    @Expose
    var dataOp: String? = null
    @SerializedName("quantidade")
    @Expose
    var quantidade: Int? = null

}