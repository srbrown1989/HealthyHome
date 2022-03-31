package com.example.android.healthyhome.ui
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProviderHomeFragment : Fragment() {

    private lateinit var binding : FragmentProviderHomeBinding
    private lateinit var database : DatabaseReference
    private lateinit var mService: IMyAPI
    private lateinit var navController: NavController


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

        binding.bioCardView.setOnClickListener {
            navController.navigate(ProviderHomeFragmentDirections.actionProviderHomeFragmentToProviderChangeBioFragment())

        }




        mService = Common.getAPI()

        fillProviderInfo()


        return binding.root    }

    private fun fillProviderInfo() {
        database.child(FirebaseAuth.getInstance().uid!!).get().addOnSuccessListener{
            val provider : Provider? = it.getValue(Provider::class.java)
            binding.companyNameTextView.text = provider?.providerName.toString()
            binding.ratingBar.rating = provider?.rating?.toFloat()!!


        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

    }


}