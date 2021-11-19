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
import com.example.android.healthyhome.databinding.FragmentServicesBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ServicesFragment : Fragment() {

    private lateinit var fAuth : FirebaseAuth
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentServicesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_services, container, false)

        fAuth = Firebase.auth

        if(fAuth.currentUser?.uid != null) {
            Toast.makeText(context, "Logged in as ${fAuth.currentUser!!.email}",Toast.LENGTH_LONG).show()

        }
        binding.icServicesCleaner.setOnClickListener {
            navController.navigate(ServicesFragmentDirections.actionServicesFragmentToProviderListFragment2())
        }

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
    }


}