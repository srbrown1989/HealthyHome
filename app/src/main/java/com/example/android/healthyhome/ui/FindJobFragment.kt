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
import com.example.android.healthyhome.database.util.IMyAPI
import com.example.android.healthyhome.databinding.FragmentFindJobBinding
import com.example.android.healthyhome.databinding.FragmentProviderChangeBioBinding

class FindJobFragment : Fragment() {

    private lateinit var binding: FragmentFindJobBinding
    private lateinit var navController: NavController
    private lateinit var mService: IMyAPI

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentFindJobBinding>(
            inflater, R.layout.fragment_find_job , container, false
        )

        navController = findNavController()


        return binding.root

    }



}