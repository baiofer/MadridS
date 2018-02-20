package com.jarzasa.madridshops.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jarzasa.madridshops.R
import com.jarzasa.madridshops.domain.model.Activity
import com.jarzasa.madridshops.utils.INTENT_ACTIVITY_DETAIL
import kotlinx.android.synthetic.main.activity_shop_detail.*

class DetailActivityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_activity)

        //Recoger la tienda
        val activity = intent.getSerializableExtra(INTENT_ACTIVITY_DETAIL) as Activity

        textView2.text = activity.name
    }
}
