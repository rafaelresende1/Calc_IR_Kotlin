package com.example.calcirkotlin.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.view.get
import com.example.calcirkotlin.Model.LoginModel.LoginModel
import com.example.calcirkotlin.Model.LoginModel.TokenGetModel
import com.example.calcirkotlin.Model.LoginModel.TokenModel
import com.example.calcirkotlin.R
import com.example.calcirkotlin.RestAPI.RetrofitInitializer
import com.example.calcirkotlin.RestAPI.Service
import com.example.calcirkotlin.RestAPI.Service.LoginService
import com.example.calcirkotlin.ui.Principal.App_Logado
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var editText_user: TextInputLayout? = null
    var editText_senha: TextInputLayout? = null
    var button_logar: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText_user = findViewById<TextInputLayout?>(R.id.editText_user)
        editText_senha = findViewById<TextInputLayout?>(R.id.editText_senha)
        button_logar = findViewById<Button?>(R.id.button_logar)
        button_logar?.setOnClickListener(View.OnClickListener {
            button_logar!!.isClickable = false
            getData(editText_user?.getEditText()?.getText().toString(),
                    editText_senha?.getEditText()?.getText().toString())
        })

    }
    private fun getData(username: String, password: String) {
        val retrofitClient = RetrofitInitializer.getRetrofitInstance()
        val loginModel = LoginModel(username, password)

        val endpoint = retrofitClient.create(LoginService::class.java)

        val callback = endpoint.validauser(loginModel, "application/json")

        callback?.enqueue(object : Callback<TokenModel?> {
            override fun onFailure(call: Call<TokenModel?>?, t: Throwable?) {
                Toast.makeText(baseContext, "Erro ao logar", Toast.LENGTH_SHORT).show()
                button_logar?.setEnabled(true)

            }

            override fun onResponse(call: Call<TokenModel?>?, response: Response<TokenModel?>?) {
                if (response != null) {
                    if (response.isSuccessful()) {
                        response.body()
                        //verifica aqui se o corpo da resposta não é nulo
                        if (response != null) {
                            button_logar?.setEnabled(true)
                            TokenGetModel.setAccess_token(response.body()?.accessToken)
                            TokenGetModel.setRefresh_token(response.body()?.refreshToken)
                            TokenGetModel.setUser_id(response.body()?.userId)
                            Toast.makeText(applicationContext, "Você está logado", Toast.LENGTH_SHORT).show()
                            val intentMain = Intent(this@MainActivity,
                                App_Logado::class.java)
                            this@MainActivity.startActivity(intentMain)
                            Log.i("Content ", " Main layout ")
                        } else {
                            Toast.makeText(applicationContext, "Resposta nula do servidor", Toast.LENGTH_SHORT).show()
                            button_logar?.setEnabled(true)
                        }
                    } else {
                        button_logar?.setEnabled(true)
                        Toast.makeText(applicationContext, "Resposta não foi sucesso", Toast.LENGTH_SHORT).show()
                        // segura os erros de requisição
                        val errorBody = response.errorBody()
                    }
                }

            }
        })

    }
}
