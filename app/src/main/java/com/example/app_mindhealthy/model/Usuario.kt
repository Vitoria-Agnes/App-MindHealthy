package com.example.app_mindhealthy.model

data class Usuario (
    var id: Long,
    var nome: String,
    var sobrenome: String,
    var idade: Int,
    var email: String,
    var foto: String,
    var senha: String
    //var postagem: List<Postagem>?
) {
}