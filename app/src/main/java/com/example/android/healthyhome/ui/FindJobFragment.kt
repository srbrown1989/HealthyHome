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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.healthyhome.R
import com.example.android.healthyhome.database.util.Common
import com.example.android.healthyhome.database.util.IMyAPI
import com.example.android.healthyhome.database.util.Jobs
import com.example.android.healthyhome.database.util.JobsAdapter
import com.example.android.healthyhome.databinding.FragmentFindJobBinding
import com.example.android.healthyhome.databinding.FragmentProviderChangeBioBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        mService = Common.getAPI()

        getJobs()


        return binding.root

    }

    private fun getJobs() {
        val service = Common.currentProvider.service

        mService.getJobsByService(service).enqueue(object: Callback<Jobs>{
            override fun onResponse(call: Call<Jobs>, response: Response<Jobs>) {
                val result = response.body()!!
                binding.jobRecycler.apply {
                    layoutManager = LinearLayoutManager(this.context)
                    adapter = JobsAdapter(result)
                }
            }

            override fun onFailure(call: Call<Jobs>, t: Throwable) {
                Toast.makeText(activity?.applicationContext, "Error getting jobs", Toast.LENGTH_SHORT).show()
            }

        })
    }


}