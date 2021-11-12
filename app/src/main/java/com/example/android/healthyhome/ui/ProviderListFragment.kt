package com.example.android.healthyhome.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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

    private lateinit var fAuth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding: FragmentProviderListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_provider_list, container, false)
        // Inflate the layout for this fragment
        fAuth = Firebase.auth

        val providerList = generateDummyList(30)

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.adapter = ProviderListAdapter(providerList)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.setHasFixedSize(true)
        return binding.root
    }
    private fun generateDummyList(size: Int): List<Provider> {

        val list = ArrayList<Provider>()

        for(i in 0 until size) {

            val item = Provider("UID$i", "Provider Name$i", "Contact$i", "Email$i", "Bio$i", "Service$i",
            "CreditCard$i", 5, 5)
            list += item
        }
        return list
    }



}