package com.example.calcirkotlin.RestAPI

import com.example.calcirkotlin.Model.ListaMediaModel.DeletaMediaModel
import com.example.calcirkotlin.Model.ListaMediaModel.ListaMediaModel
import com.example.calcirkotlin.Model.ListaMoviModel.DelModel
import com.example.calcirkotlin.Model.ListaMoviModel.DeleteMoviModel
import com.example.calcirkotlin.Model.ListaMoviModel.MoviAddServiceModel
import com.example.calcirkotlin.Model.ListaMoviModel.MoviAddServiceSerializedModel
import com.example.calcirkotlin.Model.LoginModel.LoginModel
import com.example.calcirkotlin.Model.LoginModel.TokenModel
import com.example.calcirkotlin.Model.ListaMoviModel.ListMoviModel
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

    interface MoviAcoesDellService {
        @HTTP(method = "DELETE", path = "movimento", hasBody = true)
        fun excluimovi(
            @Header("Authorization") token: String?,
            @Header("Content-Type") content_type: String?,
            @Body deleteMoviModel: DeleteMoviModel
        ): Call<DelModel?>?
    }
    interface MoviAddService {
        @PUT("movimento")
        fun addmovi(
            @Header("Authorization") token: String?,
            @Header("Content-Type") content_type: String?,
            @Body moviAddServiceModel: MoviAddServiceModel
        ): Call<MoviAddServiceSerializedModel?>?
    }
    interface ListaMedia {
        @GET("media")
        fun listamedia(
            @Header("Authorization") token: String?,
            @Header("Content-Type") content_type: String?
        ): Call<ListaMediaModel?>?
    }
    interface MoviMediaDellService {
        @HTTP(method = "DELETE", path = "media", hasBody = true)
        fun excluimedia(
            @Header("Authorization") token: String?,
            @Header("Content-Type") content_type: String?,
            @Body deletaMediaModel: DeletaMediaModel
        ): Call<DelModel?>?
    }
}