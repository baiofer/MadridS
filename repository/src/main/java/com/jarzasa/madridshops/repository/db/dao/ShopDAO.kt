package com.jarzasa.madridshops.repository.db.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.jarzasa.madridshops.repository.db.DBConstants
import com.jarzasa.madridshops.repository.db.DBHelper
import com.jarzasa.madridshops.repository.model.ShopEntity

class ShopDAO(dbHelper: DBHelper): DAOPersistable<ShopEntity> {

    private val dbReadOnlyConnection: SQLiteDatabase = dbHelper.readableDatabase
    private val dbReadWriteConnection: SQLiteDatabase = dbHelper.writableDatabase

    override fun insert(element: ShopEntity): Long {
        var id: Long = 0

        id = dbReadWriteConnection.insert(DBConstants.TABLE_SHOP,
                                    null,
                                     contentValues(element))
        return id
    }

    fun contentValues(shopEntity: ShopEntity): ContentValues {
        val content = ContentValues()
        content.put(DBConstants.KEY_SHOP_ID, shopEntity.id)
        content.put(DBConstants.KEY_SHOP_NAME, shopEntity.name)
        content.put(DBConstants.KEY_SHOP_IMAGE_URL, shopEntity.img)
        content.put(DBConstants.KEY_SHOP_LOGO_IMAGE_URL, shopEntity.logo_img)
        content.put(DBConstants.KEY_SHOP_OPENING_HOURS_EN, shopEntity.opening_hours_en)
        content.put(DBConstants.KEY_SHOP_OPENING_HOURS_ES, shopEntity.opening_hours_es)
        content.put(DBConstants.KEY_SHOP_OPENING_HOURS_JP, shopEntity.opening_hours_jp)
        content.put(DBConstants.KEY_SHOP_OPENING_HOURS_CN, shopEntity.opening_hours_cn)
        content.put(DBConstants.KEY_SHOP_OPENING_HOURS_CL, shopEntity.opening_hours_cl)
        content.put(DBConstants.KEY_SHOP_OPENING_HOURS_MX, shopEntity.opening_hours_mx)
        content.put(DBConstants.KEY_SHOP_TELEPHONE, shopEntity.telephone)
        content.put(DBConstants.KEY_SHOP_EMAIL, shopEntity.email)
        content.put(DBConstants.KEY_SHOP_URL, shopEntity.url)
        content.put(DBConstants.KEY_SHOP_ADDRESS, shopEntity.address)
        content.put(DBConstants.KEY_SHOP_DESCRIPTION_EN, shopEntity.description_en)
        content.put(DBConstants.KEY_SHOP_DESCRIPTION_ES, shopEntity.description_es)
        content.put(DBConstants.KEY_SHOP_DESCRIPTION_JP, shopEntity.description_jp)
        content.put(DBConstants.KEY_SHOP_DESCRIPTION_CN, shopEntity.description_cn)
        content.put(DBConstants.KEY_SHOP_DESCRIPTION_CL, shopEntity.description_cl)
        content.put(DBConstants.KEY_SHOP_DESCRIPTION_MX, shopEntity.description_mx)
        content.put(DBConstants.KEY_SHOP_LATITUDE, shopEntity.gps_lat)
        content.put(DBConstants.KEY_SHOP_LONGITUDE, shopEntity.gps_lon)
        content.put(DBConstants.KEY_SHOP_SPECIAL_OFFER, shopEntity.special_offer)
        content.put(DBConstants.KEY_SHOP_CATEGORIES, shopEntity.categories)
        content.put(DBConstants.KEY_SHOP_KEYWORDS_EN, shopEntity.keywords_en)
        content.put(DBConstants.KEY_SHOP_KEYWORDS_ES, shopEntity.keywords_es)
        content.put(DBConstants.KEY_SHOP_KEYWORDS_JP, shopEntity.keywords_jp)
        content.put(DBConstants.KEY_SHOP_KEYWORDS_CN, shopEntity.keywords_cn)
        content.put(DBConstants.KEY_SHOP_KEYWORDS_CL, shopEntity.keywords_cl)
        content.put(DBConstants.KEY_SHOP_KEYWORDS_MX, shopEntity.keywords_mx)
        content.put(DBConstants.KEY_SHOP_BOOKING_OPERATION, shopEntity.booking_operation)
        content.put(DBConstants.KEY_SHOP_BOOKING_DATA, shopEntity.booking_data)
        return content
    }

    override fun delete(element: ShopEntity): Long {
        return  delete(element.id)
    }

    override fun delete(id: Long): Long {
        return 1
    }

    override fun deleteAll(): Boolean {

        return true
    }


    override fun query(id: Int): ShopEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun query(): List<ShopEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun queryCursor(): Cursor {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    override fun update(id: Long, element: ShopEntity): Long {


        return 1
    }




}