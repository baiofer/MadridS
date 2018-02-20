package com.jarzasa.madridshops.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jarzasa.madridshops.R
import com.jarzasa.madridshops.domain.model.Shop
import com.jarzasa.madridshops.utils.INTENT_SHOP_DETAIL
import kotlinx.android.synthetic.main.activity_shop_detail.*

class ShopDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_detail)

        //Recoger la tienda
        val shop = intent.getSerializableExtra(INTENT_SHOP_DETAIL) as Shop

        textView2.text = shop.name
    }
}
