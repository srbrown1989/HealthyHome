package com.example.android.healthyhome.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.ToggleButton
import androidx.constraintlayout.widget.ConstraintSet
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.android.healthyhome.R
import com.example.android.healthyhome.database.DBCalls
import com.example.android.healthyhome.database.mail.Mail
import com.example.android.healthyhome.databinding.FragmentFilterServicesBinding

class FilterServicesFragment : Fragment() {

    private lateinit var binding : FragmentFilterServicesBinding
    private var recurring : Boolean = false
    private var activeServices : MutableList<String> = mutableListOf();

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var args = FilterServicesFragmentArgs.fromBundle(requireArguments())
        var chosenProvider = args.pid
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

            val cid = Integer.parseInt(binding.cidTemp.text.toString()) //Filler//TODO: Common.currentUser.uid or db call to get cid.
            val pid = Integer.parseInt(chosenProvider) //Filler
            val numOfRooms = binding.radioGroup.checkedRadioButtonId + 1
            view.findNavController().navigate(FilterServicesFragmentDirections.actionFilterServicesFragmentToFilterDateFragment(pid, recurring, 1, activeServices.toTypedArray(), cid))
        }


        //pID taken from selected provider
        DBCalls.getServicesListFromProvider(chosenProvider){ rArray ->
            buildServiceButtons(rArray)
        }

        return binding.root
    }

    private fun buildServiceButtons(services : MutableList<String>){

        println(services.size)
        if(services.size == 0){
            binding.noServices.visibility = View.VISIBLE
        }else{
            while(services.size % 4 != 0){
                services.add("holder");
            }

            var prevButton : View? = null
            var prevAboveView : View = binding.radioGroup
            var counter = 0



            for(ser in services){
                val curButton = ToggleButton(this.requireContext())
                curButton.id = View.generateViewId()
                curButton.text = ser
                curButton.textOn = ser
                curButton.textOff = ser
                curButton.textSize = 11f
                curButton.width = pixelsToDisplayPixels(20)
                curButton.height = pixelsToDisplayPixels(150)
                if(ser != "holder") {
                    curButton.setOnCheckedChangeListener { _, isChecked ->
                        if (isChecked) {
                            activeServices.add(ser)
                        } else {
                            activeServices.remove(ser)
                        }
                        Toast.makeText(this.requireContext(),
                            activeServices.toString(),
                            Toast.LENGTH_SHORT).show()
                    }
                }else{
                    curButton.visibility = View.INVISIBLE;
                }

                binding.parentConstraint.addView(curButton)
                var constSet = ConstraintSet()
                constSet.clone(binding.parentConstraint)

                //Set height of button
                constSet.connect(curButton.id, ConstraintSet.TOP, prevAboveView.id, ConstraintSet.BOTTOM, 0) //Current top -> prevTop Bottom
                if(prevButton == null){
                    //Put button to left of screen
                    constSet.connect(binding.parentConstraint.id, ConstraintSet.LEFT, curButton.id, ConstraintSet.RIGHT, pixelsToDisplayPixels(0))
                }
                else{
                    //Sets up chain links
                    constSet.connect(prevButton.id, ConstraintSet.RIGHT, curButton.id, ConstraintSet.LEFT, pixelsToDisplayPixels(0))
                }

                //Set final one to the right of the screen
                if(counter % 4 == 3){
                    constSet.connect(curButton.id, ConstraintSet.RIGHT, binding.parentConstraint.id, ConstraintSet.RIGHT, pixelsToDisplayPixels(0))
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
            println("__________________ " + counter + " extras provided with " + counter + " buttons created for them")
        }


    }

    private fun pixelsToDisplayPixels(pix : Int) : Int{
        val scale : Float = requireContext().resources.displayMetrics.density
        return (pix * scale * 0.5f).toInt()
    }

}