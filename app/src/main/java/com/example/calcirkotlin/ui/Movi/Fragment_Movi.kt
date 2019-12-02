package com.example.calc_ir_android.ui.Movi

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
import com.example.calc_ir_android.model.ListaMoviModel.ListMoviModel
import com.example.calcirkotlin.Model.LoginModel.TokenGetModel
import com.example.calcirkotlin.R
import com.example.calcirkotlin.RestAPI.RetrofitInitializer
import com.example.calcirkotlin.RestAPI.Service
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Fragment_Movi : Fragment() {
    private lateinit var moviViewModel: ViewModelMovi
    private val REQUEST_NEW = 1
    private val REQUEST_ALTER = 2
    lateinit var root: View
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
            startActivityForResult(i, REQUEST_NEW)
        }
        return root
    }

}

fun recarregar() {
// Reload current fragment
    val f: Fragment? = Fragment().getFragmentManager()?.findFragmentById(R.id.fragment_Movi)
    var fragTransaction = f?.getFragmentManager()?.beginTransaction()

    fragTransaction?.detach(f!!)
    fragTransaction?.attach(f!!)
    fragTransaction?.commit()
}



fun getList(root: View, activity: FragmentActivity?) {
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
