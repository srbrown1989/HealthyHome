package com.example.android.healthyhome.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.android.healthyhome.R
import com.example.android.healthyhome.database.User
import com.example.android.healthyhome.database.util.Common
import com.example.android.healthyhome.database.util.IMyAPI
import com.example.android.healthyhome.database.util.Providers
import com.example.android.healthyhome.databinding.FragmentServicesBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ServicesFragment : Fragment() {
    private lateinit var currentUser: User
    private lateinit var navController: NavController
    private lateinit var mService : IMyAPI

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentServicesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_services, container, false)

        currentUser= Common.currentUser

        Toast.makeText(context, "Logged in as ${currentUser.email}",Toast.LENGTH_LONG).show()

        binding.icServicesCleaner.setOnClickListener {
            getProviders("cleaning")
        }
        mService = Common.getAPI()

        binding.servicesFragmentText.text = currentUser.uid.toString()

        return binding.root
    }

    private fun getProviders(service: String) {
        mService.getProvidersByService(service).enqueue(object: Callback<Providers>{
            override fun onResponse(call: Call<Providers>, response: Response<Providers>) {
                val result : Providers = response.body()!!
                navController.navigate(ServicesFragmentDirections.actionServicesFragmentToProviderListFragment2(result))
            }

            override fun onFailure(call: Call<Providers>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
    }


}