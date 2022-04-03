package com.example.android.healthyhome.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.android.healthyhome.R
import com.example.android.healthyhome.database.LoginResponse
import com.example.android.healthyhome.database.Provider
import com.example.android.healthyhome.database.util.Common
import com.example.android.healthyhome.database.util.IMyAPI
import com.example.android.healthyhome.databinding.FragmentLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {


    private lateinit var binding: FragmentLoginBinding
    private lateinit var navController: NavController

    private lateinit var mService : IMyAPI





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater, R.layout.fragment_login, container, false
        )

        mService = Common.getAPI()






       // Register button.
        binding.buttonRegister.setOnClickListener {
            binding.confirmPasswordEditText.visibility = View.VISIBLE
            binding.passwordGuideTextView.visibility = View.VISIBLE
            binding.nameEditText.visibility = View.VISIBLE
            binding.buttonRegister.visibility = View.GONE
            binding.buttonLogin.visibility = View.GONE
            binding.svCustomerLogin.visibility = View.VISIBLE

            binding.svConfirmButton.setOnClickListener {
                registerIntent()
            }

        }

        //login button.

        binding.buttonLogin.setOnClickListener {
            loginIntent()
            //TODO: if user is provider, grab provider info

        }





        return binding.root
    }

    private fun loginIntent() {
        SignIn()
        //validateLoginForm()

    }

    private fun validateLoginForm() {
        val icon = AppCompatResources.getDrawable(
            requireContext(),
            R.drawable.ic_warning
        )

        icon?.setBounds(0, 0, icon.intrinsicWidth, icon.intrinsicHeight)
        when {
            TextUtils.isEmpty(binding.emailEditText.text.toString().trim()) -> {
                binding.emailEditText.setError("Please enter an email address", icon)
            }
            TextUtils.isEmpty(binding.passwordEditText.text.toString().trim()) -> {
                binding.passwordEditText.setError("Please enter your password", icon)
            }

            binding.emailEditText.text.toString().isNotEmpty() &&
                    binding.passwordEditText.toString().isNotEmpty() -> {

                if (binding.emailEditText.text.toString().matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))){
                    if (!binding.passwordEditText.text.toString().matches(Regex("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{8,}\$"))) {
                        binding.passwordEditText.setError("Invalid Password", icon)
                    } else {
                        SignIn()
                    }

                } else {
                    binding.emailEditText.setError("Please Enter a Valid Email ID", icon)
                }

                    }
        }
    }

    private fun SignIn() {
        mService.loginUser(binding.emailEditText.text.toString(),binding.passwordEditText.text.toString())
            .enqueue(object : Callback<LoginResponse>{
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    val result : LoginResponse? = response.body()
                    if (result?.error!!){
                        Toast.makeText(activity?.applicationContext,result.error_msg,Toast.LENGTH_SHORT).show()
                    } else {
                        Common.currentUser = response.body()!!.user
                        var currentUser2 = response.body()!!.user
                        Toast.makeText(activity?.applicationContext,"Logged in as " + response.body()!!.user.name,Toast.LENGTH_SHORT).show()

                        if (currentUser2.isProvider == 0){ //if not provider go to customer home.
                        navController.navigate(LoginFragmentDirections.actionLoginFragmentToCustomerHomeFragment());
                            } else if (currentUser2.isProvider == 1) {
                                mService.getProviderByID(currentUser2.uid.toString()).enqueue(object: Callback<Provider>{
                                    override fun onResponse(
                                        call: Call<Provider>,
                                        response: Response<Provider>
                                    ) {
                                        Common.currentProvider = response.body()
                                        navController.navigate(LoginFragmentDirections.actionLoginFragmentToProviderHomeFragment())
                                    }

                                    override fun onFailure(call: Call<Provider>, t: Throwable) {
                                        TODO("Not yet implemented")
                                    }

                                })

                            }
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(activity?.applicationContext, t.message, Toast.LENGTH_SHORT).show()
                }

            })


    }


    private fun registerIntent() {
        validateRegisterForm()


    }


    private fun validateRegisterForm(){
        val icon = AppCompatResources.getDrawable(requireContext(),
        R.drawable.ic_warning)

        icon?.setBounds(0,0,icon.intrinsicWidth,icon.intrinsicHeight)
        when {
            TextUtils.isEmpty(binding.emailEditText.text.toString().trim()) ->{
                binding.emailEditText.setError("Please enter an email address", icon)
            }
            TextUtils.isEmpty(binding.passwordEditText.text.toString().trim()) ->{
                binding.passwordEditText.setError("Please enter your password",icon)
            }
            TextUtils.isEmpty(binding.confirmPasswordEditText.text.toString().trim()) ->{
                binding.confirmPasswordEditText.setError("Please confirm your password",icon)
            }
            TextUtils.isEmpty(binding.nameEditText.text.toString().trim()) ->{
                binding.nameEditText.setError("Please enter your full name",icon)
            }

            binding.emailEditText.text.toString().isNotEmpty() &&
                    binding.passwordEditText.toString().isNotEmpty() &&
                    binding.confirmPasswordEditText.toString().isNotEmpty() &&
                    binding.nameEditText.toString().isNotEmpty() -> {

                        if (binding.emailEditText.text.toString().matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))){
                            if (binding.passwordEditText.text.toString().matches(Regex("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{8,}\$"))){
                                if(binding.passwordEditText.text.toString() == binding.confirmPasswordEditText.text.toString()){
                                    SignUp()


                                } else {
                                    binding.confirmPasswordEditText.setError("Passwords do not match", icon)
                                }

                            } else{
                                binding.passwordEditText.setError("must be 8 characters, and contain lowercase,uppercase,symbol", icon)

                            }

                        } else {
                            binding.emailEditText.setError("Please Enter a Valid Email ID", icon)
                        }
                    }
        }
    }

    private fun SignUp() {

        mService.registerUser(binding.nameEditText.text.toString(),binding.emailEditText.text.toString(),binding.passwordEditText.text.toString())
            .enqueue(object : Callback<LoginResponse>{
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    val result : LoginResponse? = response.body()
                    if (result?.error!!){
                        Toast.makeText(activity?.applicationContext,result.error_msg,Toast.LENGTH_SHORT).show()
                    } else {
                        Common.currentUser = response.body()!!.user
                        Toast.makeText(activity?.applicationContext,"Signed up as " + response.body()!!.user.name,Toast.LENGTH_SHORT).show()
                        navController.navigate(LoginFragmentDirections.actionLoginFragmentToCustomerHomeFragment());
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(activity?.applicationContext, t.message, Toast.LENGTH_SHORT).show()
                }

            })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
    }


}