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

          it.findNavController().navigate(ProviderChangeBioFragmentDirections.actionProviderChangeBioFragmentToProviderHomeFragment())

        }

        //replacing hints with current stored data

        binding.currentBioBox.hint = Common.currentProvider.bio
        binding.currentPhoneNumberBox.hint = Common.currentProvider.contact
        binding.currentEmailBox.hint = Common.currentProvider.email
        binding.currentPriceBox.hint = Common.currentProvider.price
        binding.currentAddressBox.hint = Common.currentProvider.address
        binding.currentPostcodeBox.hint = Common.currentProvider.postcode
        binding.currentRatingBox.numStars = Common.currentProvider.rating


        mService = Common.getAPI()

        // Change Info
        binding.changeDescriptionText.setOnClickListener {
            binding.currentBioBox.visibility = View.INVISIBLE
            binding.changeDescriptionText.visibility = View.INVISIBLE
            binding.changeBioBox.visibility = View.VISIBLE
        }
        binding.changePhoneNumberText.setOnClickListener {
            binding.currentPhoneNumberBox.visibility = View.INVISIBLE
            binding.changePhoneNumberText.visibility = View.INVISIBLE
            binding.changePhoneNumberBox.visibility = View.VISIBLE
        }
        binding.changeEmailText.setOnClickListener {
            binding.currentEmailBox.visibility = View.INVISIBLE
            binding.changeEmailText.visibility = View.INVISIBLE
            binding.changeEmailBox.visibility = View.VISIBLE
        }
        binding.changePriceText.setOnClickListener {
            binding.currentPriceBox.visibility = View.INVISIBLE
            binding.changePriceText.visibility = View.INVISIBLE
            binding.changePriceBox.visibility = View.VISIBLE
        }
        binding.changeAddressText.setOnClickListener {
            binding.currentAddressBox.visibility = View.INVISIBLE
            binding.changeAddressText.visibility = View.INVISIBLE
            binding.changeAddressBox.visibility = View.VISIBLE
        }
        binding.changePostcodeText.setOnClickListener {
            binding.currentPostcodeBox.visibility = View.INVISIBLE
            binding.changePostcodeText.visibility = View.INVISIBLE
            binding.changePostcodeBox.visibility = View.VISIBLE
        }
        binding.changeRatingText.setOnClickListener {
            binding.currentRatingBox.visibility = View.INVISIBLE
            binding.changeRatingText.visibility = View.INVISIBLE
            binding.changeRatingBox.visibility = View.VISIBLE
        }

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


