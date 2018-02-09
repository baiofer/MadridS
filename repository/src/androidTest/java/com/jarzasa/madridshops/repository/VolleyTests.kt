package com.jarzasa.madridshops.repository

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.jarzasa.madridshops.repository.network.GetJsonManager
import com.jarzasa.madridshops.repository.network.GetJsonManagerVolleyImpl
import com.jarzasa.madridshops.utils.ErrorCompletion
import com.jarzasa.madridshops.utils.SuccessCompletion
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VolleyTests {

    @Test
    @Throws(Exception::class)
    fun given_valid_url_we_get_non_null_json_as_string() {

        val appContext = InstrumentationRegistry.getTargetContext()
        val url = "http://madrid-shops.com/json_new/getShops.php"

        val jsonManager: GetJsonManager = GetJsonManagerVolleyImpl(appContext)
        jsonManager.execute(url,
                success = object: SuccessCompletion<String> {
                    override fun successCompletion(e: String) {
                        assertTrue(e.isNotEmpty())
                    }
                },
                error = object: ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                        Log.d("ERROR", errorMessage)
                        assertTrue(false)
                    }
        })
    }
}
