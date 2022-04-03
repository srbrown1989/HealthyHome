package com.example.android.healthyhome.ui

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.example.android.healthyhome.R
import com.example.android.healthyhome.database.util.Common
import com.example.android.healthyhome.database.util.IMyAPI
import com.example.android.healthyhome.databinding.FragmentLoginBinding
import com.example.android.healthyhome.databinding.FragmentProviderSignUpBinding

class ProviderSignUpFragment : Fragment() {

    private lateinit var binding: FragmentProviderSignUpBinding
    private lateinit var navController: NavController

    private lateinit var mService : IMyAPI
    private lateinit var spinner: Spinner



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentProviderSignUpBinding>(
            inflater, R.layout.fragment_provider_sign_up, container, false)

        //spinner = binding.spProviderServices






        mService = Common.getAPI()



        return binding.root
    }

}