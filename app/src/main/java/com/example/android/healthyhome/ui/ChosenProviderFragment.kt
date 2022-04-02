package com.example.android.healthyhome.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.android.healthyhome.R
import com.example.android.healthyhome.database.Provider
import com.example.android.healthyhome.databinding.FragmentChosenProviderBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [ChosenProviderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChosenProviderFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var chosenProvider: Provider

    private lateinit var binding : FragmentChosenProviderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var args = ChosenProviderFragmentArgs.fromBundle(requireArguments())
        chosenProvider = args.provider[0]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentChosenProviderBinding>(
            inflater,R.layout.fragment_chosen_provider,container,false
        )

        binding.bioTextView.text = chosenProvider.Bio


        binding.arrangeButton.setOnClickListener {
            it.findNavController().navigate(ChosenProviderFragmentDirections.actionChosenProviderFragmentToFilterServicesFragment(chosenProvider.pid.toString()))
        }
        return binding.root

    }


}