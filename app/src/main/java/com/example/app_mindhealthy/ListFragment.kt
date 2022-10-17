package com.example.app_mindhealthy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_mindhealthy.adapter.PostagemAdapter
import com.example.app_mindhealthy.adapter.TaskClickListener
import com.example.app_mindhealthy.databinding.FragmentListBinding
import com.example.app_mindhealthy.model.Postagem

class ListFragment : Fragment(), TaskClickListener {

    private lateinit var binding: FragmentListBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentListBinding.inflate(layoutInflater, container, false)

        mainViewModel.listPostagem()

        val adapter = PostagemAdapter(this, mainViewModel, requireContext())
        binding.recyclerPostagem.layoutManager = LinearLayoutManager(context)
        binding.recyclerPostagem.adapter = adapter
        binding.recyclerPostagem.setHasFixedSize(true)


        mainViewModel.mypostagemResponse.observe(viewLifecycleOwner) { response ->
            if (response.body() != null) {
                adapter.setList(response.body()!!)
            }
        }
        return binding.root
    }

    override fun onTaskClickListener(postagem: Postagem) {
        mainViewModel.postagemSeleciona = postagem
        findNavController().navigate(R.id.action_listFragment_to_postFragment)
    }
}