package com.example.android.healthyhome.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.android.healthyhome.R
import com.example.android.healthyhome.database.Provider
import com.example.android.healthyhome.databinding.FragmentProviderSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProviderFragment: Fragment() {

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

        binding.providerAddButton.setOnClickListener {
            var  provider = Provider()
            provider.provider_id = database.push().key
//            addProvider()


            provider.uid = FirebaseAuth.getInstance().uid ?: "uidDidntWork"
            provider.name = binding.providerNameEditText.text.toString()
            provider.bio = binding.providerContactEditText.text.toString()
            provider.contactNumber = binding.providerBioEditText.text.toString()
            provider.service = binding.providerServiceEditText.text.toString()

            database.child(provider.provider_id!!).setValue(provider)

        }

        return binding.root

    }

//    private fun addProvider() {
//
//
//        var provider = Provider()
//
//        provider.uid = FirebaseAuth.getInstance().uid ?: "uidDidntWork"
//        provider.name = binding.providerNameEditText.text.toString()
//        provider.bio = binding.providerContactEditText.text.toString()
//        provider.contactNumber = binding.providerBioEditText.text.toString()
//        provider.service = binding.providerServiceEditText.text.toString()
//
//        database.push().setValue(provider)
//
//    }
}