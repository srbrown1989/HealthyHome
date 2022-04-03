package com.example.android.healthyhome.database.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.healthyhome.R
import org.jetbrains.anko.find

class ReviewsAdapter(private val reviews: MutableList<ReviewsItem>) : RecyclerView.Adapter<ReviewsAdapter.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.reviews_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review = reviews[position]
        holder.firstName.text = review.firstName
        holder.rating.rating = Integer.valueOf(review.rating).toFloat()
        holder.review.text = review.review

    }

    override fun getItemCount(): Int = reviews.size

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val firstName : TextView = itemView.find(R.id.review_firstname_textview)
        val rating : RatingBar = itemView.find(R.id.review_rating_bar)
        val review: TextView = itemView.find(R.id.review_TextView)

    }
}