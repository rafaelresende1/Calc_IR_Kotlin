package com.example.calcirkotlin.ui.Media

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
import com.example.calcirkotlin.Model.ListaMediaModel.ListaMediaModel
import com.example.calcirkotlin.Model.LoginModel.TokenGetModel
import com.example.calcirkotlin.R
import com.example.calcirkotlin.RestAPI.RetrofitInitializer
import com.example.calcirkotlin.RestAPI.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Fragment_Media : Fragment() {
    private var viewModel_Media: ViewModel_Media? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel_Media = ViewModelProviders.of(this).get(ViewModel_Media::class.java)
        val root = inflater.inflate(R.layout.fragment_media, container, false)

        getList2(root, activity)


        return root
    }
}

fun getList2(root: View, activity: FragmentActivity?) {
    lateinit var mediaRecy: RecyclerView
    var adapter: MediaAdapter


    mediaRecy = root.findViewById(R.id.mediaRecyMedia)
    val linerLayout = LinearLayoutManager(activity)
    mediaRecy.setLayoutManager(linerLayout)


    val retrofitClient = RetrofitInitializer.getRetrofitInstance()
    val endpoint = retrofitClient.create(Service.ListaMedia::class.java)
    val call = endpoint.listamedia(TokenGetModel.getAccess_token(), "application/json")


    call?.enqueue(object : Callback<ListaMediaModel?> {
        override fun onResponse(
            call: Call<ListaMediaModel?>?,
            response: Response<ListaMediaModel?>?
        ) {
            if (response != null) {
                if (response.isSuccessful()) { //verifica aqui se o corpo da resposta não é nulo
                    adapter = MediaAdapter(response.body()?.items, activity)
                    mediaRecy.setAdapter(adapter)

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

        override fun onFailure(call: Call<ListaMediaModel?>, t: Throwable) {
            Toast.makeText(
                activity?.getApplicationContext(),
                "Erro na chamada ao servidor",
                Toast.LENGTH_SHORT
            ).show()
        }
    })
}