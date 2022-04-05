package com.example.android.healthyhome.database.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.healthyhome.R
import org.jetbrains.anko.find

class ResponsesAdapter (private val responses : MutableList<ResponsesItem>) : RecyclerView.Adapter<ResponsesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.responses_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val response = responses[position]
        holder.message.text = response.msg
        holder.name.text = response.companyName

    }

    override fun getItemCount(): Int = responses.size

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val name : TextView = itemView.find(R.id.jr_companyName_textview)
        val message : TextView = itemView.find(R.id.jr_message_textview)

    }
}