package com.example.android.healthyhome.ui

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.android.healthyhome.R
import com.example.android.healthyhome.database.DBCalls
import com.example.android.healthyhome.database.util.Common
import com.example.android.healthyhome.database.util.IMyAPI
import com.example.android.healthyhome.databinding.FragmentPostJobBinding
import com.example.android.healthyhome.databinding.FragmentProviderBookingsBinding

class PostJobFragment : Fragment() {
    private lateinit var binding: FragmentPostJobBinding
    private lateinit var navController: NavController

    private val possibleServices: Array<String> = arrayOf("Cleaning", "Locksmith", "Dog Walking", "Removals", "Gardening", "Odd Jobs")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentPostJobBinding>(inflater, R.layout.fragment_post_job, container, false)
        navController = findNavController()
        setupSpinner()

        binding.btnPost.setOnClickListener {
            if(binding.spnrService.selectedItemId != AdapterView.INVALID_ROW_ID){
                Alerts.cancelableAlert(this.requireContext(), "", "By confirming this you will notify all relevant providers of your listing.\nCheck the information provided then click OK", ::confirmPressed).show()
            }else{
                Alerts.basicAlert(this.requireContext(), "", "Please select a service from the dropdown provided", false, ::confirmNoInput).show()
            }
        }

        return binding.root
    }

    private fun confirmNoInput(dialog: DialogInterface, which: Int){}
    private fun confirmPressed(dialog: DialogInterface, which: Int){
        DBCalls.postJob(Common.currentUser.cid.toString(), possibleServices[binding.spnrService.selectedItemId.toInt()], binding.etMessage.text.toString())
        Alerts.basicAlert(this.requireContext(), "", "Job listing successfully posted", false, ::confirmNoInput).show()
        navController.navigate(PostJobFragmentDirections.actionPostJobFragmentToCustomerHomeFragment())
    }

    private fun setupSpinner(){
        var adap = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, possibleServices)
        adap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnrService.adapter = adap
    }

}