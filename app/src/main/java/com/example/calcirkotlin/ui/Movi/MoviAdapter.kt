package com.example.calcirkotlin.ui.Movi

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.calcirkotlin.Model.ListaMoviModel.DelMoviModel
import com.example.calcirkotlin.Model.ListaMoviModel.DeleteMoviModel
import com.example.calcirkotlin.Model.ListaMoviModel.MoviAddServiceModel
import com.example.calcirkotlin.Model.LoginModel.TokenGetModel
import com.example.calcirkotlin.Model.LoginModel.TokenParcModel
import com.example.calcirkotlin.R
import com.example.calcirkotlin.RestAPI.RetrofitInitializer
import com.example.calcirkotlin.RestAPI.Service
import com.example.calcirkotlin.Model.ListaMoviModel.Itens
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviAdapter internal constructor(
    private val listaMovi: MutableList<Itens?>?,
    private var activity: FragmentActivity?
) :
    RecyclerView.Adapter<MoviAdapter.MoviViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.movi_celula, parent, false)

        return MoviViewHolder(v)
    }

    override fun onBindViewHolder(holder: MoviViewHolder, position: Int) {
        val c = listaMovi?.get(position)
        holder.text_acoes?.setText(c?.getAcoes())
        holder.text_valor?.setText( " R$ " + (c?.getValorUnidade()?.toBigDecimal()))
        holder.text_quantidade?.setText("x" + c?.getQuantidade().toString())
        holder.text_data?.setText("Data: " + c?.getDataOp())
        if (c?.getCompraVenda().equals("C")) {
            holder.text_cv?.setText("Compra: ")
            holder.text_cv?.setTextColor(parseColor("#357376"))
        } else if (c?.getCompraVenda().equals("V")) {
            holder.text_cv?.setText("Venda: ")
            holder.text_cv?.setTextColor(parseColor("#FF8C00"))
        }

        if(position %2 == 1)
        {
            holder.itemView.setBackgroundColor(parseColor("#ffffff"))
            holder.but_adicionais.setBackgroundColor(parseColor("#ffffff"))
        }
        else
        {
            holder.itemView.setBackgroundColor(parseColor("#f9f9f9"))
            holder.but_adicionais.setBackgroundColor(parseColor("#f9f9f9"))
        }

    }

    override fun getItemCount(): Int {
        return listaMovi?.size!!
    }

    inner class MoviViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        var text_acoes: TextView?
        var text_valor: TextView?
        var text_quantidade: TextView?
        var text_data: TextView?
        var text_cv: TextView?
        var but_adicionais: ImageButton


        init {
            text_acoes = v?.findViewById<TextView?>(R.id.text_acoes)
            text_valor = v?.findViewById<TextView?>(R.id.text_valor)
            text_quantidade = v?.findViewById<TextView?>(R.id.text_quantidade)
            text_data = v?.findViewById<TextView?>(R.id.text_data)
            text_cv = v?.findViewById<TextView?>(R.id.text_cv)
            but_adicionais = v.findViewById(R.id.but_adicionais) as ImageButton

            but_adicionais.setOnClickListener(this)

        }

        override fun onClick(v: View) {
            val REQUEST_ALTER = 2
            val c = listaMovi?.get(getAdapterPosition())
            val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
            builder.setTitle(c?.getAcoes())
            builder.setItems(
                arrayOf<CharSequence>("Alterar", "Excluir")
            ) { dialogInterface, i ->
                when (i) {
                    0 -> {
                        val tokenParcModel = TokenParcModel(
                            TokenGetModel.getAccess_token().toString(),
                            TokenGetModel.getRefresh_token().toString(),
                            TokenGetModel.getUser_id()
                        )
                        val moviAddModel = MoviAddServiceModel(
                            c?.getUserId()!!.toInt(),
                            c?.getAcoesId()!!.toInt(),
                            c.getAcoes().toString(),
                            c.getValorUnidade()!!.toDouble(),
                            c.getCompraVenda().toString(),
                            c.getDataOp().toString(),
                            c.getQuantidade()!!.toInt()
                        )
                        val i = Intent(v.getContext(), MoviAddActivity::class.java)
                        i.putExtra("acao", moviAddModel)
                        i.putExtra("token", tokenParcModel)
                        v.getContext().startActivity(i)
                        //v.getContext().startActivity(Intent(v.getContext(),MoviAddActivity::class.java))
                    }
                    1 -> {

                        exluirAPI(
                            c?.getUserId()!!.toInt(),
                            c?.getAcoesId()!!.toInt(),
                            getAdapterPosition()
                        )
                        Toast.makeText(
                            activity?.getApplicationContext(),
                            "Item excluído!",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }
            }
            builder.create().show()
        }
    }

    fun exluirAPI(use: Int, acoes: Int, position: Int) {
        val retrofitClient = RetrofitInitializer.getRetrofitInstance()
        val deleteMoviModel =
            DeleteMoviModel(use, acoes)
        val endpoint = retrofitClient.create(Service.MoviAcoesDellService::class.java)
        val call = endpoint.excluimovi(
            TokenGetModel.getAccess_token(),
            "application/json",
            deleteMoviModel
        )
        call?.enqueue(object : Callback<DelMoviModel?> {
            override fun onResponse(call: Call<DelMoviModel?>, response: Response<DelMoviModel?>) {

                val texte = response?.body()
                if (response != null) {
                    if (response.isSuccessful()) { //verifica aqui se o corpo da resposta não é nulo
                        Toast.makeText(
                            activity?.getApplicationContext(),
                            "Item Excluído",
                            Toast.LENGTH_SHORT
                        ).show()
                        listaMovi?.removeAt(position)
                        notifyDataSetChanged()
                    } else {
                        Toast.makeText(
                            activity?.getApplicationContext(),
                            "Erro ao apagar",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            override fun onFailure(call: Call<DelMoviModel?>, t: Throwable) {
                Toast.makeText(
                    activity?.getApplicationContext(),
                    "Erro na chamada ao servidor",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
