package com.example.android.healthyhome.database.util

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.android.healthyhome.R
import org.jetbrains.anko.find

class BookingsAdapter(private val bookings: MutableList<BookingsItem>) : RecyclerView.Adapter<BookingsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bookings_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val booking = bookings[position]
        holder.booking.text = ("Booking : " + (position + 1))
        holder.bid.text = ("Booking ID: " + booking.bid)
        holder.date.text = Common.dateFormat(booking.date)
        holder.time.text = Common.timeFormat(booking.time)
        holder.address.text = ("Address: " + booking.address)
        holder.postcode.text = ("Postcode: " + booking.postcode)
        holder.custName.text = (booking.firstName + " " + booking.lastName)
        holder.providername.text = ("Provider: " + booking.companyName)

        holder.bookingsExtra.setOnClickListener{
            if (!booking.expanded) {
                holder.bookingsBody.visibility = View.VISIBLE
                holder.triangle.rotation = 180f
                booking.expanded = true
            } else {
                holder.bookingsBody.visibility = View.GONE
                holder.triangle.rotation = 0f
                booking.expanded = false;
            }
        }
    }


    override fun getItemCount(): Int = bookings.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val booking : TextView = itemView.find(R.id.tv_booking)
        val bid : TextView = itemView.find(R.id.tv_booking_id)
        val providername : TextView = itemView.find(R.id.tv_provider_name)
        val address : TextView = itemView.find(R.id.tv_booking_address)
        val postcode: TextView = itemView.find(R.id.tv_postcode)
        val custName: TextView = itemView.find(R.id.tv_booking_custName)
        val date : TextView = itemView.find(R.id.tv_booking_date)
        val time: TextView = itemView.find(R.id.tv_booking_time)

        val bookingsExtra : ConstraintLayout = itemView.find(R.id.booking_extra)
        val bookingsBody : ConstraintLayout = itemView.find(R.id.booking_body)
        val triangle : ImageView = itemView.find(R.id.triangle_image)


    }

}

