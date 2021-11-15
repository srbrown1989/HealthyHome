package com.example.android.healthyhome.ui

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import java.util.*

class DatePickerFragment(setYear: Int, setMonth: Int, setDay: Int) : DialogFragment(), DatePickerDialog.OnDateSetListener {

    var year = setYear
    var month = setMonth
    var day = setDay
    private lateinit var dpd: DatePickerDialog

    /**
     * Set the dates to display
     */
    fun setDaysToShow(days: Array<Calendar>){
        initDialog()
        dpd.selectableDays = days
    }

    override fun show(manager: FragmentManager, tag: String?) {
        initDialog()
        dpd.show(manager, tag)
    }

    private fun initDialog(){
        checkPresetDate()
        if(!this::dpd.isInitialized) dpd = DatePickerDialog.newInstance(this, year, month, day)
    }

    /*
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        checkPresetDate()

        if(!this::dpd.isInitialized) dpd = DatePickerDialog.newInstance(this, year, month, day)
        else return dpd

        // Create a new instance of DatePickerDialog and return it
        return dpd
    }
    */


    override fun onDateSet(view: DatePickerDialog, year: Int, month: Int, day: Int) {

        val intent = Intent()
        intent.putExtra("DAY", day)
        intent.putExtra("MONTH", month)
        intent.putExtra("YEAR", year)
        targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)

    }

    private fun checkPresetDate(){
        if(year == -1 && month == -1 && day == -1) {
            // Use the current date as the default date in the picker
            val c = Calendar.getInstance()
            year = c.get(Calendar.YEAR)
            month = c.get(Calendar.MONTH)
            day = c.get(Calendar.DAY_OF_MONTH)
        }
    }

}