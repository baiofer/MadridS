package com.jarzasa.madridshops.repository

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.jarzasa.madridshops.repository.db.build
import com.jarzasa.madridshops.repository.db.dao.ShopDAO
import com.jarzasa.madridshops.repository.model.ShopEntity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class ShopDAOTests {

    private fun initializeDB(): ShopDAO {
        val appContext = InstrumentationRegistry.getTargetContext()
        val dbHelper = build(appContext, "mydb.sqlite", 1)

        val shop1 = ShopEntity(1, 1, "My shop1", "", "", "",
                "", "", "", "", "",
                "", "", "", "", "My description1", "", "",
                "", "", "", "1", "1", "",
                "", "", "", "", "", "",
                "", "")
        val shop2 = ShopEntity(1, 1, "My shop2", "", "", "",
                "", "", "", "", "",
                "", "", "", "", "My description2", "", "",
                "", "", "", "1", "1", "",
                "", "", "", "", "", "",
                "", "")
        val shopEntityDAO = ShopDAO(dbHelper)

        //DeleteAll
        shopEntityDAO.deleteAll()

        //Insert 2 shops
        shopEntityDAO.insert(shop1)
        shopEntityDAO.insert(shop2)

        return shopEntityDAO
    }

    @Test
    @Throws(Exception::class)
    fun given_valid_shopentity_it_gets_inserted_correctly() {

        val shopEntityDAO: ShopDAO = initializeDB()

        //DeleteAll
        shopEntityDAO.deleteAll()

        //Insert element
        val shop = ShopEntity(1, 1, "My shop1", "", "", "",
                "", "", "", "", "",
                "", "", "", "", "My description1", "", "",
                "", "", "", "1", "1", "",
                "", "", "", "", "", "",
                "", "")

        val id = shopEntityDAO.insert(shop)

        assertTrue(id > 0)
    }

    @Test
    @Throws(Exception::class)
    fun given_valid_db_whith_registers_deleteall_functions_properly() {

        val shopEntityDAO: ShopDAO = initializeDB()

        //Delete all shops
        shopEntityDAO.deleteAll()

        //Query all shops
        val shops = shopEntityDAO.query()

        assertTrue(shops.size == 0)
    }

    @Test
    @Throws(Exception::class)
    fun given_valid_db_whith_registers_query_functions_properly() {

        val shopEntityDAO: ShopDAO = initializeDB()

        //Query all shops
        val shops = shopEntityDAO.query()

        assertTrue(shops[0].name == "My shop1")
        assertTrue(shops[1].name == "My shop2")
    }

    @Test
    @Throws(Exception::class)
    fun given_valid_db_whith_registers_queryelement_functions_properly() {

        val shopEntityDAO: ShopDAO = initializeDB()

        //Gets databaseId from first register
        val shops = shopEntityDAO.query()
        val id = shops[1].databaseId

        //Query element
        val shop = shopEntityDAO.query(id)
        assertTrue(shop.name == "My shop2")
    }

    @Test
    @Throws(Exception::class)
    fun given_valid_db_whith_registers_deleteelement_functions_properly() {

        val shopEntityDAO: ShopDAO = initializeDB()

        //Gets databaseId from first register
        val shops = shopEntityDAO.query()
        val id = shops[1].databaseId

        //Delete element
        shopEntityDAO.delete(id)

        //Query all elements
        val newShops = shopEntityDAO.query()

        assertTrue(newShops.size == 1)
    }

    @Test
    @Throws(Exception::class)
    fun given_valid_db_whith_registers_updateelement_functions_properly() {

        val shopEntityDAO: ShopDAO = initializeDB()

        //Gets databaseId from first register
        val shops = shopEntityDAO.query()
        val id = shops[1].databaseId

        //Change name of the second register
        val updateShop = ShopEntity(id, 1, "My shop2 updated", "", "", "",
                "", "", "", "", "",
                "", "", "", "", "My description2", "", "",
                "", "", "", "1", "1", "",
                "", "", "", "", "", "",
                "", "")

        //Update shop
        shopEntityDAO.update(id, updateShop)

        //Test new name
        val newShops = shopEntityDAO.query()

        assertTrue(newShops.size == 2)
        assertTrue(newShops[1].name == "My shop2 updated")
    }


}
