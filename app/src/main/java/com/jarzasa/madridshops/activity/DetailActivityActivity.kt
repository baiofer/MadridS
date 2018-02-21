package com.jarzasa.madridshops.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.jarzasa.madridshops.R
import com.jarzasa.madridshops.domain.model.Activity
import com.jarzasa.madridshops.utils.INTENT_ACTIVITY_DETAIL
import kotlinx.android.synthetic.main.activity_detail_activity.*

class DetailActivityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_activity)

        //Recoger la tienda
        val activity = intent.getSerializableExtra(INTENT_ACTIVITY_DETAIL) as Activity

        textView2.text = activity.name
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
