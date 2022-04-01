package com.example.android.healthyhome.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.android.healthyhome.R
import com.example.android.healthyhome.database.util.Common
import com.example.android.healthyhome.database.util.IMyAPI
import com.example.android.healthyhome.databinding.FragmentProviderReviewsBinding
import com.example.android.healthyhome.databinding.FragmentProviderSignUpBinding

class ProviderReviewsFragment : Fragment() {

    private lateinit var binding: FragmentProviderReviewsBinding
    private lateinit var navController: NavController

    private lateinit var mService : IMyAPI




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentProviderReviewsBinding>(
            inflater, R.layout.fragment_provider_reviews, container, false
        )

        mService = Common.getAPI()

        navController = findNavController()


        return binding.root
    }
}