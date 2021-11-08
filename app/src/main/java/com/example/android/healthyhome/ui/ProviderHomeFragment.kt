package com.example.android.healthyhome.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.android.healthyhome.R
import com.example.android.healthyhome.databinding.FragmentProviderHomeBinding
import com.google.firebase.database.DataSnapshot

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class ProviderHomeFragment : Fragment() {

    private lateinit var binding : FragmentProviderHomeBinding
    private lateinit var database : DatabaseReference
    private lateinit var dbProvider : DataSnapshot


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
// Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentProviderHomeBinding>(
            inflater, R.layout.fragment_provider_home, container, false
        )
       database = FirebaseDatabase.getInstance().reference.child("Provider")

        fillProviderInfo()


        return binding.root    }

    private fun fillProviderInfo() {
        val provider = database.child("Provider").get()
        Log.i("Test", "I am here")
    }


}