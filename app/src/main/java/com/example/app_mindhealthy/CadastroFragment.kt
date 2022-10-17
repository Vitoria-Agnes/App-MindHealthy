package com.example.app_mindhealthy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.app_mindhealthy.databinding.FragmentCadastro2Binding
import com.example.app_mindhealthy.model.Usuario

class CadastroFragment : Fragment() {

    private lateinit var binding: FragmentCadastro2Binding
    private val mainViewModel: MainViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCadastro2Binding.inflate(layoutInflater, container, false)

        binding.bCadastrar.setOnClickListener {
            inserirNoBanco()
        }

        return binding.root
    }
    private fun validarCampos(nome: String, sobrenome: String, idade: String, email: String, foto: String, senha: String): Boolean{
        return !(
                (nome == "" || nome.length < 3 || nome.length > 60) ||
                        (sobrenome == "" || sobrenome.length < 3 || sobrenome.length > 60) ||
                        (idade == "" || idade.length < 0 || idade.length > 3) ||
                        (email == "" || email.length < 3 || email.length > 60) ||
                        (foto == "" || foto.length < 3 || foto.length > 60) ||
                        (senha == "" || nome.length < 3 || nome.length > 60)
                )
    }
    private fun inserirNoBanco(){
        val nome = binding.etnome.text.toString()
        val sobrenome = binding.etsobrenome.text.toString()
        val idade = binding.etIdade.text.toString()
        val email = binding.etemail.text.toString()
        val foto = binding.etFoto.text.toString()
        val senha = binding.etSenha.text.toString()

        if (validarCampos(nome, sobrenome, idade, email, foto, senha)){
            val usuario = Usuario(0, nome, sobrenome, idade.toInt(), email,foto,senha)
            mainViewModel.cadUsuario(usuario)
            Toast.makeText(context, "Usuário Cadastrado!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_cadastroFragment2_to_listFragment)
        }else{
            Toast.makeText(context, "Verifique os Campos!", Toast.LENGTH_SHORT).show()
        }
    }

}