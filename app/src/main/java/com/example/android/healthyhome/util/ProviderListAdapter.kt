package com.example.android.healthyhome.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.healthyhome.R
import com.example.android.healthyhome.database.Provider

class ProviderListAdapter(private val providerList: List<Provider>) : RecyclerView.Adapter<ProviderListAdapter.ProviderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProviderViewHolder {

        val itemView = LayoutInflater.from(parent.context)
            .inflate(
            R.layout.provider_list_item,
            parent,
            false)
        return ProviderViewHolder(itemView)
    }
//   holder vals come from ProviderViewHolder
    override fun onBindViewHolder(holder: ProviderViewHolder, position: Int) {
        val currentItem = providerList[position]
        holder.textView1.text = currentItem.providerName
        holder.textView2.text = currentItem.bio
        holder.textView3.text = "Rating: " + currentItem.rating.toString()
        holder.textView4.text = "Price: " + currentItem.price.toString()
    }

    override fun getItemCount() = providerList.size

//    View Holder for views. vals are set to the view ids
    class ProviderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val textView1: TextView = itemView.findViewById(R.id.text_view_1)
        val textView2: TextView = itemView.findViewById(R.id.text_view_2)
        val textView3: TextView = itemView.findViewById(R.id.text_view_3)
        val textView4: TextView = itemView.findViewById(R.id.text_view_4)

    }
}