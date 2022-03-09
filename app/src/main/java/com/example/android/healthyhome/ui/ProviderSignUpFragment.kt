package com.example.android.healthyhome.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.healthyhome.R
import com.example.android.healthyhome.database.LoginResponse
import com.example.android.healthyhome.database.Provider
import com.example.android.healthyhome.database.ProviderSignUpResponse
import com.example.android.healthyhome.database.util.Common
import com.example.android.healthyhome.database.util.IMyAPI
import com.example.android.healthyhome.databinding.FragmentProviderSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProviderSignUpFragment: Fragment() {

    private lateinit var binding: FragmentProviderSignUpBinding

    private lateinit var mService : IMyAPI

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentProviderSignUpBinding>(
            inflater, R.layout.fragment_provider_sign_up, container, false
        )

        binding.finishButton.setOnClickListener {
            addProvider()
           // it.findNavController().navigate(ProviderSignUpFragmentDirections.actionProviderSignUpFragmentToProviderHomeFragment())

        }

        mService = Common.getAPI()


        return binding.root

    }

    private fun addProvider() {

        //provider.provider_id = database.push().key

        //var uid = Common.currentUser.uid

        //Id of current logged in user.

       var providerName = binding.providerName.text.toString()
        var bio = binding.bioInput.text.toString()
        var phoneNumber = binding.phoneNumber.text.toString()
        var serviceType = binding.spinnerServiceType.selectedItem.toString()
        var providerEmail = binding.providerEmail.text.toString()
        var price = binding.priceInput.text.toString()
        var address = binding.companyAddress.text.toString()
        var postcode = binding.companyPostcode.text.toString()
        var rating = binding.ratingStar.rating.toString()
        var offers = binding.radioYes.isChecked.toString()

        mService.registerProvider(45,"test avenue","Ct27UB", "test","01892592345", "shauns cleaners", "test bio", "cleaning","[carpet]",4, "£15")
            .enqueue(object : Callback<ProviderSignUpResponse>{
                override fun onResponse(
                    call: Call<ProviderSignUpResponse>,
                    response: Response<ProviderSignUpResponse>
                ) {
                    Common.currentProvider = response.body()!!.provider
                    Toast.makeText(activity?.applicationContext,"Successfully signed up as " + response.body()!!.provider!!.name,Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<ProviderSignUpResponse>, t: Throwable) {
                    Toast.makeText(activity?.applicationContext,t.message,Toast.LENGTH_SHORT).show()
                }

            })


    }

}