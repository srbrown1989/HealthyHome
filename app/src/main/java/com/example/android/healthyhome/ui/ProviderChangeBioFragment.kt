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
import com.example.android.healthyhome.R
import com.example.android.healthyhome.database.ProviderSignUpResponse
import com.example.android.healthyhome.database.util.Common
import com.example.android.healthyhome.database.util.IMyAPI
import com.example.android.healthyhome.databinding.ActivityProviderChangeBioFragmentBinding
import com.example.android.healthyhome.databinding.FragmentProviderSignUpBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProviderChangeBioFragment : Fragment() {
    private lateinit var binding: ActivityProviderChangeBioFragmentBinding
    private lateinit var navController: NavController
    private lateinit var mService: IMyAPI
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<ActivityProviderChangeBioFragmentBinding>(
            inflater, R.layout.activity_provider_change_bio_fragment, container, false
        )

        binding.popupConfirmButton.setOnClickListener {

          //  it.findNavController().navigate(ProviderChangeBioFragmentDirections.actionProviderChangeBioFragmentToProviderHomeFragment())

        }

        //replacing hints with current stored data

        binding.changeBioBox.hint = Common.currentProvider.bio
        binding.changePhoneNumberBox.hint = Common.currentProvider.contact
        binding.changeEmailBox.hint = Common.currentProvider.email
        binding.changePriceBox.hint = Common.currentProvider.price
        binding.changeAddressBox.hint = Common.currentProvider.address
        binding.changePostcodeBox.hint = Common.currentProvider.postcode
        binding.changeRatingBox.numStars = Common.currentProvider.rating


        mService = Common.getAPI()

        return binding.root

    }

    private fun updateProvider() {

        //var uid = Common.currentUser.uid

        //update provider bio

        var newBio = binding.changeBioBox.text.toString()
        var newContact = binding.changePhoneNumberBox.text.toString()
        var newEmail = binding.changeEmailBox.text.toString()
        var newPrice = binding.changePriceBox.text.toString()
        var newAddress = binding.changeAddressBox.text.toString()
        var newPostcode = binding.changePostcodeBox.text.toString()
        var newRating = binding.changeRatingBox.rating.toString()

        mService.updateProvider(
            45,
            "test lane",
            "CT23UB",
            "01892592375",
            "cleaner@test.com",
            "testing",
            4,
            "£16"
        )
            .enqueue(object : Callback<ProviderSignUpResponse> {
                override fun onResponse(
                    call: Call<ProviderSignUpResponse>,
                    response: Response<ProviderSignUpResponse>
                ) {
                    Common.currentProvider = response.body()!!.provider
                    Toast.makeText(
                        activity?.applicationContext,
                        "Successfully updated as " + response.body()!!.provider!!.name,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onFailure(call: Call<ProviderSignUpResponse>, t: Throwable) {
                    Toast.makeText(activity?.applicationContext, t.message, Toast.LENGTH_SHORT)
                        .show()
                }
            })
    }
}


