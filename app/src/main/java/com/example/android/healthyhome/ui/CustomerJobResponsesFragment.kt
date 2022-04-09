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
import com.example.android.healthyhome.database.util.Responses
import com.example.android.healthyhome.database.util.ResponsesAdapter
import com.example.android.healthyhome.databinding.FragmentCustomerJobResponsesBinding
import com.example.android.healthyhome.databinding.FragmentPostJobBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomerJobResponsesFragment : Fragment() {
    private lateinit var binding: FragmentCustomerJobResponsesBinding
    private lateinit var mService: IMyAPI
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentCustomerJobResponsesBinding>(
            inflater, R.layout.fragment_customer_job_responses, container, false
        )

        mService = Common.getAPI()

        navController = findNavController()

        getResponses()


        return binding.root
    }

    private fun getResponses() {
        val cid = Common.currentUser.cid
        mService.getResponsesByCid(Integer.parseInt(cid)).enqueue(object: Callback<Responses>{
            override fun onResponse(call: Call<Responses>, response: Response<Responses>) {
                val result = response.body()!!
                binding.responsesRecycler.apply {
                    layoutManager = LinearLayoutManager(this.context)
                    adapter = ResponsesAdapter(result)
                }
            }

            override fun onFailure(call: Call<Responses>, t: Throwable) {
                Toast.makeText(activity?.applicationContext, "Error retrieving responses", Toast.LENGTH_SHORT).show()
            }

        })

        //TODO:: needs testing

    }

}