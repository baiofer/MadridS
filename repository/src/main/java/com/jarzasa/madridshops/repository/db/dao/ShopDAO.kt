package com.jarzasa.madridshops.repository.db.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.jarzasa.madridshops.repository.db.DBConstantsShops
import com.jarzasa.madridshops.repository.db.DBHelper
import com.jarzasa.madridshops.repository.model.ShopEntity

internal class ShopDAO(dbHelper: DBHelper): DAOPersistable<ShopEntity> {

    private val dbReadOnlyConnection: SQLiteDatabase = dbHelper.readableDatabase
    private val dbReadWriteConnection: SQLiteDatabase = dbHelper.writableDatabase

    override fun insert(element: ShopEntity): Long {
        val id = dbReadWriteConnection.insert(DBConstantsShops.TABLE_SHOP,
                                    null,
                                     contentValues(element))
        return id
    }

    fun contentValues(shopEntity: ShopEntity): ContentValues {
        val content = ContentValues()
        content.put(DBConstantsShops.KEY_SHOP_ID_JSON, shopEntity.id)
        content.put(DBConstantsShops.KEY_SHOP_NAME, shopEntity.name)
        content.put(DBConstantsShops.KEY_SHOP_IMAGE_URL, shopEntity.img)
        content.put(DBConstantsShops.KEY_SHOP_LOGO_IMAGE_URL, shopEntity.logo_img)
        content.put(DBConstantsShops.KEY_SHOP_OPENING_HOURS_EN, shopEntity.opening_hours_en)
        content.put(DBConstantsShops.KEY_SHOP_OPENING_HOURS_ES, shopEntity.opening_hours_es)
        content.put(DBConstantsShops.KEY_SHOP_OPENING_HOURS_JP, shopEntity.opening_hours_jp)
        content.put(DBConstantsShops.KEY_SHOP_OPENING_HOURS_CN, shopEntity.opening_hours_cn)
        content.put(DBConstantsShops.KEY_SHOP_OPENING_HOURS_CL, shopEntity.opening_hours_cl)
        content.put(DBConstantsShops.KEY_SHOP_OPENING_HOURS_MX, shopEntity.opening_hours_mx)
        content.put(DBConstantsShops.KEY_SHOP_TELEPHONE, shopEntity.telephone)
        content.put(DBConstantsShops.KEY_SHOP_EMAIL, shopEntity.email)
        content.put(DBConstantsShops.KEY_SHOP_URL, shopEntity.url)
        content.put(DBConstantsShops.KEY_SHOP_ADDRESS, shopEntity.address)
        content.put(DBConstantsShops.KEY_SHOP_DESCRIPTION_EN, shopEntity.description_en)
        content.put(DBConstantsShops.KEY_SHOP_DESCRIPTION_ES, shopEntity.description_es)
        content.put(DBConstantsShops.KEY_SHOP_DESCRIPTION_JP, shopEntity.description_jp)
        content.put(DBConstantsShops.KEY_SHOP_DESCRIPTION_CN, shopEntity.description_cn)
        content.put(DBConstantsShops.KEY_SHOP_DESCRIPTION_CL, shopEntity.description_cl)
        content.put(DBConstantsShops.KEY_SHOP_DESCRIPTION_MX, shopEntity.description_mx)
        content.put(DBConstantsShops.KEY_SHOP_LATITUDE, shopEntity.gps_lat)
        content.put(DBConstantsShops.KEY_SHOP_LONGITUDE, shopEntity.gps_lon)
        content.put(DBConstantsShops.KEY_SHOP_SPECIAL_OFFER, shopEntity.special_offer)
        //content.put(DBConstantsShops.KEY_SHOP_CATEGORIES, shopEntity.categories)
        content.put(DBConstantsShops.KEY_SHOP_KEYWORDS_EN, shopEntity.keywords_en)
        content.put(DBConstantsShops.KEY_SHOP_KEYWORDS_ES, shopEntity.keywords_es)
        content.put(DBConstantsShops.KEY_SHOP_KEYWORDS_JP, shopEntity.keywords_jp)
        content.put(DBConstantsShops.KEY_SHOP_KEYWORDS_CN, shopEntity.keywords_cn)
        content.put(DBConstantsShops.KEY_SHOP_KEYWORDS_CL, shopEntity.keywords_cl)
        content.put(DBConstantsShops.KEY_SHOP_KEYWORDS_MX, shopEntity.keywords_mx)
        content.put(DBConstantsShops.KEY_SHOP_BOOKING_OPERATION, shopEntity.booking_operation)
        content.put(DBConstantsShops.KEY_SHOP_BOOKING_DATA, shopEntity.booking_data)
        return content
    }

    override fun delete(element: ShopEntity): Long {
        if (element.databaseId < 1) {
            return  0
        }
        return  delete(element.databaseId)
    }

    override fun delete(id: Long): Long {
        return dbReadWriteConnection.delete(
                DBConstantsShops.TABLE_SHOP,
                DBConstantsShops.KEY_SHOP_DATABASE_ID + " = ? ",
                arrayOf(id.toString())).toLong()
    }

    override fun deleteAll(): Boolean {
        return dbReadWriteConnection.delete(
                DBConstantsShops.TABLE_SHOP,
                null,
                null
                ).toLong() >= 0
    }

