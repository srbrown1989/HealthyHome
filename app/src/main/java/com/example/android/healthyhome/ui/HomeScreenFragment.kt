package com.example.android.healthyhome.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android.healthyhome.R
import com.example.android.healthyhome.databinding.FragmentHomeScreenBinding

class HomeScreenFragment : Fragment() {

    private var _binding: FragmentHomeScreenBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, bundle: Bundle?
    ): View {

        _binding = FragmentHomeScreenBinding
            .inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClickListener()

    }

    private fun initClickListener() {

        binding.btnSearchHome.setOnClickListener {
            navigateToServices()
        }
    }

    private fun navigateToServices() {

        findNavController().navigate(R.id.action_loginFragment_to_homeScreenFragment)

    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}