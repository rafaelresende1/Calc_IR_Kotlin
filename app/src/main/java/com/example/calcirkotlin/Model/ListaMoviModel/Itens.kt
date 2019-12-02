package com.example.calc_ir_android.model.ListaMoviModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Itens {
    @SerializedName("user_id")
    @Expose
    private var userId: Int? = null
    @SerializedName("acoes_id")
    @Expose
    private var acoesId: Int? = null
    @SerializedName("acoes")
    @Expose
    private var acoes: String? = null
    @SerializedName("valor_unidade")
    @Expose
    private var valorUnidade: Double? = null
    @SerializedName("compra_venda")
    @Expose
    private var compraVenda: String? = null
    @SerializedName("data_op")
    @Expose
    private var dataOp: String? = null
    @SerializedName("quantidade")
    @Expose
    private var quantidade: Int? = null

    fun getUserId(): Int? {
        return userId
    }

    fun setUserId(userId: Int?) {
        this.userId = userId
    }

    fun getAcoesId(): Int? {
        return acoesId
    }

    fun setAcoesId(acoesId: Int?) {
        this.acoesId = acoesId
    }

    fun getAcoes(): String? {
        return acoes
    }

    fun setAcoes(acoes: String?) {
        this.acoes = acoes
    }

    fun getValorUnidade(): Double? {
        return valorUnidade
    }

    fun setValorUnidade(valorUnidade: Double?) {
        this.valorUnidade = valorUnidade
    }

    fun getCompraVenda(): String? {
        return compraVenda
    }

    fun setCompraVenda(compraVenda: String?) {
        this.compraVenda = compraVenda
    }

    fun getDataOp(): String? {
        return dataOp
    }

    fun setDataOp(dataOp: String?) {
        this.dataOp = dataOp
    }

    fun getQuantidade(): Int? {
        return quantidade
    }

    fun setQuantidade(quantidade: Int?) {
        this.quantidade = quantidade
    }
}