package com.example.calcirkotlin.Model.ListaMediaModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Item {


    @SerializedName("user_id")
    @Expose
    private var userId: Int? = null
    @SerializedName("id_result")
    @Expose
    private var idResult: Int? = null
    @SerializedName("acoes")
    @Expose
    private var acoes: String? = null
    @SerializedName("media_compra")
    @Expose
    private var mediaCompra: Double? = null
    @SerializedName("media_venda")
    @Expose
    private var mediaVenda: Double? = null
    @SerializedName("lucro")
    @Expose
    private var lucro: Double? = null
    @SerializedName("result_date")
    @Expose
    private var resultDate: String? = null
    @SerializedName("quant_compra_result")
    @Expose
    private var quantCompraResult: Int? = null
    @SerializedName("quant_venda_result")
    @Expose
    private var quantVendaResult: Int? = null

    fun getUserId(): Int? {
        return userId
    }

    fun setUserId(userId: Int?) {
        this.userId = userId
    }

    fun getIdResult(): Int? {
        return idResult
    }

    fun setIdResult(idResult: Int?) {
        this.idResult = idResult
    }

    fun getAcoes(): String? {
        return acoes
    }

    fun setAcoes(acoes: String) {
        this.acoes = acoes
    }

    fun getMediaCompra(): Double? {
        return mediaCompra
    }

    fun setMediaCompra(mediaCompra: Double?) {
        this.mediaCompra = mediaCompra
    }

    fun getMediaVenda(): Double? {
        return mediaVenda
    }

    fun setMediaVenda(mediaVenda: Double?) {
        this.mediaVenda = mediaVenda
    }

    fun getLucro(): Double? {
        return lucro
    }

    fun setLucro(lucro: Double?) {
        this.lucro = lucro
    }

    fun getResultDate(): String? {
        return resultDate
    }

    fun setResultDate(resultDate: String) {
        this.resultDate = resultDate
    }

    fun getQuantCompraResult(): Int? {
        return quantCompraResult
    }

    fun setQuantCompraResult(quantCompraResult: Int?) {
        this.quantCompraResult = quantCompraResult
    }

    fun getQuantVendaResult(): Int? {
        return quantVendaResult
    }

    fun setQuantVendaResult(quantVendaResult: Int?) {
        this.quantVendaResult = quantVendaResult
    }
}


