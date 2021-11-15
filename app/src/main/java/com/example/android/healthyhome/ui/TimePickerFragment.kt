package com.example.android.healthyhome.ui

import android.app.Activity
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class TimePickerFragment(setHour: Int, setMinute: Int) : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    var hour: Int = setHour
    var min: Int = setMinute

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current time as the default date in the picker
        if(hour == -1 && min == -1) {
            val c = Calendar.getInstance()
            hour = c.get(Calendar.HOUR_OF_DAY)
            min = c.get(Calendar.MINUTE)
        }


        // Create a new instance of TimePickerDialog and return it
        return TimePickerDialog(this.requireContext(), this, hour, min, DateFormat.is24HourFormat(this.requireActivity()))
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minuteOfDay: Int) {

        val intent = Intent()

        intent.putExtra("HOUR", hourOfDay)
        intent.putExtra("MINUTE", minuteOfDay)
        targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)

    }

}