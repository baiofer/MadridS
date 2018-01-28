package com.jarzasa.madridshops.domain.interactors

import com.jarzasa.madridshops.domain.model.Shop
import com.jarzasa.madridshops.domain.model.Shops

class GetAllShopsInteractorFakeImpl: GetAllShopsInteractor {
    override fun execute(success: SuccessCompletion<Shops>,
                         error: ErrorCompletion) {
        var allOK = false

        //Connect to the Repository

        if (allOK) {
            val shops = createFakeListOfShops()
            success.successCompletion(shops)
        } else {
            error.errorCompletion("Error while accessing the Repository")
        }
    }

    fun createFakeListOfShops(): Shops {
        val list = ArrayList<Shop>()
        for (i in 0..100) {
            val shop = Shop(i, "Shop " + i, "Address " + i)
            list.add(shop)
        }
        val shops = Shops(list)
        return shops
    }
}