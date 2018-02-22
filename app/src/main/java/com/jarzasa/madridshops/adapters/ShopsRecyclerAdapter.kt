package com.jarzasa.madridshops.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jarzasa.madridshops.R
import com.jarzasa.madridshops.domain.model.Shop
import com.jarzasa.madridshops.domain.model.Shops
import com.squareup.picasso.Picasso


class ShopsRecyclerAdapter(val shops: Shops): RecyclerView.Adapter<ShopsRecyclerAdapter.ShopViewHolder>() {

    var onClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ShopViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_cell, parent, false)

        view.setOnClickListener(onClickListener)

        return ShopViewHolder(view)
    }

    override fun getItemCount(): Int {
        return shops.count()
    }

    override fun onBindViewHolder(holder: ShopViewHolder?, position: Int) {
        holder?.bindShop(shops.get(position))
    }

    inner class ShopViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val itemImage = itemView.findViewById<ImageView>(R.id.item_image)
        val itemName = itemView.findViewById<TextView>(R.id.item_name)
        val itemHours = itemView.findViewById<TextView>(R.id.item_hours)

        fun bindShop(shop: Shop) {
            itemName.text = shop.name
            itemHours.text = shop.openingHours
            Picasso
                    .with(itemView.context)
                    .load(shop.image)
                    .placeholder(R.drawable.madrid)
                    .into(itemImage)
        }
    }
}