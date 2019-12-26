package com.example.calcirkotlin.Model.ListaMediaModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ListaMediaModel {

    @SerializedName("items")
    @Expose
    var items: MutableList<Item>? = null



}
