package com.example.android.healthyhome.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.healthyhome.R
import com.example.android.healthyhome.database.Provider
import com.example.android.healthyhome.databinding.ProviderListItemRecyclerBinding

class ProviderRecyclerAdapter (

    val context: Context?, val dataSet: List<Provider>
        ) : RecyclerView.Adapter<ProviderRecyclerAdapter.ItemViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProviderRecyclerAdapter.ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.provider_list_item_recycler, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ProviderRecyclerAdapter.ItemViewHolder, position: Int) {
        val item = dataSet[position]
        holder.companyName.text = item.name.toString()
        holder.bio.text = item.bio.toString()
        holder.price.text = item.price.toString()
    }

    override fun getItemCount() = dataSet.size

    class ItemViewHolder (
        view : View
            ) : RecyclerView.ViewHolder(view){
                val companyName : TextView = view.findViewById(R.id.company_name_recycle)
                val bio : TextView = view.findViewById(R.id.provider_bio_recycle)
                val price : TextView = view.findViewById(R.id.provider_price_recycle)


    }
}