package com.example.android.healthyhome.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.healthyhome.R
import com.example.android.healthyhome.databinding.ActivityProviderBookingsFragmentBinding
import com.example.android.healthyhome.databinding.FragmentProviderSignUpBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ProviderBookingsFragment : Fragment() {
    private lateinit var binding: ActivityProviderBookingsFragmentBinding
    private lateinit var database: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<ActivityProviderBookingsFragmentBinding>(
            inflater, R.layout.activity_provider_bookings_fragment, container, false
        )
        database = FirebaseDatabase.getInstance().reference.child("Provider")

      //  binding.finishButton.setOnClickListener {
            //it.findNavController().navigate(ProviderSignUpFragmentDirections.actionProviderSignUpFragmentToProviderHomeFragment())

     //   }


        return binding.root
    }

}

//fun providerTable(): String {
   // return "CREATE TABLE" +
     //       Db.tableclass.PROVIDER_BOOKINGS + "(" +
     //       Db.tableclass.COLUMN_ID + " INTEGER PRIMARY KEY, " +
     //       Db.tableclass.COLUMN_NAME + " TEXT NOT NULL, " +
     //       Db.tableclass.COLUMN_CLIENT + " TEXT NOT NULL, " +
     //       Db.tableclass.COLUMN_DAY + " TEXT NOT NULL, " +
     //       Db.tableclass.COLUMN_START_TIME + " TEXT NOT NULL, " +
     //       Db.tableclass.COLUMN_END_TIME + " TEXT NOT NULL, " +

          //  "UNIQUE (" +
      //      Db.tableclass.COLUMN_ID +
         //   ") ON CONFLICT REPLACE);"
//}
