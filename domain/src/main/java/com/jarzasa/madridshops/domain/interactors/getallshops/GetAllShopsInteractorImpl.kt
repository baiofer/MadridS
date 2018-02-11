package com.jarzasa.madridshops.domain.interactors.getallshops

import android.content.Context
import android.util.Log
import com.jarzasa.madridshops.domain.model.Shop
import com.jarzasa.madridshops.domain.model.Shops
import com.jarzasa.madridshops.repository.Repository
import com.jarzasa.madridshops.repository.RepositoryImpl
import com.jarzasa.madridshops.repository.model.ShopEntity
import com.jarzasa.madridshops.utils.ErrorCompletion
import com.jarzasa.madridshops.utils.SuccessCompletion
import java.lang.ref.WeakReference

class GetAllShopsInteractorImpl(context: Context): GetAllShopsInteractor {
    private val weakContext = WeakReference<Context>(context)
    private val repository: Repository = RepositoryImpl(weakContext.get()!!)

    override fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion) {
        repository.getAllShops(
                success = {
                    val shops: Shops = entityMapper(it)
                    success.successCompletion(shops)
                }, error = {
                    error(it)
                })
    }

    private fun entityMapper(list: List<ShopEntity>): Shops {
        val tempList = ArrayList<Shop>()
        list.forEach {
            var lat: Float = 40.4167f
            var lon: Float = -3.70325f
            try {
                lat = it.gps_lat.toFloat()
                lon = it.gps_lon.toFloat()
            } catch (e: Exception) {
                //Log.d("MAL", it.name)
            }
            val shop = Shop(it.id.toInt(),
                    it.name,
                    it.description_es,
                    lat,
                    lon,
                    it.img,
                    it.logo_img,
                    it.opening_hours_es,
                    it.address)
            tempList.add(shop)
        }
        val shops = Shops(tempList)
        return  shops
    }
}