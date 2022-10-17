package com.example.app_mindhealthy

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_mindhealthy.model.Postagem
import com.example.app_mindhealthy.model.Temas
import com.example.app_mindhealthy.api.Repository
import com.example.app_mindhealthy.model.Usuario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
    ):ViewModel () {

    var postagemSeleciona: Postagem? = null

    private val _myTemasResponse =
        MutableLiveData<Response<List<Temas>>>()

    val myTemasResponse: LiveData<Response<List<Temas>>> =
        _myTemasResponse

    private val _myPostagemResponse =
        MutableLiveData<Response<List<Postagem>>>()

    val mypostagemResponse: LiveData<Response<List<Postagem>>> =
        _myPostagemResponse


    init {
        //listTemas()
    }

    fun listTemas() {
        viewModelScope.launch {
            try {
                val response = repository.listTemas()
                _myTemasResponse.value = response
            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun addPostagem(postagem: Postagem) {
        viewModelScope.launch {
            try {
                repository.addPostagem(postagem)
            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun listPostagem() {
        viewModelScope.launch {
            try {
                val response = repository.listPostagem()
                _myPostagemResponse.value = response
            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun updatePostagem(postagem: Postagem){
        viewModelScope.launch {
            try {
                repository.updatePostagem(postagem)
                listPostagem()

            }catch (e: Exception){
                Log.d("Erro", e.message.toString())

            }
        }
    }

    fun deletePostagem(id: Long){
        viewModelScope.launch{
            try {
                repository.deletePostagem(id)
                listPostagem()

            }catch (e: Exception){
                Log.d("Erro", e.message.toString())
            }
        }
    }
    fun cadUsuario(usuario: Usuario){
        viewModelScope.launch {
            try {
                repository.cadUsuario(usuario)
            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }
    }
}
