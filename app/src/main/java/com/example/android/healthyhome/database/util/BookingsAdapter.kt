package com.example.android.healthyhome.database.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
        holder.bid.text = booking.bid
        holder.date.text = booking.date
        holder.time.text = booking.time
        holder.name.text = (booking.firstName + " " + booking.lastName)

    }

    override fun getItemCount(): Int = bookings.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bid : TextView = itemView.find(R.id.bid_textview)
        val name: TextView = itemView.find(R.id.name_textview)
        val date : TextView = itemView.find(R.id.date_textview)
        val time: TextView = itemView.find(R.id.time_textview)


    }

}

