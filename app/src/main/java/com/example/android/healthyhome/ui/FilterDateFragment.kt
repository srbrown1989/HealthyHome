package com.example.android.healthyhome.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.android.healthyhome.R
import com.example.android.healthyhome.databinding.FragmentFilterDateBinding

class FilterDateFragment : Fragment() {

    private lateinit var binding : FragmentFilterDateBinding
    private val REQUEST_DATE: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentFilterDateBinding>(inflater, R.layout.fragment_filter_date, container, false)


        binding.tvDatePicker.setOnClickListener { _ ->
            val picker: DatePickerFragment = DatePickerFragment()
            picker.setTargetFragment(this, REQUEST_DATE)
            picker.show(parentFragmentManager, "datePicker")
        }

        binding.btnNext.setOnClickListener { view: View ->
            //Go to the next part of the page
        }

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode != Activity.RESULT_OK){
            return
        }
        if(requestCode == REQUEST_DATE) {
            val date: String? = data?.getStringExtra("DATE")
            binding.tvDatePicker.text = date
        }

    }

}