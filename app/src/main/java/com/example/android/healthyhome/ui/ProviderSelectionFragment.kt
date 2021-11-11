package com.example.android.healthyhome.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.android.healthyhome.R
import com.example.android.healthyhome.adapters.ProviderRecyclerAdapter
import com.example.android.healthyhome.data.ProviderDataSource
import com.example.android.healthyhome.databinding.FragmentProviderSelectionBinding
import javax.sql.DataSource

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProviderSelectionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProviderSelectionFragment : Fragment() {

    private lateinit var binding : FragmentProviderSelectionBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentProviderSelectionBinding>(
            inflater, R.layout.fragment_provider_selection, container, false
        )
        val providerDataSet = ProviderDataSource().loadProviders()
        val recyclerView = binding.providerRecyclerView

        recyclerView.adapter = ProviderRecyclerAdapter(this.context,providerDataSet)


        return binding.root
    }




}