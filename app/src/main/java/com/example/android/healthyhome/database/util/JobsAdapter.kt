package com.example.android.healthyhome.database.util

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.healthyhome.R
import org.jetbrains.anko.find

class JobsAdapter(private val jobs : MutableList<JobsItem>) : RecyclerView.Adapter<JobsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.jobs_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val job = jobs[position]
        holder.service.text = job.service
        holder.message.text = job.msg
    }

    override fun getItemCount(): Int = jobs.size

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val service : TextView = itemView.find(R.id.j_service_textview)
        val message : TextView = itemView.find(R.id.j_message_textview)


    }
}