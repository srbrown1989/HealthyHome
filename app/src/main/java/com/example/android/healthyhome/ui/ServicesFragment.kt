package com.example.android.healthyhome.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.healthyhome.R
import com.example.android.healthyhome.databinding.FragmentServicesBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


/**
 * A simple [Fragment] subclass.
 * Use the [ServicesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ServicesFragment : Fragment() {

    private lateinit var fAuth : FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentServicesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_services, container, false)

        fAuth = Firebase.auth

        if(fAuth.currentUser?.uid != null) {
            Toast.makeText(context, "Logged in as ${fAuth.currentUser!!.email}",Toast.LENGTH_LONG).show()

        }

        binding.cleaningCardView.setOnClickListener {
            it.findNavController().navigate(ServicesFragmentDirections.actionServicesFragmentToProviderSelectionFragment())
        }

        return binding.root
    }


}