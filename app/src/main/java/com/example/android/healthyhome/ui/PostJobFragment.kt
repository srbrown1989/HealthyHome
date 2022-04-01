package com.example.android.healthyhome.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.android.healthyhome.R
import com.example.android.healthyhome.database.util.Common
import com.example.android.healthyhome.database.util.IMyAPI
import com.example.android.healthyhome.databinding.FragmentPostJobBinding
import com.example.android.healthyhome.databinding.FragmentProviderBookingsBinding

class PostJobFragment : Fragment() {
    private lateinit var binding: FragmentPostJobBinding
    private lateinit var mService: IMyAPI
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentPostJobBinding>(
            inflater, R.layout.fragment_post_job, container, false
        )

        mService = Common.getAPI()

        navController = findNavController()




        return binding.root
    }

}