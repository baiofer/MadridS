package com.jarzasa.madridshops.domain.interactors

import com.jarzasa.madridshops.domain.model.Shop
import com.jarzasa.madridshops.domain.model.Shops

class GetAllShopsInteractorFakeImpl: GetAllShopsInteractor {

    //Forma mas Java de implementación
    override fun execute(success: SuccessCompletion<Shops>,
                         error: ErrorCompletion) {
        var allOK = true

        //Connect to the Repository

        if (allOK) {
            val shops = createFakeListOfShops()
            success.successCompletion(shops)
        } else {
            error.errorCompletion("Error while accessing the Repository")
        }
    }

    //Forma mas Kottlin de implementación
    fun execute(success: SuccessClosure, error: ErrorClosure) {
        var allOK = true

        //Connect to the Repository

        if (allOK) {
            val shops = createFakeListOfShops()
            success(shops)
        } else {
            error("Error while accessing the Repository")
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