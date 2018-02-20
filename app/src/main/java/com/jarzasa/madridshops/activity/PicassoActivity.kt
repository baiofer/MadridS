package com.jarzasa.madridshops.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jarzasa.madridshops.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_picasso.*

class PicassoActivity : AppCompatActivity() {

    companion object {
        fun intent(context: Context) = Intent(context, PicassoActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picasso)

        Picasso.with(this)
                .load("https://orig00.deviantart.net/e5e9/f/2012/136/b/c/shark_png_by_lg_design-d4zyhto.png")
                .placeholder(android.R.drawable.btn_radio)
                .into(img1)

        Picasso.with(this)
                .load("https://orig00.deviantart.net/463f/f/2012/137/5/5/bob_esponja_png___sponge_bob_png_by_tuutiifull-d505gmh.png")
                .placeholder(android.R.drawable.btn_dialog)
                .into(img2)

        Picasso.with(this)
                .load("http://imagenpng.com/wp-content/uploads/2015/09/87374826f991a03fd2723ad99a7db8ffo.png")
                .placeholder(android.R.drawable.btn_star_big_off)
                .into(img3)
    }
}
