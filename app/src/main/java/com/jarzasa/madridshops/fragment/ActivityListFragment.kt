package com.jarzasa.madridshops.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jarzasa.madridshops.R
import com.jarzasa.madridshops.domain.model.Activities

class ActivityListFragment : Fragment() {

    private var items: Activities? = null

    fun setItems(items: Activities) {
        this.items = items
        Log.d("ActivityListFragment", items.count().toString())
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_list, container, false)
    }

}// Required empty public constructor
