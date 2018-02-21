package com.jarzasa.madridshops.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jarzasa.madridshops.R
import com.jarzasa.madridshops.domain.model.Shop
import com.squareup.picasso.Picasso
import java.lang.ref.WeakReference

//Adapter del RecyclerView de la lista de tiendas (Shops)
class ShopsRecyclerViewAdapter(val context: Context, private var shops: MutableList<Shop>?): RecyclerView.Adapter<ShopsRecyclerViewAdapter.ShopViewHolder>() {

    private var weakContext = WeakReference<Context>(context)
    lateinit var view: View
    private var onClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ShopsRecyclerViewAdapter.ShopViewHolder {
        view = LayoutInflater.from(parent?.context).inflate(R.layout.cell_shop, parent, false)
        view.setOnClickListener(onClickListener)
        return ShopViewHolder(view)
    }

    override fun getItemCount(): Int {
        return shops?.count() ?: 0
    }

    override fun onBindViewHolder(holder: ShopViewHolder?, position: Int) {
        holder?.bindShop(shops?.get(position))
    }


    inner class ShopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val nameShop = itemView.findViewById<TextView>(R.id.cell_name)
        private val openingHoursShop = itemView.findViewById<TextView>(R.id.cell_opening_hours)
        private val imageShop = itemView.findViewById<ImageView>(R.id.cell_image)

        fun bindShop(shop: Shop?) {
            nameShop.text = shop?.name ?: ""
            openingHoursShop.text = shop?.openingHours
            Picasso.with(weakContext.get()!!)
                    .load(shop?.image)
                    .placeholder(android.R.drawable.btn_radio)
                    .into(imageShop)
        }
    }
}