package com.example.android.healthyhome.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.healthyhome.R
import com.example.android.healthyhome.database.util.*
import com.example.android.healthyhome.databinding.FragmentProviderBookingsBinding

class ProviderBookingsFragment : Fragment() {
    private lateinit var binding: FragmentProviderBookingsBinding
    private lateinit var mService: IMyAPI
    private lateinit var navController: NavController
    private lateinit var bookings: Bookings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = ProviderBookingsFragmentArgs.fromBundle(requireArguments())
        bookings = args.bookings




    }

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



        binding.bookingsRecycler.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = BookingsAdapter(bookings)
        }




        return binding.root
    }

}

