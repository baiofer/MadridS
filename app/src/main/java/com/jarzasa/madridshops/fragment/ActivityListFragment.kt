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
import com.jarzasa.madridshops.adapters.ActivitiesRecyclerViewAdapter
import com.jarzasa.madridshops.domain.model.Activities
import com.jarzasa.madridshops.domain.model.Activity
import kotlinx.android.synthetic.main.fragment_activity_list.*

class ActivityListFragment : Fragment() {

    private var items: Activities? = null

    fun setItems(items: Activities) {
        this.items = items
        Log.d("ActivityListFragment", items.count().toString())
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater!!.inflate(R.layout.fragment_activity_list, container, false)


        return root
    }

}// Required empty public constructor
