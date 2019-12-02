package com.example.calcirkotlin.ui.Movi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.calcirkotlin.Model.ListaMoviModel.MoviAddModel
import com.example.calcirkotlin.R
import com.example.calcirkotlin.ui.Principal.App_Logado

class MoviAddActivity : AppCompatActivity() {
    private var text_acoes: EditText? = null
    private var text_valor: EditText? = null
    private var text_data: EditText? = null
    private var text_cv: EditText? = null
    private var text_quatidade: EditText? = null
    private var button_movi: Button? = null
    private lateinit var acao: MoviAddModel

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

        text_quatidade?.setText(acao.quantidade.toString())

        text_acoes?.setText(acao.acoes)
        text_valor?.setText(acao.Double.toString())
        text_data?.setText(acao.data_op)
        text_cv?.setText(acao.compra_venda)

        button_movi?.setOnClickListener(View.OnClickListener {
            Toast.makeText(applicationContext, "Clicado", Toast.LENGTH_SHORT).show()
            val intentMain = Intent(
                this@MoviAddActivity,
                App_Logado::class.java
            )
            this@MoviAddActivity.startActivity(intentMain)
            Log.i("Content ", " Main layout ")
        })
    }
}