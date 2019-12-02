package com.example.calcirkotlin.RestAPI

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class RetrofitInitializer {

    companion object {

        /** Retorna uma Instância do Client Retrofit para Requisições
         * @param path Caminho Principal da API
         *
         *
         */
        fun getRetrofitInstance() : Retrofit {
            val interceptor = HttpLoggingInterceptor()
            interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
            val client =
                OkHttpClient.Builder().addInterceptor(interceptor).build()

            var retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("http://47.74.246.126/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit
        }
    }
}