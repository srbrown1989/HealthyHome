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
import com.example.android.healthyhome.R
import com.example.android.healthyhome.databinding.FragmentFilterDateBinding
import java.util.*

class FilterDateFragment : Fragment(){

    private lateinit var binding : FragmentFilterDateBinding
    val REQUEST_DATE: Int = 0
    val REQUEST_TIME: Int = 1

    //Defaults, will display current date and time on creation
    var curYear: Int = -1
    var curMonth: Int = -1
    var curDay: Int = -1
    var curHour: Int = -1
    var curMin: Int = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentFilterDateBinding>(inflater, R.layout.fragment_filter_date, container, false)


        binding.tvDatePicker.setOnClickListener { _ -> displayDatePrompt() }
        binding.tvTimePicker.setOnClickListener { _ -> displayTimePrompt() }

        binding.btnNext.setOnClickListener { view: View ->
            //Go to the next part of the page
        }

        return binding.root
    }

    /**
     * Displays the prompt to pick a date
     */
    private fun displayDatePrompt(){
        val picker: DatePickerFragment = DatePickerFragment(curYear, curMonth, curDay)
        picker.setTargetFragment(this, REQUEST_DATE)

        //Testing with just todays date
        picker.setDaysToShow(arrayOf(Calendar.getInstance()))
        picker.show(parentFragmentManager, "datePicker")
    }

    /**
     * Displays the prompt to pick a time
     */
    private fun displayTimePrompt(){
        val picker: TimePickerFragment = TimePickerFragment(curHour, curMin)
        picker.setTargetFragment(this, REQUEST_TIME)
        picker.show(parentFragmentManager, "timePicker")
    }

    /**
     * Method called when an intent is sent to this class
     * Allows the date and time to be set on the fragment
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode != Activity.RESULT_OK){
            return
        }

        //When date is returned
        if(requestCode == REQUEST_DATE) {
            val day: Int? = data?.getIntExtra("DAY", -1)
            val month: Int? = data?.getIntExtra("MONTH", -1)
            val year: Int? = data?.getIntExtra("YEAR", -1)

            if(day != null && month != null && year != null){
                curDay = day
                curMonth = month
                curYear = year
            }

            binding.tvDatePicker.text = "${curYear}/${curMonth}/${curDay}"
        }

        //When time is returned
        if(requestCode == REQUEST_TIME){
            val hour: Int? = data?.getIntExtra("HOUR", -1)
            val minute: Int? = data?.getIntExtra("MINUTE", -1)

            if(hour != null && minute != null){
                curHour = hour
                curMin = minute
            }

            binding.tvTimePicker.text = "${curHour}:${curMin}"
        }
    }

}