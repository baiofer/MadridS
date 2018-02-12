package com.jarzasa.madridshops.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jarzasa.madridshops.R
import com.jarzasa.madridshops.domain.model.Shops


/**
 * A simple [Fragment] subclass.
 */
class MapFragment : Fragment() {

    private var shops: Shops? = null

    fun setShops(shops: Shops) {
        this.shops = shops
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_map, container, false)
    }

}// Required empty public constructor
