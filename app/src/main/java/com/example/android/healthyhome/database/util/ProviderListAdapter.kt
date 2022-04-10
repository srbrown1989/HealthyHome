package com.example.android.healthyhome.database.util

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.android.healthyhome.R
import com.example.android.healthyhome.ui.ProviderListFragmentDirections
import com.firebase.ui.auth.AuthUI.getApplicationContext
import org.jetbrains.anko.find

class ProviderListAdapter(
    private val providerList: Providers
    ) :
    RecyclerView.Adapter<ProviderListAdapter.ProviderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProviderViewHolder {

        val itemView = LayoutInflater.from(parent.context)
            .inflate(
            R.layout.provider_list_item,
            parent,
            false)
        return ProviderViewHolder(itemView)
    }
//   holder vals come from ProviderViewHolder
    @SuppressLint("RestrictedApi")
    override fun onBindViewHolder(holder: ProviderViewHolder, position: Int) {
        val currentItem = providerList[position]

        holder.provName.text = currentItem.companyName
        holder.rating.rating = currentItem.rating.toFloat()
        holder.service.text = ("Service: " + currentItem.service)
        holder.email.text = ("Email: " + currentItem.companyEmail)
        holder.address.text = currentItem.address
        holder.bio.text = currentItem.Bio
        holder.postcode.text = ("Postcode: " + currentItem.postcode)
        holder.price.text = currentItem.price
        holder.contact.text = ("Phone: " + currentItem.contact)
        holder.URButton.setOnClickListener {
            Toast.makeText(getApplicationContext(), "Proceed to user reviews", Toast.LENGTH_SHORT).show() //dodgy call to getApplicationContext.

        }
        holder.bookingButton.setOnClickListener {
            val nav : NavController = it.findNavController();
            nav.navigate(ProviderListFragmentDirections.actionProviderListFragmentToFilterServicesFragment(currentItem.pid.toString()))

        }

    holder.plExtra.setOnClickListener{
        if (!currentItem.expanded) {
            holder.plBody.visibility = View.VISIBLE
            holder.triangle.rotation = 180f
            currentItem.expanded = true
        } else {
            holder.plBody.visibility = View.GONE
            holder.triangle.rotation = 0f
            currentItem.expanded = false;
        }
    }

    }

    override fun getItemCount() = providerList.size

//    View Holder for views. vals are set to the view ids
    inner class ProviderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val provName : TextView = itemView.find(R.id.pl_companyName)
    val service : TextView = itemView.find(R.id.pl_service)
    val rating : RatingBar = itemView.find(R.id.pl_rating_bar)
    val email : TextView = itemView.find(R.id.pl_email)
    val address : TextView = itemView.find(R.id.pl_address)
    val postcode : TextView = itemView.find(R.id.pl_postcode)
    val price : TextView = itemView.find(R.id.pl_price)
    val contact : TextView = itemView.find(R.id.pl_contact)
    val bio : TextView = itemView.find(R.id.pl_bio)

    val plExtra : ConstraintLayout = itemView.find(R.id.pl_extra)
    val plBody : ConstraintLayout = itemView.find(R.id.pl_body)
    val bookingButton : Button = itemView.find(R.id.pl_booking_button)
    val URButton : Button = itemView.find(R.id.pl_ur_button)
    val triangle : ImageView = itemView.find(R.id.pl_triangle_image)




}




}