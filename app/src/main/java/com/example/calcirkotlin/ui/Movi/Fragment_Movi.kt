package com.example.calcirkotlin.ui.Movi

import android.content.Intent
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
import com.example.calcirkotlin.Model.ListaMoviModel.MoviAddServiceModel
import com.example.calcirkotlin.Model.LoginModel.TokenGetModel
import com.example.calcirkotlin.Model.LoginModel.TokenParcModel
import com.example.calcirkotlin.RestAPI.RetrofitInitializer
import com.example.calcirkotlin.RestAPI.Service
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.calcirkotlin.R
import com.example.calcirkotlin.Model.ListaMoviModel.ListMoviModel


class Fragment_Movi : Fragment() {
    private lateinit var moviViewModel: ViewModelMovi
    private val REQUEST_NEW = 1
    //private val REQUEST_ALTER = 2
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        moviViewModel = ViewModelProviders.of(this).get(ViewModelMovi::class.java)
        val root = inflater.inflate(R.layout.fragment_movi, container, false)

        val fab: FloatingActionButton = root.findViewById(R.id.fab)
        getList(root, activity)


        fab.setOnClickListener {
            val i = Intent(activity, MoviAddActivity::class.java)
            val moviAddServiceModel: MoviAddServiceModel? = MoviAddServiceModel(0,0,"",0.0,"","",0)
            i.putExtra("acao", moviAddServiceModel)
            val tokenParcModel = TokenParcModel(
                TokenGetModel.getAccess_token().toString(),
                TokenGetModel.getRefresh_token().toString(),
                TokenGetModel.getUser_id()
            )
            i.putExtra("token", tokenParcModel)
            startActivityForResult(i, REQUEST_NEW)
        }
        return root
    }
}

fun getList(root: View, activity: FragmentActivity?) {
    lateinit var moviRecy: RecyclerView
    var adapter: MoviAdapter


    moviRecy = root.findViewById(R.id.moviRecy)
    val linerLayout = LinearLayoutManager(activity)

    moviRecy.setLayoutManager(linerLayout)


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