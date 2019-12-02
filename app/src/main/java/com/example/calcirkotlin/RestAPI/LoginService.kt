package com.example.calcirkotlin.RestAPI

import com.example.calc_ir_android.model.ListaMoviModel.ListMoviModel
import com.example.calcirkotlin.Model.ListaMoviModel.DelMoviModel
import com.example.calcirkotlin.Model.LoginModel.DeleteMoviModel
import com.example.calcirkotlin.Model.LoginModel.LoginModel
import com.example.calcirkotlin.Model.LoginModel.TokenModel
import retrofit2.Call
import retrofit2.http.*

class Service {
    interface LoginService {
        @POST("login")
        open fun validauser(
            @Body userModel: LoginModel,
            @Header("Content-Type") content_type: String
        ): Call<TokenModel?>?
    }

    interface ListaAcoesService {
        @GET("movimento")
        open fun listamovi(
            @Header("Authorization") token: String?,
            @Header("Content-Type") content_type: String?
        ): Call<ListMoviModel?>?
    }

    interface ExcluirAcoesService {
        @HTTP(method = "DELETE", path = "movimento", hasBody = true)
        open fun excluimovi(
            @Header("Authorization") token: String?,
            @Header("Content-Type") content_type: String?,
            @Body deleteMoviModel: DeleteMoviModel
        ): Call<DelMoviModel?>?
    }
}