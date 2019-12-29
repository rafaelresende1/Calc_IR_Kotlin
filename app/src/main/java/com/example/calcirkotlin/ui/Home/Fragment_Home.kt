package com.example.calcirkotlin.ui.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calcirkotlin.Model.ListaMoviModel.Itens
import com.example.calcirkotlin.Model.ListaMoviModel.ListMoviModel
import com.example.calcirkotlin.Model.LoginModel.TokenGetModel
import com.example.calcirkotlin.R
import com.example.calcirkotlin.RestAPI.RetrofitInitializer
import com.example.calcirkotlin.RestAPI.Service
import com.example.calcirkotlin.ui.Movi.MoviAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Fragment_Home : Fragment() {
    private var homeViewModel: ViewModelHome? = null
    lateinit var media : MutableList<Itens>
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProviders.of(this).get(ViewModelHome::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)


        getList3(root, activity)


        return root
    }
}
fun getList3(root: View, activity: FragmentActivity?) {
    lateinit var cardMediaRecy: RecyclerView
    var adapter: CardCustodiaAdapterHome


    cardMediaRecy = root.findViewById(R.id.cardCustodiaRecyMedia)
    val linerLayout = LinearLayoutManager(activity)

    cardMediaRecy.setLayoutManager(linerLayout)


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

                    val resp = response.body()?.getItems()
                    val media = mutableListOf<Itens>()
                    resp?.forEach { x ->
                        if (x?.finalizado!!.equals(false)) {
                            media.add(x)
                        }
                    }
                    var list = mutableListOf<ListaMedia>()
                    media?.forEach { x ->
                        var i = false
                        list.forEach { y ->
                            if (y.acao.equals(x.getAcoes())) {
                                y.quant += x.getQuantidade()!!
                                y.somaValor += (x.getQuantidade()!! * x.getValorUnidade()!!)
                                i = true
                            }
                        }
                        if (i.equals(false)) {
                            list.add(
                                ListaMedia(
                                    x.getAcoes().toString(),
                                    x.getCompraVenda().toString(),
                                    x.getQuantidade()!!,
                                    x.getValorUnidade()!! * x.getQuantidade()!!
                                )
                            )

                        }
                    }
                    adapter = CardCustodiaAdapterHome(list , activity)
                    cardMediaRecy.setAdapter(adapter)

                } else {
                    Toast.makeText(
                        activity?.getApplicationContext(),
                        "Resposta não foi sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                    // segura os erros de requisição
                    //val errorBody = response.errorBody()
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