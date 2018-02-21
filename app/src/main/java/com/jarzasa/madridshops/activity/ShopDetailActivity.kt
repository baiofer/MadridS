package com.jarzasa.madridshops.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.jarzasa.madridshops.R
import com.jarzasa.madridshops.domain.model.Shop
import com.jarzasa.madridshops.utils.INTENT_SHOP_DETAIL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_shop_detail.*

class ShopDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_detail)

        //Recoger la tienda
        val shop = intent.getSerializableExtra(INTENT_SHOP_DETAIL) as Shop

        shop_detail_text_view.text = shop.name
        Picasso.with(this)
                .load(shop.image)
                .placeholder(android.R.drawable.btn_radio)
                .into(shop_detail_image)

        //Activo el botón de back de la barra
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    //Si pulsan el botón Back, salgo de la actividad sin mas y vuelvo a la anterior
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
