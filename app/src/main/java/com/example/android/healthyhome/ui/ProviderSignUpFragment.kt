package com.example.android.healthyhome.ui

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.android.healthyhome.R
import com.example.android.healthyhome.database.LoginResponse
import com.example.android.healthyhome.database.ProviderSignUpResponse
import com.example.android.healthyhome.database.util.Common
import com.example.android.healthyhome.database.util.IMyAPI
import com.example.android.healthyhome.databinding.FragmentLoginBinding
import com.example.android.healthyhome.databinding.FragmentProviderSignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProviderSignUpFragment : Fragment() {

    private lateinit var binding: FragmentProviderSignUpBinding
    private lateinit var navController: NavController
    private lateinit var mService : IMyAPI




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentProviderSignUpBinding>(
            inflater, R.layout.fragment_provider_sign_up, container, false)


    mService = Common.getAPI()
    navController = findNavController()
      //TODO:: NEED TO ASK FOR AVAILABILTIY IN SOME WAY

    binding.btnProviderSubmit.setOnClickListener { 1
        signUp()
    }





        return binding.root
    }

    private fun signUp() {
        val uid = Common.currentUser.uid
        val address = binding.etAddressFirst.text.toString() + binding.etAddressSecond.text.toString()
        val postCode = binding.etAddressPostcode.text.toString()
        val companyName = binding.etProviderNameSignup.text.toString()
        val companyEmail = binding.etProviderEmailSignup.text.toString()
        val contact = binding.etTelephone.text.toString()
        val bio = binding.etProviderBio.text.toString()
        val service = binding.spinner.selectedItem.toString()
        val extras = "Wallpapering"

        val price = binding.etPrice.text.toString()


        mService.registerProvider(uid, address, postCode, companyName, contact, companyEmail, bio, service, extras, price).enqueue(object : Callback<ProviderSignUpResponse>{
            override fun onResponse(
                call: Call<ProviderSignUpResponse>,
                response: Response<ProviderSignUpResponse>
            ) {
                val result : ProviderSignUpResponse? = response.body()
                if (result?.error!!){
                    Toast.makeText(activity?.applicationContext,result.error_msg,Toast.LENGTH_SHORT).show()

                } else {
                    Common.currentProvider = response.body()!!.provider
                    navController.navigate(ProviderSignUpFragmentDirections.actionProviderSignUpFragment2ToProviderHomeFragment(Common.currentProvider.pid.toString()))

                }
            }

            override fun onFailure(call: Call<ProviderSignUpResponse>, t: Throwable) {

               //Toast.makeText(activity?.applicationContext,"Error with submitting provider sign up data",Toast.LENGTH_SHORT).show()
            }


        })


    }

}