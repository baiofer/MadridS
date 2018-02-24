package com.jarzasa.madridshops.activity

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.jarzasa.madridshops.R
import com.jarzasa.madridshops.domain.interactors.deleteallactivities.DeleteAllActivitiesInteractorImpl
import com.jarzasa.madridshops.domain.interactors.deleteallshops.DeleteAllShopsInteractorImpl
import com.jarzasa.madridshops.domain.interactors.internetstatus.InternetStatusInteractorImpl
import com.jarzasa.madridshops.router.Router
import kotlinx.android.synthetic.main.activity_entrance.*

class EntranceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrance)

        disableButtons()
        checkInternetStatus()
    }

    private fun checkInternetStatus() {
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
                                //checkInternetStatus()
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

    private fun deleteAll() {
        userMessages.text = getString(R.string.deleting_cache)
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
