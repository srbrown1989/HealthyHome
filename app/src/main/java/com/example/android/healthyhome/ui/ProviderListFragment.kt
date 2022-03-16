package com.example.android.healthyhome.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.healthyhome.R
import com.example.android.healthyhome.database.DBCalls
import com.example.android.healthyhome.database.Provider
import com.example.android.healthyhome.database.util.ProviderListAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.example.android.healthyhome.databinding.FragmentProviderListBinding


class ProviderListFragment : Fragment() {

    private lateinit var fAuth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding: FragmentProviderListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_provider_list, container, false)
        // Inflate the layout for this fragment
        fAuth = Firebase.auth

        val providerList = generateDummyList(30)
        //Obtain passed service
        val args = ProviderListFragmentArgs.fromBundle(requireArguments())

        DBCalls.getProvidersFromService(args.service){ providerList ->
            for(p in providerList){
                println(p.companyName)
            }
        }
        //All of this green below will have to be done within the lambda curly brackets above, this is just how it has to work since its asynchronous but it shouldn't cause any issues
        //providerList is of type List<ProviderCViewType> so the ProviderListAdapter will have to be updated to work with this instead of your old Provider dataclass
        //Worth noting that I didn't have the php return provider personal info like address, phone number and email. Thought would be better from a security front and it can be obtained later in the booking sequence if needed
        //All the variables you have to work with are in ProviderCViewType (means Provider Customer View)
        //Shouldn't have any problems with the data returning as I tested it out and it seems to work just fine

        /**
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.adapter = ProviderListAdapter(providerList)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.setHasFixedSize(true)
        */

        binding.tempButton.setOnClickListener {
            it.findNavController().navigate( ProviderListFragmentDirections.actionProviderListFragmentToChosenProviderFragment())
        }

        return binding.root
    }
    private fun generateDummyList(size: Int): List<Provider> {

        val list = ArrayList<Provider>()

        for(i in 0 until size) {

            val item = Provider("UID$i", "Provider Name$i", "Contact$i", "Email$i", "Bio$i", "Service$i",
            "CreditCard$i", 5, 5)
            list += item
        }
        return list
    }



}