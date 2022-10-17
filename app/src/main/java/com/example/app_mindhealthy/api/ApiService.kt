package com.example.app_mindhealthy.api

import com.example.app_mindhealthy.model.Postagem
import com.example.app_mindhealthy.model.Temas
import com.example.app_mindhealthy.model.Usuario
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("temas")
    suspend fun listTemas(): Response<List<Temas>>

    @POST("postagem")
    suspend fun addPostagem (
        @Body postagem: Postagem
    ):Response<Postagem>

    @GET("postagem")
    suspend fun  listPostagem(): Response<List<Postagem>>

    @PUT("postagem")
    suspend fun updatePostagem (
        @Body postagem: Postagem

    ): Response<Postagem>

    @DELETE("postagem/{id}")
    suspend fun deletePostagem(
        @Path("id") id: Long
    ): Response<Postagem>

    @POST("usuario")
    suspend fun cadUsuario(
        @Body usuario: Usuario
    ):Response<Usuario>
}