    override fun update(id: Long, element: ShopEntity): Long {
        val id1 = dbReadWriteConnection.update(
                DBConstantsShops.TABLE_SHOP,
                contentValues(element),
                DBConstantsShops.KEY_SHOP_DATABASE_ID + " = ? ",
                arrayOf(id.toString())
        )
        return id1.toLong()
    }

    fun Cursor.readString(column: String): String {
        return this.getString(this.getColumnIndex(column))
    }

    fun Cursor.readLong(column: String): Long {
        return this.getLong(this.getColumnIndex(column))
    }

    fun Cursor.readFloat(column: String): Float {
        return this.getFloat(this.getColumnIndex(column))
    }

    fun entityFromCursor(cursor: Cursor): ShopEntity? {
        if (cursor.isAfterLast || cursor.isBeforeFirst) {
            return null
        }
        return ShopEntity(
                cursor.readLong(DBConstantsShops.KEY_SHOP_DATABASE_ID),
                cursor.readLong(DBConstantsShops.KEY_SHOP_ID_JSON),
                cursor.readString(DBConstantsShops.KEY_SHOP_NAME),
                cursor.readString(DBConstantsShops.KEY_SHOP_IMAGE_URL),
                cursor.readString(DBConstantsShops.KEY_SHOP_LOGO_IMAGE_URL),
                cursor.readString(DBConstantsShops.KEY_SHOP_OPENING_HOURS_EN),
                cursor.readString(DBConstantsShops.KEY_SHOP_OPENING_HOURS_ES),
                cursor.readString(DBConstantsShops.KEY_SHOP_OPENING_HOURS_JP),
                cursor.readString(DBConstantsShops.KEY_SHOP_OPENING_HOURS_CN),
                cursor.readString(DBConstantsShops.KEY_SHOP_OPENING_HOURS_CL),
                cursor.readString(DBConstantsShops.KEY_SHOP_OPENING_HOURS_MX),
                cursor.readString(DBConstantsShops.KEY_SHOP_TELEPHONE),
                cursor.readString(DBConstantsShops.KEY_SHOP_EMAIL),
                cursor.readString(DBConstantsShops.KEY_SHOP_URL),
                cursor.readString(DBConstantsShops.KEY_SHOP_ADDRESS),
                cursor.readString(DBConstantsShops.KEY_SHOP_DESCRIPTION_EN),
                cursor.readString(DBConstantsShops.KEY_SHOP_DESCRIPTION_ES),
                cursor.readString(DBConstantsShops.KEY_SHOP_DESCRIPTION_JP),
                cursor.readString(DBConstantsShops.KEY_SHOP_DESCRIPTION_CN),
                cursor.readString(DBConstantsShops.KEY_SHOP_DESCRIPTION_CL),
                cursor.readString(DBConstantsShops.KEY_SHOP_DESCRIPTION_MX),
                cursor.readString(DBConstantsShops.KEY_SHOP_LATITUDE),
                cursor.readString(DBConstantsShops.KEY_SHOP_LONGITUDE),
                cursor.readString(DBConstantsShops.KEY_SHOP_SPECIAL_OFFER),
                //cursor.readString(DBConstantsShops.KEY_SHOP_CATEGORIES),
                cursor.readString(DBConstantsShops.KEY_SHOP_KEYWORDS_EN),
                cursor.readString(DBConstantsShops.KEY_SHOP_KEYWORDS_ES),
                cursor.readString(DBConstantsShops.KEY_SHOP_KEYWORDS_JP),
                cursor.readString(DBConstantsShops.KEY_SHOP_KEYWORDS_CN),
                cursor.readString(DBConstantsShops.KEY_SHOP_KEYWORDS_CL),
                cursor.readString(DBConstantsShops.KEY_SHOP_KEYWORDS_MX),
                cursor.readString(DBConstantsShops.KEY_SHOP_BOOKING_OPERATION),
                cursor.readString(DBConstantsShops.KEY_SHOP_BOOKING_DATA)
        )
    }

    override fun query(id: Long): ShopEntity {
        val cursor = queryCursor(id)
        cursor.moveToFirst()
        val shop = entityFromCursor(cursor)!!
        return shop
    }

    override fun query(): List<ShopEntity> {
        val queryResult = ArrayList<ShopEntity>()
        val cursor =dbReadOnlyConnection.query(
                        DBConstantsShops.TABLE_SHOP,
                        DBConstantsShops.ALL_COLUMNS,
                        null,
                        null,
                        "",
                        "",
                        DBConstantsShops.KEY_SHOP_DATABASE_ID
                    )
        while (cursor.moveToNext()) {
            val shop: ShopEntity = entityFromCursor(cursor)!!
            queryResult.add(shop)
        }
        return queryResult
    }

    override fun queryCursor(id: Long): Cursor {
        return dbReadOnlyConnection.query(
                DBConstantsShops.TABLE_SHOP,
                DBConstantsShops.ALL_COLUMNS,
                DBConstantsShops.KEY_SHOP_DATABASE_ID + " = ? ",
                arrayOf(id.toString()),
                "",
                "",
                DBConstantsShops.KEY_SHOP_DATABASE_ID
        )
    }
}