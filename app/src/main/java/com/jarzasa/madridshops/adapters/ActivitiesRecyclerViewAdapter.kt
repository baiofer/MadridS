package com.jarzasa.madridshops.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jarzasa.madridshops.R
import com.jarzasa.madridshops.domain.model.Activity
import com.squareup.picasso.Picasso
import java.lang.ref.WeakReference

//Adapter del RecyclerView de la lista de actividades (Activities)
class ActivitiesRecyclerViewAdapter(var context: Context, private var activities: MutableList<Activity>?): RecyclerView.Adapter<ActivitiesRecyclerViewAdapter.ActivityViewHolder>() {

    private var weakContext = WeakReference<Context>(context)
    lateinit var view: View
    private var onClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ActivitiesRecyclerViewAdapter.ActivityViewHolder {
        view = LayoutInflater.from(parent?.context).inflate(R.layout.cell_shop, parent, false)
        view.setOnClickListener(onClickListener)
        return ActivityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return activities?.count() ?: 0
    }

    override fun onBindViewHolder(holder: ActivityViewHolder?, position: Int) {
        holder?.bindActivity(activities?.get(position))
    }


    inner class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val nameActivity = itemView.findViewById<TextView>(R.id.cell_name)
        private val openingHoursActivity = itemView.findViewById<TextView>(R.id.cell_opening_hours)
        private val imageActivity = itemView.findViewById<ImageView>(R.id.cell_image)

        fun bindActivity(activity: Activity?) {
            nameActivity.text = activity?.name ?: ""
            openingHoursActivity.text = activity?.openingHours
            Picasso.with(weakContext.get()!!)
                    .load(activity?.image)
                    .placeholder(android.R.drawable.btn_radio)
                    .into(imageActivity)
        }
    }
}