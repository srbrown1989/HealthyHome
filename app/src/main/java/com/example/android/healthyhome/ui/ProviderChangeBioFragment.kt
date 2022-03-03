package com.example.android.healthyhome.ui

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.healthyhome.R
import com.example.android.healthyhome.databinding.FragmentProviderSignUpBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ProviderChangeBioFragment : Fragment() {
    private lateinit var binding: FragmentProviderSignUpBinding
    private lateinit var database: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentProviderSignUpBinding>(
            inflater, R.layout.fragment_provider_sign_up, container, false
        )
        database = FirebaseDatabase.getInstance().reference.child("Provider")

        binding.finishButton.setOnClickListener {
            it.findNavController().navigate(ProviderSignUpFragmentDirections.actionProviderSignUpFragmentToProviderHomeFragment())

        }

        binding.backButton.setOnClickListener {
            it.findNavController().navigate(ProviderSignUpFragmentDirections.actionProviderSignUpFragmentToServicesFragment())
        }


        return binding.root
    }

}


//fun updateById($pid: Long, $Bio: String, $price: Int, $contact: String, $companyEmail: String ): Int
//{
    //val data = ContentValues()
    //data.put(COLUMN_USERNAME, $Bio)
    //data.put(COLUMN_PRICE, $price)
    //data.put(COLUMN_EMAIL, $companyEmail)
    //data.put(COLUMN_CONTACT, $contact)
    //val whereclause = "$COLUMN_PID=?"
    //val whereargs = arrayOf(pid.toString())
  //  return this.writableDatabase.update(TABLE_NAME, data, whereclause, whereargs)
//}