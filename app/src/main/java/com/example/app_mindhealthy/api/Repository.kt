package com.example.app_mindhealthy.api

import com.example.app_mindhealthy.model.Postagem
import com.example.app_mindhealthy.model.Temas
import com.example.app_mindhealthy.model.Usuario
import retrofit2.Response

class Repository {
        suspend fun listTemas(): Response<List<Temas>> {
            return RetrofitInstance.api.listTemas()
        }
        suspend fun addPostagem(postagem: Postagem): Response<Postagem> {
            return RetrofitInstance.api.addPostagem(postagem)
        }

        suspend fun listPostagem(): Response<List<Postagem>> {
            return RetrofitInstance.api.listPostagem()
        }

       suspend fun updatePostagem(postagem: Postagem): Response<Postagem>{
        return RetrofitInstance.api.updatePostagem(postagem)
       }

       suspend fun deletePostagem(id: Long): Response<Postagem>{
           return RetrofitInstance.api.deletePostagem(id)
       }
    suspend fun cadUsuario(usuario: Usuario): Response<Usuario> {
        return RetrofitInstance.api.cadUsuario(usuario)
    }

    }
