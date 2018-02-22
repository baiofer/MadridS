package com.jarzasa.madridshops.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jarzasa.madridshops.R
import com.jarzasa.madridshops.adapters.ShopsRecyclerAdapter
import com.jarzasa.madridshops.domain.model.Shops

class ListFragment : Fragment() {

    private var items: Shops? = null
    private lateinit var root: View
    private lateinit var shopsRecycler: RecyclerView

    fun setItems(items: Shops) {
        this.items = items
        Log.d("ListFragment", items.count().toString())
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        root = inflater!!.inflate(R.layout.fragment_list, container, false)


        return root
    }

}// Required empty public constructor
