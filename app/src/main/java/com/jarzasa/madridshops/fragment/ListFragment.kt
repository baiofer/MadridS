package com.jarzasa.madridshops.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jarzasa.madridshops.R
import com.jarzasa.madridshops.adapters.ShopsRecyclerViewAdapter
import com.jarzasa.madridshops.domain.model.Shop
import com.jarzasa.madridshops.domain.model.Shops
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private var items: Shops? = null

    fun setItems(items: Shops) {
        this.items = items
        Log.d("ListFragment", items.count().toString())
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater!!.inflate(R.layout.fragment_list, container, false)

        //Creamos el RecyclerView con la lista de tiendas
        //Montamos el RecyclerView
        // Le decimos su LayoutManager
        shops_list.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        // Le decimos cómo se anima
        shops_list.itemAnimator = DefaultItemAnimator()
        // Creamos el adapter
        val adapter = ShopsRecyclerViewAdapter(activity, items as MutableList<Shop>)

        // Le decimos su adapter
        shops_list.adapter = adapter

        return root
    }

}// Required empty public constructor
