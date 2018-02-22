package com.jarzasa.madridshops.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jarzasa.madridshops.R
import com.jarzasa.madridshops.domain.model.Activities
import com.jarzasa.madridshops.domain.model.Activity
import com.squareup.picasso.Picasso

class ActivitiesRecyclerAdapter(val activities: Activities): RecyclerView.Adapter<ActivitiesRecyclerAdapter.ActivityViewHolder>() {

    var onClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ActivityViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_cell, parent, false)

        view.setOnClickListener(onClickListener)

        return ActivityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return activities.count()
    }

    override fun onBindViewHolder(holder: ActivityViewHolder?, position: Int) {
        holder?.bindActivity(activities.get(position))
    }

    inner class ActivityViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val itemImage = itemView.findViewById<ImageView>(R.id.item_image)
        val itemName = itemView.findViewById<TextView>(R.id.item_name)
        val itemHours = itemView.findViewById<TextView>(R.id.item_hours)

        fun bindActivity(activity: Activity) {
            itemName.text = activity.name
            itemHours.text = activity.openingHours
            Picasso
                    .with(itemView.context)
                    .load(activity.image)
                    .placeholder(R.drawable.madrid)
                    .into(itemImage)
        }
    }
}