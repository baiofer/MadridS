package com.jarzasa.madridshops.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.jarzasa.madridshops.R
import com.jarzasa.madridshops.domain.interactors.deleteallactivities.DeleteAllActivitiesInteractorImpl
import com.jarzasa.madridshops.domain.interactors.deleteallshops.DeleteAllShopsInteractorImpl
import com.jarzasa.madridshops.domain.interactors.getallactivities.GetAllActivitiesInteractorImpl
import com.jarzasa.madridshops.domain.interactors.getallshops.GetAllShopsInteractorImpl
import com.jarzasa.madridshops.domain.interactors.internetstatus.InternetStatusInteractorImpl
import com.jarzasa.madridshops.domain.model.Activities
import com.jarzasa.madridshops.domain.model.Shops
import com.jarzasa.madridshops.navigation.Router
import com.jarzasa.madridshops.utils.ErrorCompletion
import com.jarzasa.madridshops.utils.SuccessCompletion
import kotlinx.android.synthetic.main.activity_entrance.*

class EntranceActivity : AppCompatActivity() {

    companion object {
        fun intent(context: Context) = Intent(context, EntranceActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrance)

        disableButtons()
        checkInternetStatus()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun checkInternetStatus() {
        InternetStatusInteractorImpl().execute(
                success = {
                    enableButtons()
                    activeButtons()
                },
                error = {
                    AlertDialog.Builder(this)
                            .setTitle("ERROR")
                            .setMessage(it)
                            .setPositiveButton("Intentar", { dialog, witch ->
                                dialog.dismiss()
                                checkInternetStatus()
                            })
                            .setNegativeButton("Salir",  { dialog, which ->
                                finish()
                            })
                            .show()
                })
    }

    private fun disableButtons() {
        shop_button.visibility = View.INVISIBLE
        activity_button.visibility = View.INVISIBLE
        delete_button.visibility = View.INVISIBLE
    }

    private fun enableButtons() {
        shop_button.visibility = View.VISIBLE
        activity_button.visibility = View.VISIBLE
        delete_button.visibility = View.VISIBLE
    }

    private fun activeButtons() {

        shop_button.setOnClickListener {
            Router().navigateFromEntranceToShops(this)
        }

        activity_button.setOnClickListener {
            Router().navigateFromEntranceToActivities(this)
        }

        delete_button.setOnClickListener {
            deleteAll()
        }
    }

    fun deleteAll() {

        userMessages.text = "Borrando Cache"
        //Delete All
        DeleteAllShopsInteractorImpl(this).execute(success = {
            Log.d("SUCCESS", "ALL SHOPS DELETED")
        }, error = {
            Log.d("ERROR", "ERROR DELETING SHOPS")
        })

        DeleteAllActivitiesInteractorImpl(this).execute(success = {
            Log.d("SUCCESS", "ALL ACTIVITIES DELETED")
        }, error = {
            Log.d("ERROR", "ERROR DELETING ACTIVITIES")
        })

        userMessages.text = ""
    }
}
