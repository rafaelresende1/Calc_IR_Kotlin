package com.example.calc_ir_android.model.ListaMoviModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ListMoviModel {
    @SerializedName("items")
    @Expose
    private var items: MutableList<Itens?>? = null

    fun getItems(): MutableList<Itens?>? {
        return items
    }

    fun setItems(items: MutableList<Itens?>?) {
        this.items = items
    }
}