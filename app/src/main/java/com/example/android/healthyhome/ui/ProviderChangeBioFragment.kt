package com.example.android.healthyhome.ui

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.android.healthyhome.R
import com.example.android.healthyhome.database.Provider
import com.example.android.healthyhome.database.ProviderSignUpResponse
import com.example.android.healthyhome.database.util.Common
import com.example.android.healthyhome.database.util.IMyAPI
import com.example.android.healthyhome.databinding.FragmentProviderChangeBioBinding
import com.example.android.healthyhome.databinding.FragmentProviderSignUpBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProviderChangeBioFragment : Fragment() {

    private lateinit var binding: FragmentProviderChangeBioBinding
    private lateinit var navController: NavController
    private lateinit var mService: IMyAPI

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentProviderChangeBioBinding>(
            inflater,R.layout.fragment_provider_change_bio , container, false
        )

        navController = findNavController()

        updateFields();


        return binding.root

    }

    private fun updateFields() {
        val provider : Provider = Common.currentProvider
        binding.cbNameTv.text = provider.companyName
        binding.cbBioTv.text = provider.Bio
        binding.cbAddressTv.text = provider.address
        binding.cbPostcodeTv.text = provider.postcode
        binding.cbContactTv.text = provider.contact
        binding.cbEmailTv.text = provider.companyEmail
        binding.cbServiceTv.text = provider.service
        binding.cbPriceTv.text = provider.price

    }


}


