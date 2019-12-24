package com.example.calcirkotlin.Model.ListaMediaModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Item {

    @SerializedName("user_id")
    @Expose
    var userId: Int? = null
    @SerializedName("acoes")
    @Expose
    var acoes: String? = null
    @SerializedName("result_compra")
    @Expose
    var resultCompra: Double? = null
    @SerializedName("result_venda")
    @Expose
    var resultVenda: Double? = null
    @SerializedName("lucro")
    @Expose
    var lucro: Double? = null
    @SerializedName("result_date")
    @Expose
    var resultDate: String? = null

}
