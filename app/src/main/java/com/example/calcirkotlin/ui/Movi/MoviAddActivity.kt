package com.example.calcirkotlin.ui.Movi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.calcirkotlin.Model.ListaMoviModel.DelMoviModel
import com.example.calcirkotlin.Model.ListaMoviModel.DeleteMoviModel
import com.example.calcirkotlin.Model.ListaMoviModel.MoviAddModel
import com.example.calcirkotlin.Model.ListaMoviModel.MoviAddServiceModel
import com.example.calcirkotlin.Model.LoginModel.TokenGetModel
import com.example.calcirkotlin.Model.LoginModel.TokenParcModel
import com.example.calcirkotlin.R
import com.example.calcirkotlin.RestAPI.RetrofitInitializer
import com.example.calcirkotlin.RestAPI.Service
import com.example.calcirkotlin.ui.Principal.App_Logado
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviAddActivity : AppCompatActivity() {
    private var text_acoes: EditText? = null
    private var text_valor: EditText? = null
    private var text_data: EditText? = null
    private var text_cv: EditText? = null
    private var text_quatidade: EditText? = null
    private var button_movi: Button? = null
    private lateinit var acao: MoviAddModel
    private lateinit var token: TokenParcModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.movi_edit)
        text_acoes = findViewById<EditText?>(R.id.text_acoes)
        text_valor = findViewById<EditText?>(R.id.text_valor)
        text_data = findViewById<EditText?>(R.id.text_data)
        text_cv = findViewById<EditText?>(R.id.text_cv)
        text_quatidade = findViewById<EditText?>(R.id.text_quatidade)
        button_movi = findViewById<Button?>(R.id.button_movi)

        acao = intent.getParcelableExtra("acao")
        token = intent.getParcelableExtra("token")

        text_quatidade?.setText(acao.quantidade.toString())

        text_acoes?.setText(acao.acoes)
        text_valor?.setText(acao.Double.toString())
        text_data?.setText(acao.data_op)
        text_cv?.setText(acao.compra_venda)

        button_movi?.setOnClickListener(View.OnClickListener {
            Toast.makeText(applicationContext, "Clicado", Toast.LENGTH_SHORT).show()
            var moviAddModel = MoviAddServiceModel(
                2,
                null,
                text_acoes?.text.toString(),
                text_valor?.text.toString().toDouble(),
                text_cv?.text.toString(),
                text_data?.text.toString(),
                text_quatidade?.text.toString().toInt()
                )



            /////////////alterar ////////////////////////////

            addmovi(moviAddModel)

            val intentMain = Intent(
                this@MoviAddActivity,
                App_Logado::class.java
            )
            this@MoviAddActivity.startActivity(intentMain)
            Log.i("Content ", " Main layout ")
        })
    }
    fun addmovi(moviAddServiceModel: MoviAddServiceModel) {
        val retrofitClient = RetrofitInitializer.getRetrofitInstance()
        val endpoint = retrofitClient.create(Service.MoviAcoesService::class.java)
        val call = endpoint.addmovi(
            token.access_token,
            "application/json",
            moviAddServiceModel
        )
        call?.enqueue(object : Callback<MoviAddServiceModel?> {
            override fun onResponse(call: Call<MoviAddServiceModel?>, response: Response<MoviAddServiceModel?>) {

                val texte = response?.body()
                if (response != null) {
                    if (response.isSuccessful()) { //verifica aqui se o corpo da resposta não é nulo
                        Toast.makeText(
                            getApplicationContext(),
                            "Item adicionado",
                            Toast.LENGTH_SHORT
                        ).show()
                        //notifyDataSetChanged()
                    } else {
                        Toast.makeText(
                            getApplicationContext(),
                            "Erro ao adicionar",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            override fun onFailure(call: Call<MoviAddServiceModel?>, t: Throwable) {
                Toast.makeText(
                    getApplicationContext(),
                    "Erro na chamada ao servidor",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}