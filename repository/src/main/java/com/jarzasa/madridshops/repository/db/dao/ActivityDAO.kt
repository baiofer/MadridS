package com.jarzasa.madridshops.repository.db.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.jarzasa.madridshops.repository.db.DBConstantsActivities
import com.jarzasa.madridshops.repository.db.DBConstantsShops
import com.jarzasa.madridshops.repository.db.DBHelper
import com.jarzasa.madridshops.repository.model.ActivityEntity

internal class ActivityDAO(dbHelper: DBHelper): DAOPersistable<ActivityEntity> {

    private val dbReadOnlyConnection: SQLiteDatabase = dbHelper.readableDatabase
    private val dbReadWriteConnection: SQLiteDatabase = dbHelper.writableDatabase

    override fun insert(element: ActivityEntity): Long {
        val id = dbReadWriteConnection.insert(DBConstantsActivities.TABLE_ACTIVITY,
                null,
                contentValues(element))
        closeDB()
        return id
    }

    fun contentValues(activityEntity: ActivityEntity): ContentValues {
        val content = ContentValues()
        content.put(DBConstantsActivities.KEY_ACTIVITY_ID_JSON, activityEntity.id)
        content.put(DBConstantsActivities.KEY_ACTIVITY_NAME, activityEntity.name)
        content.put(DBConstantsActivities.KEY_ACTIVITY_IMAGE_URL, activityEntity.img)
        content.put(DBConstantsActivities.KEY_ACTIVITY_LOGO_IMAGE_URL, activityEntity.logo_img)
        content.put(DBConstantsActivities.KEY_ACTIVITY_OPENING_HOURS_EN, activityEntity.opening_hours_en)
        content.put(DBConstantsActivities.KEY_ACTIVITY_OPENING_HOURS_ES, activityEntity.opening_hours_es)
        content.put(DBConstantsActivities.KEY_ACTIVITY_OPENING_HOURS_JP, activityEntity.opening_hours_jp)
        content.put(DBConstantsActivities.KEY_ACTIVITY_OPENING_HOURS_CN, activityEntity.opening_hours_cn)
        content.put(DBConstantsActivities.KEY_ACTIVITY_OPENING_HOURS_CL, activityEntity.opening_hours_cl)
        content.put(DBConstantsActivities.KEY_ACTIVITY_OPENING_HOURS_MX, activityEntity.opening_hours_mx)
        content.put(DBConstantsActivities.KEY_ACTIVITY_TELEPHONE, activityEntity.telephone)
        content.put(DBConstantsActivities.KEY_ACTIVITY_EMAIL, activityEntity.email)
        content.put(DBConstantsActivities.KEY_ACTIVITY_URL, activityEntity.url)
        content.put(DBConstantsActivities.KEY_ACTIVITY_ADDRESS, activityEntity.address)
        content.put(DBConstantsActivities.KEY_ACTIVITY_DESCRIPTION_EN, activityEntity.description_en)
        content.put(DBConstantsActivities.KEY_ACTIVITY_DESCRIPTION_ES, activityEntity.description_es)
        content.put(DBConstantsActivities.KEY_ACTIVITY_DESCRIPTION_JP, activityEntity.description_jp)
        content.put(DBConstantsActivities.KEY_ACTIVITY_DESCRIPTION_CN, activityEntity.description_cn)
        content.put(DBConstantsActivities.KEY_ACTIVITY_DESCRIPTION_CL, activityEntity.description_cl)
        content.put(DBConstantsActivities.KEY_ACTIVITY_DESCRIPTION_MX, activityEntity.description_mx)
        content.put(DBConstantsActivities.KEY_ACTIVITY_LATITUDE, activityEntity.gps_lat)
        content.put(DBConstantsActivities.KEY_ACTIVITY_LONGITUDE, activityEntity.gps_lon)
        content.put(DBConstantsActivities.KEY_ACTIVITY_SPECIAL_OFFER, activityEntity.special_offer)
        //content.put(DBConstantsActivities.KEY_ACTIVITY_CATEGORIES, activityEntity.categories)
        content.put(DBConstantsActivities.KEY_ACTIVITY_KEYWORDS_EN, activityEntity.keywords_en)
        content.put(DBConstantsActivities.KEY_ACTIVITY_KEYWORDS_ES, activityEntity.keywords_es)
        content.put(DBConstantsActivities.KEY_ACTIVITY_KEYWORDS_JP, activityEntity.keywords_jp)
        content.put(DBConstantsActivities.KEY_ACTIVITY_KEYWORDS_CN, activityEntity.keywords_cn)
        content.put(DBConstantsActivities.KEY_ACTIVITY_KEYWORDS_CL, activityEntity.keywords_cl)
        content.put(DBConstantsActivities.KEY_ACTIVITY_KEYWORDS_MX, activityEntity.keywords_mx)
        content.put(DBConstantsActivities.KEY_ACTIVITY_BOOKING_OPERATION, activityEntity.booking_operation)
        content.put(DBConstantsActivities.KEY_ACTIVITY_BOOKING_DATA, activityEntity.booking_data)
        return content
    }

    override fun delete(element: ActivityEntity): Long {
        if (element.databaseId < 1) {
            return  0
        }
        return  delete(element.databaseId)
    }

    override fun delete(id: Long): Long {
        val del = dbReadWriteConnection.delete(
                DBConstantsActivities.TABLE_ACTIVITY,
                DBConstantsActivities.KEY_ACTIVITY_DATABASE_ID + " = ? ",
                arrayOf(id.toString())).toLong()
        closeDB()
        return del
    }

    override fun deleteAll(): Boolean {
        val del =dbReadWriteConnection.delete(
                DBConstantsActivities.TABLE_ACTIVITY,
                null,
                null
        ).toLong() >= 0
        closeDB()
        return del
    }

