package com.example.android.healthyhome.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import android.widget.ToggleButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.marginLeft
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.android.healthyhome.R
import com.example.android.healthyhome.databinding.FragmentFilterServicesBinding
import com.example.android.healthyhome.databinding.FragmentProviderHomeBinding
import com.google.firebase.database.FirebaseDatabase

class FilterServicesFragment : Fragment() {

    private lateinit var binding : FragmentFilterServicesBinding
    private var recurring : Boolean = false
    private var activeServices : MutableList<String> = mutableListOf();

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activeServices = mutableListOf()
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentFilterServicesBinding>(inflater, R.layout.fragment_filter_services, container, false)

        binding.btnOneOff.setOnCheckedChangeListener{ _, isChecked ->
            if(isChecked){
                recurring = false
                binding.btnRegular.isChecked = false
            }else{
                binding.btnRegular.isChecked = true
                recurring = true
            }
        }

        binding.btnRegular.setOnCheckedChangeListener{ _, isChecked ->
            if(isChecked){
                recurring = true
                binding.btnOneOff.isChecked = false
            }else{
                recurring = false
                binding.btnOneOff.isChecked = true
            }
        }


        binding.btnNext.setOnClickListener { view: View ->
            val uid = "100" //Filler
            val numOfRooms = binding.radioGroup.checkedRadioButtonId + 1
            view.findNavController().navigate(FilterServicesFragmentDirections.actionFilterServicesFragmentToFilterDateFragment(uid, recurring, numOfRooms, activeServices.toTypedArray()))
        }

        buildServiceButtons(listOf("Sofa", "Carpet", "Bathroom", "Hoover", "Party", "Upstairs", "more", "etc"))

        return binding.root
    }

    private fun buildServiceButtons(services : List<String>){
        var prevButton : View? = null
        var prevAboveView : View = binding.radioGroup
        var counter = 0

        //binding.parentConstraint

        for(ser in services){
            val curButton = ToggleButton(this.requireContext())
            curButton.id = View.generateViewId()
            curButton.text = ser
            curButton.textOn = ser
            curButton.textOff = ser
            curButton.textSize = 11f
            curButton.width = pixelsToDisplayPixels(0)
            curButton.height = pixelsToDisplayPixels(150)
            curButton.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    activeServices.add(ser)
                }else{
                    activeServices.remove(ser)
                }
                Toast.makeText(this.requireContext(), activeServices.toString(), Toast.LENGTH_SHORT).show()
            }

            binding.parentConstraint.addView(curButton)
            var constSet = ConstraintSet()
            constSet.clone(binding.parentConstraint)

            //Set height of button
            constSet.connect(curButton.id, ConstraintSet.TOP, prevAboveView.id, ConstraintSet.BOTTOM, 0) //Current top -> prevTop Bottom
            if(prevButton == null){
                //Put button to left of screen
                constSet.connect(binding.parentConstraint.id, ConstraintSet.LEFT, curButton.id, ConstraintSet.RIGHT, 0)
            }
            else{
                //Sets up chain links
                constSet.connect(prevButton.id, ConstraintSet.RIGHT, curButton.id, ConstraintSet.LEFT, pixelsToDisplayPixels(20))
            }

            //Set final one to the right of the screen
            if(counter % 4 == 3){
                constSet.connect(curButton.id, ConstraintSet.RIGHT, binding.parentConstraint.id, ConstraintSet.RIGHT, 0)
            }

            //Go down a row
            counter = (counter + 1)
            if(counter % 4 == 0){
                prevButton = null
                prevAboveView = curButton
            }else{
                prevButton = curButton
            }
            constSet.applyTo(binding.parentConstraint)
        }
        println("__________________ " + counter + " buttons created")
    }

    private fun pixelsToDisplayPixels(pix : Int) : Int{
        val scale : Float = requireContext().resources.displayMetrics.density
        return (pix * scale * 0.5f).toInt()
    }

}