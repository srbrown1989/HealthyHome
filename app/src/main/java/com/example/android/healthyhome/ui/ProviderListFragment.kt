package com.example.android.healthyhome.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.healthyhome.R
import com.example.android.healthyhome.database.Provider
import com.example.android.healthyhome.database.util.ProviderListAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.example.android.healthyhome.databinding.FragmentProviderListBinding


class ProviderListFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding: FragmentProviderListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_provider_list, container, false)
        // Inflate the layout for this fragment


        val recyclerView: RecyclerView = binding.recyclerView
        //recyclerView.adapter = ProviderListAdapter(providerList)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.setHasFixedSize(true)

        binding.tempButton.setOnClickListener {
            it.findNavController().navigate( ProviderListFragmentDirections.actionProviderListFragmentToChosenProviderFragment())
        }

        return binding.root
    }




}