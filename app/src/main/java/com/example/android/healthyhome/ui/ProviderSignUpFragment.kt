package com.example.android.healthyhome.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.healthyhome.R
import com.example.android.healthyhome.database.Provider
import com.example.android.healthyhome.databinding.FragmentProviderSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ProviderSignUpFragment: Fragment() {

    private lateinit var binding: FragmentProviderSignUpBinding
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentProviderSignUpBinding>(
            inflater, R.layout.fragment_provider_sign_up, container, false
        )
        database = FirebaseDatabase.getInstance().reference.child("Provider")

        binding.finishButton.setOnClickListener {
            addProvider()
            it.findNavController().navigate(ProviderSignUpFragmentDirections.actionProviderSignUpFragmentToProviderHomeFragment())

        }

        return binding.root

    }

    private fun addProvider() {
        var provider = Provider()
        //provider.provider_id = database.push().key


        //Id of current logged in user.
        provider.uid = FirebaseAuth.getInstance().uid

        provider.providerName = binding.providerName.text.toString()
        provider.bio = binding.bioInput.text.toString()
        provider.phoneNumber = binding.phoneNumber.text.toString()
        provider.serviceType = binding.spinnerServiceType.selectedItem.toString()
        provider.providerEmail = binding.providerEmail.text.toString()
        provider.price = Integer.parseInt(binding.priceInput.text.toString())



        database.child(provider.uid!!).setValue(provider)
    }

}