    override fun update(id: Long, element: ActivityEntity): Long {
        val id1 = dbReadWriteConnection.update(
                DBConstantsActivities.TABLE_ACTIVITY,
                contentValues(element),
                DBConstantsActivities.KEY_ACTIVITY_DATABASE_ID + " = ? ",
                arrayOf(id.toString())
        )
        closeDB()
        return id1.toLong()
    }

    fun Cursor.readString(column: String): String {
        return this.getString(this.getColumnIndex(column))
    }

    fun Cursor.readLong(column: String): Long {
        return this.getLong(this.getColumnIndex(column))
    }

    fun entityFromCursor(cursor: Cursor): ActivityEntity? {
        if (cursor.isAfterLast || cursor.isBeforeFirst) {
            return null
        }
        return ActivityEntity(
                cursor.readLong(DBConstantsActivities.KEY_ACTIVITY_DATABASE_ID),
                cursor.readLong(DBConstantsActivities.KEY_ACTIVITY_ID_JSON),
                cursor.readString(DBConstantsActivities.KEY_ACTIVITY_NAME),
                cursor.readString(DBConstantsActivities.KEY_ACTIVITY_IMAGE_URL),
                cursor.readString(DBConstantsActivities.KEY_ACTIVITY_LOGO_IMAGE_URL),
                cursor.readString(DBConstantsActivities.KEY_ACTIVITY_OPENING_HOURS_EN),
                cursor.readString(DBConstantsActivities.KEY_ACTIVITY_OPENING_HOURS_ES),
                cursor.readString(DBConstantsActivities.KEY_ACTIVITY_OPENING_HOURS_JP),
                cursor.readString(DBConstantsActivities.KEY_ACTIVITY_OPENING_HOURS_CN),
                cursor.readString(DBConstantsActivities.KEY_ACTIVITY_OPENING_HOURS_CL),
                cursor.readString(DBConstantsActivities.KEY_ACTIVITY_OPENING_HOURS_MX),
                cursor.readString(DBConstantsActivities.KEY_ACTIVITY_TELEPHONE),
                cursor.readString(DBConstantsActivities.KEY_ACTIVITY_EMAIL),
                cursor.readString(DBConstantsActivities.KEY_ACTIVITY_URL),
                cursor.readString(DBConstantsActivities.KEY_ACTIVITY_ADDRESS),
                cursor.readString(DBConstantsActivities.KEY_ACTIVITY_DESCRIPTION_EN),
                cursor.readString(DBConstantsActivities.KEY_ACTIVITY_DESCRIPTION_ES),
                cursor.readString(DBConstantsActivities.KEY_ACTIVITY_DESCRIPTION_JP),
                cursor.readString(DBConstantsActivities.KEY_ACTIVITY_DESCRIPTION_CN),
                cursor.readString(DBConstantsActivities.KEY_ACTIVITY_DESCRIPTION_CL),
                cursor.readString(DBConstantsActivities.KEY_ACTIVITY_DESCRIPTION_MX),
                cursor.readString(DBConstantsActivities.KEY_ACTIVITY_LATITUDE),
                cursor.readString(DBConstantsActivities.KEY_ACTIVITY_LONGITUDE),
                cursor.readString(DBConstantsActivities.KEY_ACTIVITY_SPECIAL_OFFER),
                //cursor.readString(DBConstantsActivities.KEY_ACTIVITY_CATEGORIES),
                cursor.readString(DBConstantsActivities.KEY_ACTIVITY_KEYWORDS_EN),
                cursor.readString(DBConstantsActivities.KEY_ACTIVITY_KEYWORDS_ES),
                cursor.readString(DBConstantsActivities.KEY_ACTIVITY_KEYWORDS_JP),
                cursor.readString(DBConstantsActivities.KEY_ACTIVITY_KEYWORDS_CN),
                cursor.readString(DBConstantsActivities.KEY_ACTIVITY_KEYWORDS_CL),
                cursor.readString(DBConstantsActivities.KEY_ACTIVITY_KEYWORDS_MX),
                cursor.readString(DBConstantsActivities.KEY_ACTIVITY_BOOKING_OPERATION),
                cursor.readString(DBConstantsActivities.KEY_ACTIVITY_BOOKING_DATA)
        )
    }

    override fun query(id: Long): ActivityEntity {
        val cursor = queryCursor(id)
        cursor.moveToFirst()
        val activity = entityFromCursor(cursor)!!
        return activity
    }

    override fun query(): List<ActivityEntity> {
        val queryResult = ArrayList<ActivityEntity>()
        val cursor =dbReadOnlyConnection.query(
                DBConstantsActivities.TABLE_ACTIVITY,
                DBConstantsActivities.ALL_COLUMNS,
                null,
                null,
                "",
                "",
                DBConstantsActivities.KEY_ACTIVITY_DATABASE_ID
        )
        while (cursor.moveToNext()) {
            val activity: ActivityEntity = entityFromCursor(cursor)!!
            queryResult.add(activity)
        }
        closeDB()
        return queryResult
    }

    override fun queryCursor(id: Long): Cursor {
        val del = dbReadOnlyConnection.query(
                DBConstantsActivities.TABLE_ACTIVITY,
                DBConstantsActivities.ALL_COLUMNS,
                DBConstantsShops.KEY_SHOP_DATABASE_ID + " = ? ",
                arrayOf(id.toString()),
                "",
                "",
                DBConstantsActivities.KEY_ACTIVITY_DATABASE_ID
        )
        closeDB()
        return del
    }

    fun closeDB() {
        dbReadOnlyConnection.close()
        dbReadWriteConnection.close()
    }
}