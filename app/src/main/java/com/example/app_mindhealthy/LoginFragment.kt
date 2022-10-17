package com.example.app_mindhealthy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.app_mindhealthy.databinding.FragmentCadastro2Binding
import com.example.app_mindhealthy.databinding.FragmentLogin2Binding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLogin2Binding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLogin2Binding.inflate(layoutInflater, container, false)

        binding.bLogin.setOnClickListener {
            findNavController().navigate(R.id.listFragment)
        }

        binding.bCadastrarSe.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment2_to_cadastroFragment2)
        }

        return binding.root
    }



}