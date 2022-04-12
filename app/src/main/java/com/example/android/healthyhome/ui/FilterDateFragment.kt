package com.example.android.healthyhome.ui

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.android.healthyhome.R
import com.example.android.healthyhome.database.AvailabilityType
import com.example.android.healthyhome.database.BookingType
import com.example.android.healthyhome.database.DBCalls
import com.example.android.healthyhome.database.mail.Mail
import com.example.android.healthyhome.database.util.Common
import com.example.android.healthyhome.databinding.FragmentFilterDateBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class FilterDateFragment : Fragment(){

    private lateinit var binding : FragmentFilterDateBinding
    val REQUEST_DATE: Int = 0

    //Defaults, will display current date on creation
    var selYear: Int = -1
    var selMonth: Int = -1
    var selDay: Int = -1

    var curDateStr: String = ""

    var cid: Int = -1
    var pid: Int = -1
    var recurring: Boolean = false
    var numOfRooms: Int = -1
    var extras: MutableList<String> = mutableListOf()
    var times: MutableList<String> = mutableListOf()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //Obtain variables
        val args = FilterDateFragmentArgs.fromBundle(requireArguments())
        cid = args.cid
        pid = args.pid
        recurring = args.recurring
        numOfRooms = args.numOfRooms
        extras = args.services.toMutableList()

        //Set current date
        setCurrentDate()

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentFilterDateBinding>(inflater, R.layout.fragment_filter_date, container, false)
        fillBookingInformation()
        setUpTimeSpinner()

        binding.tvDatePicker.setOnClickListener { _ ->
            println(pid)
            DBCalls.getAvailabilityByPID(pid.toString()){a ->
                if(!a.set){
                    binding.tvDatePicker.text = "ERROR: Provider hasn't set their availability"
                }else{
                    displayDatePrompt(a)
                }
            }

        }

        binding.btnConfirm.setOnClickListener {
            val timeSelection = binding.spnrTimePicker.selectedItemId
            if(timeSelection >= 0){
                var time = times[timeSelection.toInt()]
                println(numOfRooms)
                println(extras.size)
                DBCalls.insertBooking(cid.toString(), pid.toString(), binding.tvDatePicker.text.toString(), time.replace(":", ""), binding.tvExtrasPresent.text.toString().replace(" ", ""), numOfRooms + extras.size)

                //UID OBTAINED FROM SESSION
                DBCalls.getEmailByUID(Common.currentUser.uid.toString()){ email ->
                    Mail.sendEMail("Healthy Homes : New Booking", Mail.buildBookingConfirmation(binding.tvCustNamePresent.text.toString(), binding.tvProvNamePresent.text.toString(), binding.tvDatePicker.text.toString(), time), email)
                }

                Alerts.basicAlert(this.requireContext(), "", "Your booking has been confirmed\nYou will receive an additional confirmation by E-Mail", false, ::confirmSuccess).show()

            }else{
                Alerts.basicAlert(this.requireContext(), "", "Please select a date and time to continue this booking", false, ::confirmNoInput).show()
            }
        }



        return binding.root
    }

    private fun confirmNoInput(dialog: DialogInterface, which: Int){}
    private fun confirmSuccess(dialog: DialogInterface, which: Int){
        //Temporary takes you back to booking screen
        view?.findNavController()?.navigate(FilterDateFragmentDirections.actionFilterDateFragmentToServicesFragment())
    }

    /**
     * Fills in the booking information that has already been provided
     */
    private fun fillBookingInformation(){
        //cid
        DBCalls.getCustomerFullNameByID(cid.toString()){name ->
            binding.tvCustNamePresent.text = name
        }
        //pid
        DBCalls.getProviderNameByID(pid.toString()){name ->
            binding.tvProvNamePresent.text = name
        }
        //recurring
        if(recurring){
            binding.tvRecurringPresent.text = "Yes"
        }else{
            binding.tvRecurringPresent.text = "No"
        }
        //extras
        var exString = "No Extras Selected"
        if(extras.size != 0){
            exString = ""
            for(s in extras){
                exString += "${s}, "
            }
            exString = exString.substring(0, exString.length - 2)
        }
        binding.tvExtrasPresent.text = exString
    }

    /**
     * Displays the prompt to pick a date
     */
    private fun displayDatePrompt(avail: AvailabilityType){

        val picker: DatePickerFragment = DatePickerFragment(selYear, selMonth, selDay)
        picker.setTargetFragment(this, REQUEST_DATE)

        val dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        var offset = (dayOfWeek - 2) % 7
        var availDaysList: MutableList<Calendar> = mutableListOf()

        //60 days in advance
        for(i in 1..60){
            var dayI = Calendar.getInstance()
            dayI.add(Calendar.DAY_OF_MONTH, i)

            val real = (i + offset) % 7
            //Mon
            if(real == 0){
                //Not a day off
                if(avail.mon != "000000000000000000000000000000000000000000000000"){
                    availDaysList.add(dayI)
                }
            }
            //Tues
            else if(real == 1){
                //Not a day off
                if(avail.tue != "000000000000000000000000000000000000000000000000"){
                    availDaysList.add(dayI)
                }
            }
            //Wed
            else if(real == 2){
                //Not a day off
                if(avail.wed != "000000000000000000000000000000000000000000000000"){
                    availDaysList.add(dayI)
                }
            }
            //Thurs
            else if(real == 3){
                //Not a day off
                if(avail.thu != "000000000000000000000000000000000000000000000000"){
                    availDaysList.add(dayI)
                }
            }
            //Fri
            else if(real == 4){
                //Not a day off
                if(avail.fri != "000000000000000000000000000000000000000000000000"){
                    availDaysList.add(dayI)
                }
            }
            //Sat
            else if(real == 5){
                //Not a day off
                if(avail.sat != "000000000000000000000000000000000000000000000000"){
                    availDaysList.add(dayI)
                }
            }
            //Sunday
            else if(real == 6){
                //Not a day off
                if(avail.sun != "000000000000000000000000000000000000000000000000"){
                    availDaysList.add(dayI)
                }
            }
        }

        picker.setDaysToShow(availDaysList.toTypedArray())
        picker.show(parentFragmentManager, "datePicker")
    }

    private fun setUpTimeSpinner(){
        updateTimeItems("", listOf())
    }

    /**
     * This will check the database for available times on the date set and update the spinner items to reflect that
     */
    private fun updateTimeItems(dayAvailability: String, existingBookings: List<BookingType>){

        if(dayAvailability != "") {
            var min: Int = 0
            var busy: Int = 0
            for (i in 0..47) {
                val curMin: Int = min % 60
                val curHour: Int = min / 60
                var timeString = changeTimeString(curHour, curMin)

                if (busy != 0) {
                    min += 30
                    busy--
                    continue
                }
                if (dayAvailability[i] == '0') {
                    min += 30
                    continue
                }

                var conflict = false
                for (booking in existingBookings) {
                    if (booking.time == timeString) {
                        min += 30
                        busy = booking.halfHoursreq
                        conflict = true
                        break
                    }
                }

                if (!conflict) {
                    min += 30
                    var hStr = curHour.toString()
                    if(hStr.length == 1) {
                        hStr = "0${curHour}"
                    }
                    var mStr = curMin.toString()
                    if(mStr.length == 1) {
                        mStr = "0${curMin}"
                    }
                    times.add("${hStr}:${mStr}")
                }
            }
        }


        //This would be for if there are no valid times for the date selected
        if(dayAvailability == "" || times.isEmpty()){
            var adap = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, arrayOf("No Times"))
            adap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spnrTimePicker.adapter = adap
        }
        else {
            var adap: ArrayAdapter<String> =
                ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, times)
            adap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spnrTimePicker.adapter = adap
        }

    }

    /**
     * Sets the current date
     */
    private fun setCurrentDate(){
        val cal: Calendar = Calendar.getInstance()
        var d: String = cal.get(Calendar.DAY_OF_MONTH).toString()
        var m: String = cal.get(Calendar.WEEK_OF_MONTH).toString()
        var y: String = cal.get(Calendar.YEAR).toString()
        if(d.length == 1){
            d = "0${d}"
        }
        if(m.length == 1){
            m = "0${m}"
        }
        curDateStr = "${y}/${m}/${d}"
    }

    /**
     * Converts int time to string time
     */
    private fun changeTimeString(hour: Int, min: Int): String{
        var h: String = hour.toString()
        var m: String = min.toString()
        if(hour < 10){
            h = "0${hour}"
        }
        if(min < 10){
            m = "0${min}"
        }
        return "${h}${m}"
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
                selDay = day
                selMonth = month
                selYear = year
            }

            var monStr = month.toString()
            var dayStr = day.toString()
            if(monStr.length == 1){
                monStr = "0${monStr}"
            }
            if(dayStr.length == 1){
                dayStr = "0${dayStr}"
            }
            binding.tvDatePicker.text = "${year}/${monStr}/${dayStr}"
            println("${year}/${monStr}/${dayStr}")

            DBCalls.getBookingsByPIDAndDate(pid.toString(), "${year}/${monStr}/${dayStr}"){ bookingList ->
                DBCalls.getAvailabilityByPID(pid.toString()){a ->
                    if(a.set){
                        var availabilityStr: String

                        //Finds day of week of the date you have selected
                        val sdf= SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                        val selDate = sdf.parse("${selDay}/${selMonth}/${selYear}")
                        sdf.applyPattern("EEE")
                        val selDayOfWeek = sdf.format(selDate)

                        if(selDayOfWeek == "Mon"){
                            availabilityStr = a.mon
                        }else if(selDayOfWeek == "Tue"){
                            availabilityStr = a.tue
                        }else if(selDayOfWeek == "Wed"){
                            availabilityStr = a.wed
                        }else if(selDayOfWeek == "Thu"){
                            availabilityStr = a.thu
                        }else if(selDayOfWeek == "Fri"){
                            availabilityStr = a.fri
                        }else if(selDayOfWeek == "Sat"){
                            availabilityStr = a.sat
                        }else if(selDayOfWeek == "Sun"){
                            availabilityStr = a.sun
                        }else{
                            availabilityStr = ""
                        }

                        updateTimeItems(availabilityStr, bookingList)
                    }
                }
            }
        }
    }

}