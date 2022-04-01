package com.example.android.healthyhome.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.android.healthyhome.R
import com.example.android.healthyhome.database.util.Common
import com.example.android.healthyhome.database.util.IMyAPI
import com.example.android.healthyhome.databinding.FragmentProviderBookingsBinding
import com.example.android.healthyhome.databinding.FragmentProviderSignUpBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ProviderBookingsFragment : Fragment() {
    private lateinit var binding: FragmentProviderBookingsBinding
    private lateinit var mService: IMyAPI
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentProviderBookingsBinding>(
            inflater, R.layout.fragment_provider_bookings, container, false
        )

        mService = Common.getAPI()

        navController = findNavController()




        return binding.root
    }

}

