package com.example.android.healthyhome.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.android.healthyhome.R
import com.example.android.healthyhome.databinding.FragmentFilterServicesBinding
import com.example.android.healthyhome.databinding.FragmentProviderHomeBinding
import com.google.firebase.database.FirebaseDatabase

class FilterServicesFragment : Fragment() {

    private lateinit var binding : FragmentFilterServicesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
     // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentFilterServicesBinding>(inflater, R.layout.fragment_filter_services, container, false)
            return binding.root
        }

}