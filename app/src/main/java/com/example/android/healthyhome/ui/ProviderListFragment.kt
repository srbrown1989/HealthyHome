package com.example.android.healthyhome.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.healthyhome.R
import com.example.android.healthyhome.database.Provider
import com.example.android.healthyhome.database.util.ProviderListAdapter
import com.example.android.healthyhome.database.util.Providers
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.example.android.healthyhome.databinding.FragmentProviderListBinding


class ProviderListFragment  : Fragment(), ProviderListAdapter.OnItemClickListener{

    private lateinit var providers : List<Provider>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val args = ProviderListFragmentArgs.fromBundle(requireArguments())
        providers = args.providers
        val binding: FragmentProviderListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_provider_list, container, false)
        // Inflate the layout for this fragment

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ProviderListAdapter(providers as Providers, this@ProviderListFragment)
        }

        return binding.root
    }

    override fun onItemClick(position: Int) {
        //Toast.makeText(this.context, "Item position: $position Item pid: ${providers.get(position).pid} clicked", Toast.LENGTH_SHORT).show()
        var navigation = findNavController()

        val temp : Providers = Providers()
        temp.add(providers[position])

        navigation.navigate(ProviderListFragmentDirections.actionProviderListFragmentToChosenProviderFragment(
            temp
        ))
        //TODO : NAVIGATE TO NEXT SCREEN WITH PID AS ARGUMENT. grab
    }


}