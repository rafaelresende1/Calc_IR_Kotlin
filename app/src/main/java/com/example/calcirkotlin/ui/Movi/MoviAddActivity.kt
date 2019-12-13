package com.example.calcirkotlin.ui.Movi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.calcirkotlin.Model.ListaMoviModel.*
import com.example.calcirkotlin.Model.LoginModel.TokenParcModel
import com.example.calcirkotlin.R
import com.example.calcirkotlin.RestAPI.RetrofitInitializer
import com.example.calcirkotlin.RestAPI.Service
import com.example.calcirkotlin.ui.Principal.App_Logado
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MoviAddActivity : AppCompatActivity() {
    private var text_acoes: TextInputLayout? = null
    private var text_valor: TextInputLayout? = null
    private var text_data: EditText? = null
    private var text_cv: TextInputLayout? = null
    private var text_quatidade: TextInputLayout? = null
    private var button_movi: Button? = null
    private lateinit var acao: MoviAddModel
    private lateinit var token: TokenParcModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.movi_edit)
        text_acoes = findViewById<TextInputLayout?>(R.id.text_acoes)
        text_valor = findViewById<TextInputLayout?>(R.id.text_valor)
        text_data = findViewById<EditText?>(R.id.text_data)
        text_cv = findViewById<TextInputLayout?>(R.id.text_cv)
        text_quatidade = findViewById<TextInputLayout?>(R.id.text_quatidade)
        button_movi = findViewById<Button?>(R.id.button_movi)

        acao = intent.getParcelableExtra("acao")
        token = intent.getParcelableExtra("token")

        text_quatidade?.editText?.setText(acao.quantidade.toString())
        text_acoes?.editText?.setText(acao.acoes.toString())
        text_valor?.editText?.setText(acao.valor_unidade.toString())
        text_data?.setText(acao.data_op)
        text_cv?.editText?.setText(acao.compra_venda)

        button_movi?.setOnClickListener {
            Toast.makeText(applicationContext, "Clicado", Toast.LENGTH_SHORT).show()
            val moviAddModel = MoviAddServiceModel(
                token.user_id,
                0,
                text_acoes?.getEditText()?.getText().toString(),
                text_valor?.getEditText()?.getText().toString().toDouble(),
                text_cv?.getEditText()?.getText().toString(),
                text_data?.text.toString(),
                text_quatidade?.getEditText()?.getText().toString().toInt()
            )


            addmovi(moviAddModel)

            val intentMain = Intent(
                this@MoviAddActivity,
                App_Logado::class.java
            )
            this@MoviAddActivity.startActivity(intentMain)
            Log.i("Content ", " Main layout ")
        }
    }

    fun addmovi(moviAddServiceModel: MoviAddServiceModel) {
        val retrofitClient = RetrofitInitializer.getRetrofitInstance()
        val endpoint = retrofitClient.create(Service.MoviAddService::class.java)
        val call = endpoint.addmovi(
            token.access_token,
            "application/json",
            moviAddServiceModel
        )
        call?.enqueue(object : Callback<MoviAddServiceSerializedModel?> {
            override fun onResponse(
                call: Call<MoviAddServiceSerializedModel?>,
                response: Response<MoviAddServiceSerializedModel?>
            ) {

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

            override fun onFailure(call: Call<MoviAddServiceSerializedModel?>, t: Throwable) {
                Toast.makeText(
                    getApplicationContext(),
                    "Erro na chamada ao servidor",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }
}