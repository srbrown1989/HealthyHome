package com.example.android.healthyhome.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.android.healthyhome.R
import com.example.android.healthyhome.database.util.Bookings
import com.example.android.healthyhome.database.util.Common
import com.example.android.healthyhome.database.util.IMyAPI
import com.example.android.healthyhome.databinding.FragmentCustomerHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomerHomeFragment : Fragment() {

    private lateinit var binding : FragmentCustomerHomeBinding
    private lateinit var mService: IMyAPI
    private lateinit var navController: NavController


    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
// Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentCustomerHomeBinding>(
            inflater, R.layout.fragment_customer_home, container, false
        )


        mService = Common.getAPI()

        navController = findNavController()

        binding.searchButton.setOnClickListener {
            navController.navigate(CustomerHomeFragmentDirections.actionCustomerHomeFragmentToServicesFragment())
        }

        binding.postButton.setOnClickListener {
            navController.navigate(CustomerHomeFragmentDirections.actionCustomerHomeFragmentToPostJobFragment())
        }

        binding.bookingsButton.setOnClickListener {
            getCustomerBookings()
        }

        if(Common.currentUser != null){
            binding.welcomeTextView.text = String.format(getString(R.string.welcome),getFirstName(Common.currentUser.name))
        }




        return binding.root    }

    private fun getCustomerBookings() {
        mService.getBookingsByCid(Common.currentUser.uid.toString()).enqueue(object: Callback<Bookings>{ //TODO: change currentUser.uid to actual cid
            override fun onResponse(call: Call<Bookings>, response: Response<Bookings>) {
                val result = response.body()!!
                navController.navigate(CustomerHomeFragmentDirections.actionCustomerHomeFragmentToCustomerBookingsFragment(result))

            }

            override fun onFailure(call: Call<Bookings>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun getFirstName(name: String): String {

        val words = name.split("\\s".toRegex()).toTypedArray()

        return words[0];
    }
}