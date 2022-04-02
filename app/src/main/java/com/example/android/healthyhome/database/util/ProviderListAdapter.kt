package com.example.android.healthyhome.database.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.healthyhome.R

class ProviderListAdapter(
    private val providerList: Providers,
    private val listener: OnItemClickListener
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
    override fun onBindViewHolder(holder: ProviderViewHolder, position: Int) {
        val currentItem = providerList[position]
        holder.textView1.text = currentItem.companyName
        holder.textView1.tag = currentItem.pid
        holder.textView2.text = currentItem.Bio
        holder.textView3.text = ("Rating: " + currentItem.rating.toString())
        holder.textView4.text = ("Price: " + currentItem.price.toString())
    }

    override fun getItemCount() = providerList.size

//    View Holder for views. vals are set to the view ids
    inner class ProviderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
View.OnClickListener{

        val textView1: TextView = itemView.findViewById(R.id.text_view_1)
        val textView2: TextView = itemView.findViewById(R.id.text_view_2)
        val textView3: TextView = itemView.findViewById(R.id.text_view_3)
        val textView4: TextView = itemView.findViewById(R.id.text_view_4)


    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
          val position = adapterPosition
        if(position != RecyclerView.NO_POSITION) {
            listener.onItemClick(position)
        }
    }


}
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}