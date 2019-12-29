package com.example.calcirkotlin.ui.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.calcirkotlin.R
import kotlinx.android.synthetic.main.home_card_custodia_celula.view.*

class CardCustodiaAdapterHome internal constructor(
    private val listaCardCustodia: MutableList<ListaMedia>,
    private var activity: FragmentActivity?

) :
    RecyclerView.Adapter<CardCustodiaAdapterHome.CardAdapterViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardAdapterViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.home_card_custodia_celula, parent, false)
        return CardAdapterViewHolder(v)
    }

    override fun onBindViewHolder(holder: CardAdapterViewHolder, position: Int) {

        val c = listaCardCustodia?.get(position)
        holder.itemView.txt_home_acao.text = c?.acao
        if(c?.cv=="C"||c?.cv=="c")
        {holder.itemView.txt_home_cv.text = "Compra"}
        else if(c?.cv=="V"||c?.cv=="v")
        {holder.itemView.txt_home_cv.text = "Venda"}
        holder.itemView.txt_home_quant.text = c?.quant.toString()
        holder.itemView.txt_home_media.text = (c?.somaValor / c?.quant).toString()

    }

    override fun getItemCount(): Int {
        return listaCardCustodia?.size!!
    }
    inner class CardAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txt_home_acao: TextView?
        var txt_home_cv: TextView?
        var txt_home_quant: TextView?
        var txt_home_media: TextView?


        init {
            txt_home_acao = itemView?.findViewById<TextView?>(R.id.text_acoes)
            txt_home_cv = itemView?.findViewById<TextView?>(R.id.text_valor)
            txt_home_quant = itemView?.findViewById<TextView?>(R.id.text_quantidade)
            txt_home_media = itemView?.findViewById<TextView?>(R.id.text_data)

        }
    }
}