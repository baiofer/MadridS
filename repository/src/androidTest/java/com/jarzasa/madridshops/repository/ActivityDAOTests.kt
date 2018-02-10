package com.jarzasa.madridshops.repository

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.jarzasa.madridshops.repository.db.build
import com.jarzasa.madridshops.repository.db.dao.ActivityDAO
import com.jarzasa.madridshops.repository.model.ActivityEntity
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivityDAOTests {

    private fun initializeDB(): ActivityDAO {
        val appContext = InstrumentationRegistry.getTargetContext()
        val dbHelper = build(appContext, BuildConfig.DB_TESTS, 1)

        val activity1 = ActivityEntity(1, 1, "My activity1", "", "", "",
                "", "", "", "", "",
                "", "", "", "", "My description1", "", "",
                "", "", "", "1", "1", "",
                "", "", "", "", "", "",
                "", "")
        val activity2 = ActivityEntity(1, 1, "My activity2", "", "", "",
                "", "", "", "", "",
                "", "", "", "", "My description2", "", "",
                "", "", "", "1", "1", "",
                "", "", "", "", "", "",
                "", "")
        val activityEntityDAO = ActivityDAO(dbHelper)

        //DeleteAll
        activityEntityDAO.deleteAll()

        //Insert 2 shops
        activityEntityDAO.insert(activity1)
        activityEntityDAO.insert(activity2)

        return activityEntityDAO
    }

    @Test
    @Throws(Exception::class)
    fun given_valid_activityentity_it_gets_inserted_correctly() {

        val activityEntityDAO: ActivityDAO = initializeDB()

        //DeleteAll
        activityEntityDAO.deleteAll()

        //Insert element
        val activity = ActivityEntity(1, 1, "My activity1", "", "", "",
                "", "", "", "", "",
                "", "", "", "", "My description1", "", "",
                "", "", "", "1", "1", "",
                "", "", "", "", "", "",
                "", "")

        val id = activityEntityDAO.insert(activity)

        Assert.assertTrue(id > 0)
    }

    @Test
    @Throws(Exception::class)
    fun given_valid_db_whith_activity_registers_deleteall_functions_properly() {

        val activityEntityDAO: ActivityDAO = initializeDB()

        //Delete all shops
        activityEntityDAO.deleteAll()

        //Query all shops
        val activities = activityEntityDAO.query()

        Assert.assertTrue(activities.size == 0)
    }

    @Test
    @Throws(Exception::class)
    fun given_valid_db_whith_activity_registers_query_functions_properly() {

        val activityEntityDAO: ActivityDAO = initializeDB()

        //Query all shops
        val activities = activityEntityDAO.query()

        Assert.assertTrue(activities[0].name == "My activity1")
        Assert.assertTrue(activities[1].name == "My activity2")
    }

    @Test
    @Throws(Exception::class)
    fun given_valid_db_whith_activity_registers_queryelement_functions_properly() {

        val activityEntityDAO: ActivityDAO = initializeDB()

        //Gets databaseId from first register
        val activities = activityEntityDAO.query()
        val id = activities[1].databaseId

        //Query element
        val activity = activityEntityDAO.query(id)
        Assert.assertTrue(activity.name == "My activity2")
    }

    @Test
    @Throws(Exception::class)
    fun given_valid_db_whith_registers_deleteelement_functions_properly() {

        val activityEntityDAO: ActivityDAO = initializeDB()

        //Gets databaseId from first register
        val activities = activityEntityDAO.query()
        val id = activities[1].databaseId

        //Delete element
        activityEntityDAO.delete(id)

        //Query all elements
        val newactivities = activityEntityDAO.query()

        Assert.assertTrue(newactivities.size == 1)
    }

    @Test
    @Throws(Exception::class)
    fun given_valid_db_whith_registers_updateelement_functions_properly() {

        val activityEntityDAO: ActivityDAO = initializeDB()

        //Gets databaseId from first register
        val activities = activityEntityDAO.query()
        val id = activities[1].databaseId

        //Change name of the second register
        val updateActivity = ActivityEntity(id, 1, "My activity2 updated", "", "", "",
                "", "", "", "", "",
                "", "", "", "", "My description2", "", "",
                "", "", "", "1", "1", "",
                "", "", "", "", "", "",
                "", "")

        //Update shop
        activityEntityDAO.update(id, updateActivity)

        //Test new name
        val newActivities = activityEntityDAO.query()

        Assert.assertTrue(newActivities.size == 2)
        Assert.assertTrue(newActivities[1].name == "My activity2 updated")
    }


}