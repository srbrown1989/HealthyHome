package com.example.android.healthyhome.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.android.healthyhome.R
import com.example.android.healthyhome.database.UserX
import com.example.android.healthyhome.database.util.Common
import com.example.android.healthyhome.databinding.FragmentServicesBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ServicesFragment : Fragment() {
    private lateinit var currentUser: UserX
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentServicesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_services, container, false)

        currentUser= Common.currentUser

        Toast.makeText(context, "Logged in as ${currentUser.email}",Toast.LENGTH_LONG).show()

        binding.icServicesCleaner.setOnClickListener {
            //Do this manually for each button ig
            navigateToProviders("cleaning")
        }

        binding.servicesFragmentText.text = currentUser.uid.toString()

        return binding.root
    }

    private fun navigateToProviders(service: String){
        navController.navigate(ServicesFragmentDirections.actionServicesFragmentToProviderListFragment2(service))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
    }


}