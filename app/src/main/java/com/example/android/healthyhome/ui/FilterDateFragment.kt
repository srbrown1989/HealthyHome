package com.example.android.healthyhome.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.android.healthyhome.R
import com.example.android.healthyhome.databinding.FragmentFilterDateBinding
import java.util.*

class FilterDateFragment : Fragment(){

    private lateinit var binding : FragmentFilterDateBinding
    val REQUEST_DATE: Int = 0

    //Defaults, will display current date on creation
    var curYear: Int = -1
    var curMonth: Int = -1
    var curDay: Int = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentFilterDateBinding>(inflater, R.layout.fragment_filter_date, container, false)
        setUpTimeSpinner()


        binding.tvDatePicker.setOnClickListener { _ -> displayDatePrompt() }

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
        var date2 = Calendar.getInstance()
        date2.set(2022, 1, 4)
        var date2cal = Calendar.getInstance()
        date2cal.set(2022, 1, 10)

        picker.setDaysToShow(arrayOf(Calendar.getInstance(), date2cal, date2))
        picker.show(parentFragmentManager, "datePicker")
    }

    private fun setUpTimeSpinner(){
        updateTimeItems()
    }

    /**
     * This will check the database for available times on the date set and update the spinner items to reflect that
     */
    private fun updateTimeItems(){
        //This all has to be temporary as I have no database link but this string array would be formed from the database
        val times = arrayOf("10:15", "12:45", "13:15", "14:00", "14:50", "15:00", "16:50", "18:20", "18:30", "19:20")
        //This would be for if there are no valid times for the date selected
        if(times.isEmpty()){
            var adap = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, arrayOf("No Times"))
        }
        else {
            var adap: ArrayAdapter<String> =
                ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, times)
            adap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spnrTimePicker.adapter = adap
        }

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
    }

}