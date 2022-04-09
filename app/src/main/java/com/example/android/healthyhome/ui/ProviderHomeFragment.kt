package com.example.android.healthyhome.ui
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.android.healthyhome.R
import com.example.android.healthyhome.database.Provider
import com.example.android.healthyhome.database.util.Common
import com.example.android.healthyhome.database.util.IMyAPI
import com.example.android.healthyhome.databinding.FragmentProviderHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.android.healthyhome.database.LoginResponse
import com.example.android.healthyhome.database.util.Bookings
import com.example.android.healthyhome.database.util.BookingsItem
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProviderHomeFragment : Fragment() {

    private lateinit var binding : FragmentProviderHomeBinding
    private lateinit var mService: IMyAPI
    private lateinit var navController: NavController
    private lateinit var bookings : Bookings


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
// Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentProviderHomeBinding>(
            inflater, R.layout.fragment_provider_home, container, false
        )

        navController = findNavController()

        mService = Common.getAPI()
        if (Common.currentProvider != null){
        binding.companyNameTextView.text = Common.currentProvider.companyName
        }

        binding.bioCardView.setOnClickListener {
            navController.navigate(ProviderHomeFragmentDirections.actionProviderHomeFragmentToProviderChangeBioFragment())

        }
        
        binding.bookingsCardView.setOnClickListener {
          getBookings()

        }

        binding.customerHomeButton.setOnClickListener {
            navController.navigate(ProviderHomeFragmentDirections.actionProviderHomeFragmentToCustomerHomeFragment())
        }

        binding.findJobCardview.setOnClickListener{
            navController.navigate(ProviderHomeFragmentDirections.actionProviderHomeFragmentToFindJobFragment())
        }

        binding.userReviewsCardview.setOnClickListener{
            navController.navigate(ProviderHomeFragmentDirections.actionProviderHomeFragmentToProviderReviewsFragment())
        }







        //TODO: IMPLEMENT GETTING INFORMATION FROM LOGGED IN PROVIDER.




        return binding.root    }

    private fun getBookings() {
            mService.getAllBookings("2").enqueue(object: Callback<Bookings>{
               override fun onResponse(call: Call<Bookings>, response: Response<Bookings>) {
                   var result : Bookings? = response.body()
                   bookings = result!!
                   navController.navigate(ProviderHomeFragmentDirections.actionProviderHomeFragmentToProviderBookingsFragment(bookings))
               }

               override fun onFailure(call: Call<Bookings>, t: Throwable) {
                   TODO("Not yet implemented")
               }

           })
        d("here","getbookings")
    }

    private fun fillProviderInfo() {}


}
