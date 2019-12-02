package com.example.calc_ir_android.ui.Movi

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calc_ir_android.model.ListaMoviModel.Itens
import com.example.calc_ir_android.model.ListaMoviModel.ListMoviModel
import com.example.calc_ir_android.ui.Movi.MoviAdapter.MoviViewHolder
import com.example.calcirkotlin.Model.ListaMoviModel.DelMoviModel
import com.example.calcirkotlin.Model.LoginModel.DeleteMoviModel
import com.example.calcirkotlin.Model.LoginModel.TokenGetModel
import com.example.calcirkotlin.R
import com.example.calcirkotlin.RestAPI.RetrofitInitializer
import com.example.calcirkotlin.RestAPI.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviAdapter internal constructor(
    private val listaMovi: MutableList<Itens?>?,
    private var activity: FragmentActivity?
) :
    RecyclerView.Adapter<MoviViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.movi_celula, parent, false)
        return MoviViewHolder(v)
    }

    override fun onBindViewHolder(holder: MoviViewHolder, position: Int) {
        val c = listaMovi?.get(position)
        holder.text_acoes?.setText(c?.getAcoes())
        holder.text_valor?.setText(c?.getValorUnidade().toString())
        holder.text_quantidade?.setText(c?.getQuantidade().toString())
        holder.text_data?.setText(c?.getDataOp())
        holder.text_cv?.setText(c?.getCompraVenda())

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
        protected var but_adicionais: ImageButton


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
            val c = listaMovi?.get(getAdapterPosition())
            val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
            builder.setTitle(c?.getAcoes())
            builder.setItems(
                arrayOf<CharSequence>("Alterar", "Excluir")
            ) { dialogInterface, i ->
                when (i) {
                    0 -> {
                        Toast.makeText(
                            activity?.getApplicationContext(),
                            "0" + getAdapterPosition(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    1 -> {

                        exluirAPI(c?.getUserId()!!.toInt(), c?.getAcoesId()!!.toInt())
                        Toast.makeText(
                            activity?.getApplicationContext(),
                            "Excluir " + getAdapterPosition(),
                            Toast.LENGTH_SHORT
                        ).show()
                        recarrega(activity, v )
                    }
                }
            }
            builder.create().show()
        }
    }

    fun exluirAPI(use: Int, acoes: Int) {
        val retrofitClient = RetrofitInitializer.getRetrofitInstance()
        val deleteMoviModel = DeleteMoviModel(use, acoes)
        val endpoint = retrofitClient.create(Service.ExcluirAcoesService::class.java)
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

fun recarrega(activity: FragmentActivity?, root: View) {
    lateinit var moviRecy: RecyclerView
    var adapter: MoviAdapter? = null


    moviRecy = root.findViewById(R.id.moviRecy)
    moviRecy.setHasFixedSize(true)
    val llm = LinearLayoutManager(activity)
    llm.orientation = LinearLayoutManager.VERTICAL
    moviRecy.setLayoutManager(llm)


    val retrofitClient = RetrofitInitializer.getRetrofitInstance()
    val endpoint = retrofitClient.create(Service.ListaAcoesService::class.java)
    val call = endpoint.listamovi(TokenGetModel.getAccess_token(), "application/json")


    call?.enqueue(object : Callback<ListMoviModel?> {
        override fun onResponse(
            call: Call<ListMoviModel?>?,
            response: Response<ListMoviModel?>?
        ) {
            if (response != null) {
                if (response.isSuccessful()) { //verifica aqui se o corpo da resposta não é nulo
                    adapter = MoviAdapter(response.body()?.getItems(), activity)
                    moviRecy.setAdapter(adapter)
                    var items = response.body()?.getItems()

                } else {
                    Toast.makeText(
                        activity?.getApplicationContext(),
                        "Resposta não foi sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                    // segura os erros de requisição
                    val errorBody = response.errorBody()
                }
            } else {
                Toast.makeText(
                    activity?.getApplicationContext(),
                    "Resposta nula do servidor",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        override fun onFailure(call: Call<ListMoviModel?>, t: Throwable) {
            Toast.makeText(
                activity?.getApplicationContext(),
                "Erro na chamada ao servidor",
                Toast.LENGTH_SHORT
            ).show()
        }
    })
}