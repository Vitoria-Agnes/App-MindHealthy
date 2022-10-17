package com.example.app_mindhealthy

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.app_mindhealthy.databinding.FragmentPostBinding
import com.example.app_mindhealthy.model.Postagem
import com.example.app_mindhealthy.model.Temas

class PostFragment : Fragment() {

    private  lateinit var binding: FragmentPostBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private var temaSelecionado = 0L
    private var postagemSelecionada: Postagem? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPostBinding.inflate(layoutInflater, container, false)

        carregarDados()

        mainViewModel.listTemas()


        mainViewModel.myTemasResponse.observe(viewLifecycleOwner){
            response -> Log.d("Requisicao", response.body().toString())
            spinnerTemas(response.body())
        }

        binding.buttonPublicar.setOnClickListener{
            inserirNoBanco()
        }

        return binding.root
    }

    private fun spinnerTemas(listTemas: List<Temas>?){
        if (listTemas != null){
            binding.spinnerTemas.adapter =
                ArrayAdapter(
                    requireContext(),
                    androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                    listTemas
                )

            binding.spinnerTemas.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        val selected = binding.spinnerTemas.selectedItem as Temas

                        temaSelecionado = selected.id
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                }

        }

    }

    private fun validarCampos(titulo: String, postagem: String, linkImage: String):Boolean{


        return !(
                (titulo == "" || titulo.length < 3 || titulo.length > 200) ||
                        (postagem == "" || postagem.length < 5 || postagem.length > 200) ||
                        (linkImage == "" || linkImage.length < 3 || linkImage.length > 1000)
                )
    }

    private fun inserirNoBanco() {
        val titulo = binding.editTextTitulo.text.toString()
        val postagem = binding.editTextPost.text.toString()
        val linkImage = binding.editTextLinkImage.text.toString()
        val tema = Temas(temaSelecionado, null, null)

        if (validarCampos(titulo, postagem, linkImage)) {
            val salvar: String
            if (postagemSelecionada != null){
                salvar = "Postagem Atualizada"
                val post = Postagem( postagemSelecionada?.id!!, titulo, postagem, linkImage, tema)
                mainViewModel.updatePostagem(post)
            }else{
                salvar = "Postagem Criada"
                val post = Postagem(0, titulo, postagem, linkImage, tema)
                mainViewModel.addPostagem(post)
            }

            Toast.makeText(context, salvar, Toast.LENGTH_SHORT).show()
            //findNavController().navigate(R.id.action_postFragment_to_listFragment)
//            if (findNavController().currentDestination == findNavController().findDestination(R.id.postFragment)){
//
//            }


        } else {
            Toast.makeText(context, "Verifique os Campos!", Toast.LENGTH_SHORT).show()

        }
    }


    private fun carregarDados(){
      postagemSelecionada = mainViewModel.postagemSeleciona
        if (postagemSelecionada != null){
            binding.editTextTitulo.setText(postagemSelecionada?.titulo)
            binding.editTextPost.setText(postagemSelecionada?.descricao)
            binding.editTextLinkImage.setText(postagemSelecionada?.imagem)

        }

    }

}