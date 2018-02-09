package com.jarzasa.madridshops.repository

import com.jarzasa.madridshops.repository.db.convert
import com.jarzasa.madridshops.repository.model.ActivitiesResponseEntity
import com.jarzasa.madridshops.repository.model.ActivityEntity
import com.jarzasa.madridshops.repository.model.ShopEntity
import com.jarzasa.madridshops.repository.model.ShopsResponseEntity
import com.jarzasa.madridshops.repository.network.json.JsonEntitiesParser
import com.jarzasa.madridshops.repository.util.ReadJsonFile
import org.junit.Test

import org.junit.Assert.*

class JsonParsingTests {
    @Test
    @Throws(Exception::class)
    fun given_valid_string_containing_json_it_parses_one_shop_correctly() {

        val shopsJson = ReadJsonFile().loadJSONFromAsset("Shops1Tienda.json")

        assertTrue(shopsJson.isNotEmpty())

        // shopsJson is not empty. Now we parsing the file
        val parser = JsonEntitiesParser()
        val shop = parser.parse<ShopEntity>(shopsJson)

        assertNotNull(shop)
        assertEquals("Cortefiel - Preciados", shop.name)
    }

    @Test
    @Throws(Exception::class)
    fun given_valid_string_containing_json_it_parses_activity_correctly() {

        val activitiesJson = ReadJsonFile().loadJSONFromAsset("Activities1Actividad.json")

        assertTrue(activitiesJson.isNotEmpty())

        // shopsJson is not empty. Now we parsing the file
        val parser = JsonEntitiesParser()
        val activity = parser.parse<ShopEntity>(activitiesJson)

        assertNotNull(activity)
        assertEquals("Tour del Bernabéu", activity.name)
    }

    @Test
    @Throws(Exception::class)
    fun given_valid_string_containing_json_it_parses_shops_correctly() {

        val shopsJson = ReadJsonFile().loadJSONFromAsset("Shops3Tiendas.json")

        assertTrue(shopsJson.isNotEmpty())

        // shopsJson is not empty. Now we parsing the file
        val parser = JsonEntitiesParser()
        val responseEntity = parser.parse<ShopsResponseEntity>(shopsJson)

        assertNotNull(responseEntity)
        assertEquals(3, responseEntity.result.size)
        assertEquals("Cortefiel - Preciados", responseEntity.result[0].name)
    }

    @Test
    @Throws(Exception::class)
    fun given_valid_string_containing_json_it_parses_activities_correctly() {

        val activitiesJson = ReadJsonFile().loadJSONFromAsset("Activities3Actividades.json")

        assertTrue(activitiesJson.isNotEmpty())

        // shopsJson is not empty. Now we parsing the file
        val parser = JsonEntitiesParser()
        val responseEntity = parser.parse<ActivitiesResponseEntity>(activitiesJson)

        assertNotNull(responseEntity)
        assertEquals(3, responseEntity.result.size)
        assertEquals("Tour del Bernabéu", responseEntity.result[0].name)
    }
}