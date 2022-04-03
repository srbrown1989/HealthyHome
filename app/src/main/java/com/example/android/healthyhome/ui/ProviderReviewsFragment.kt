package com.example.android.healthyhome.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.healthyhome.R
import com.example.android.healthyhome.database.util.Common
import com.example.android.healthyhome.database.util.IMyAPI
import com.example.android.healthyhome.database.util.Reviews
import com.example.android.healthyhome.database.util.ReviewsAdapter
import com.example.android.healthyhome.databinding.FragmentProviderReviewsBinding
import com.example.android.healthyhome.databinding.FragmentProviderSignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProviderReviewsFragment : Fragment() {

    private lateinit var binding: FragmentProviderReviewsBinding
    private lateinit var navController: NavController

    private lateinit var mService : IMyAPI



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentProviderReviewsBinding>(
            inflater, R.layout.fragment_provider_reviews, container, false
        )

        mService = Common.getAPI()

        getReviews()

        navController = findNavController()

//        binding.reviewsRecycler.apply{
//            layoutManager = LinearLayoutManager(this.context)
//            adapter = ReviewsAdapter(reviews)
//        }


        return binding.root
    }

    private fun getReviews() {
        mService.getReviewsByPid("20").enqueue(object: Callback<Reviews>{//TODO: replace pid with actual pid call.
            override fun onResponse(call: Call<Reviews>, response: Response<Reviews>) {
                val reviews : Reviews = response.body()!!
                binding.reviewsRecycler.apply{
                layoutManager = LinearLayoutManager(this.context)
                adapter = ReviewsAdapter(reviews)
            }
            }

            override fun onFailure(call: Call<Reviews>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }
}