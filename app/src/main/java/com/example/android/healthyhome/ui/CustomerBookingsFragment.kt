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
import com.example.android.healthyhome.database.util.Bookings
import com.example.android.healthyhome.database.util.BookingsAdapter
import com.example.android.healthyhome.database.util.Common
import com.example.android.healthyhome.database.util.IMyAPI
import com.example.android.healthyhome.databinding.FragmentCustomerBookingsBinding


class CustomerBookingsFragment : Fragment() {
    private lateinit var binding: FragmentCustomerBookingsBinding
    private lateinit var mService: IMyAPI
    private lateinit var navController: NavController
    private lateinit var bookings: Bookings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = CustomerBookingsFragmentArgs.fromBundle(requireArguments())
        bookings = args.bookings



    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentCustomerBookingsBinding>(
            inflater, R.layout.fragment_customer_bookings, container, false
        )


        mService = Common.getAPI()

        navController = findNavController()



        binding.customerBookingsRecycler.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = BookingsAdapter(bookings)
        }




        return binding.root
    }

}