package com.example.android.healthyhome.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.android.healthyhome.R
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
    private lateinit var providerID: String
    private lateinit var dbProvider : DatabaseReference

    private lateinit var binding : FragmentChosenProviderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            providerID = it.getString(ARG_PARAM1).toString()

        }
        dbProvider = FirebaseDatabase.getInstance().reference.child("Provider").child(providerID)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentChosenProviderBinding>(
            inflater,R.layout.fragment_chosen_provider,container,false
        )
        return binding.root

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ChosenProviderFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(providerID: String) =
            ChosenProviderFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, providerID)
                }
            }
    }
}