package com.android.campaign.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.campaign.R
import com.android.campaign.data.model.Campaign
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.counter.view.*
import kotlinx.android.synthetic.main.item_banner.view.*
import kotlinx.android.synthetic.main.item_hot_deal.view.*


class MainAdapter(private val set: ArrayList<Campaign>) : androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(viewType, parent, false)
        var viewHolder : androidx.recyclerview.widget.RecyclerView.ViewHolder

        if(viewType == R.layout.item_hot_deal){
            viewHolder = CounterViewHolder(view)
        }
        else {
            viewHolder = ViewHolder(view)

        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return  set!!.size
    }

    override fun getItemViewType(position: Int): Int {
        //set row type
        if(set[position].hotDeal != null) {
            return  R.layout.item_hot_deal
        }else{
            return  R.layout.item_banner
        }

    }

    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        if(set[position].hotDeal != null) {

            val holder = holder as CounterViewHolder
            if(holder?.counter?.counterTime?.text == ""){
                holder?.counter?.initializeCounter(set[position].hotDeal?.title, set[position].hotDeal?.expirationDate)
            }
            holder.setIsRecyclable(false)

        }else {
            val holder = holder as ViewHolder
            Picasso.get().load(set[position].banner?.image?.url).into(holder?.imgBanner)
            holder.setIsRecyclable(false)
        }
    }

    class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        val imgBanner = itemView.imgBanner
    }
    class CounterViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        val counter = itemView.counter
    }

    fun addCampaing(campaigns: ArrayList<Campaign>){
        set?.addAll(campaigns)
        notifyDataSetChanged()
    }

}