package com.example.calcirkotlin.RestAPI

import com.example.calcirkotlin.Model.ListaMoviModel.DelMoviModel
import com.example.calcirkotlin.Model.ListaMoviModel.DeleteMoviModel
import com.example.calcirkotlin.Model.ListaMoviModel.MoviAddServiceModel
import com.example.calcirkotlin.Model.LoginModel.LoginModel
import com.example.calcirkotlin.Model.LoginModel.TokenModel
import com.example.calcirkotlin.model.ListaMoviModel.ListMoviModel
import retrofit2.Call
import retrofit2.http.*

class Service {
    interface LoginService {
        @POST("login")
        fun validauser(
            @Body userModel: LoginModel,
            @Header("Content-Type") content_type: String
        ): Call<TokenModel?>?
    }

    interface ListaAcoesService {
        @GET("movimento")
        fun listamovi(
            @Header("Authorization") token: String?,
            @Header("Content-Type") content_type: String?
        ): Call<ListMoviModel?>?
    }

    interface MoviAcoesService {
        @HTTP(method = "DELETE", path = "movimento", hasBody = true)
        fun excluimovi(
            @Header("Authorization") token: String?,
            @Header("Content-Type") content_type: String?,
            @Body deleteMoviModel: DeleteMoviModel
        ): Call<DelMoviModel?>?

        @POST
        fun addmovi(
            @Header("Authorization") token: String?,
            @Header("Content-Type") content_type: String?,
            @Body moviAddServiceModel: MoviAddServiceModel
        ): Call<MoviAddServiceModel?>?
    }
}