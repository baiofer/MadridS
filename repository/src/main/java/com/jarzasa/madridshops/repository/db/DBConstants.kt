package com.jarzasa.madridshops.repository.db

object DBConstants {
    val TABLE_SHOP = "SHOP"

    // Table field constants
    val KEY_SHOP_DATABASE_ID = "_id"
    val KEY_SHOP_ID_JSON = "DATABASE_ID"
    val KEY_SHOP_NAME = "NAME"
    val KEY_SHOP_IMAGE_URL = "IMAGE_URL"
    val KEY_SHOP_LOGO_IMAGE_URL = "LOGO_IMAGE_URL"

    val KEY_SHOP_ADDRESS = "ADDRESS"
    val KEY_SHOP_TELEPHONE = "TELEPHONE"
    val KEY_SHOP_EMAIL = "EMAIL"
    val KEY_SHOP_URL = "URL"
    val KEY_SHOP_DESCRIPTION_EN = "DESCRIPTION_EN"
    val KEY_SHOP_DESCRIPTION_ES = "DESCRIPTION_ES"
    val KEY_SHOP_DESCRIPTION_JP = "DESCRIPTION_JP"
    val KEY_SHOP_DESCRIPTION_CN = "DESCRIPTION_CN"
    val KEY_SHOP_DESCRIPTION_CL = "DESCRIPTION_CL"
    val KEY_SHOP_DESCRIPTION_MX = "DESCRIPTION_MX"

    val KEY_SHOP_LATITUDE = "LATITUDE"
    val KEY_SHOP_LONGITUDE = "LONGITUDE"

    val KEY_SHOP_OPENING_HOURS_EN = "OPENING_HOURS_EN"
    val KEY_SHOP_OPENING_HOURS_ES = "OPENING_HOURS_ES"
    val KEY_SHOP_OPENING_HOURS_JP = "OPENING_HOURS_JP"
    val KEY_SHOP_OPENING_HOURS_CN = "OPENING_HOURS_CN"
    val KEY_SHOP_OPENING_HOURS_CL = "OPENING_HOURS_CL"
    val KEY_SHOP_OPENING_HOURS_MX = "OPENING_HOURS_MX"

    val KEY_SHOP_SPECIAL_OFFER = "SPECIAL_OFFER"
    val KEY_SHOP_CATEGORIES = "CATEGORIES"
    val KEY_SHOP_BOOKING_OPERATION = "BOOKING_OPERATION"
    val KEY_SHOP_BOOKING_DATA = "BOOKING_DATA"
    val KEY_SHOP_KEYWORDS_EN = "KEYWORDS_EN"
    val KEY_SHOP_KEYWORDS_ES = "KEYWORDS_ES"
    val KEY_SHOP_KEYWORDS_JP = "KEYWORDS_JP"
    val KEY_SHOP_KEYWORDS_CN = "KEYWORDS_CN"
    val KEY_SHOP_KEYWORDS_CL = "KEYWORDS_CL"
    val KEY_SHOP_KEYWORDS_MX = "KEYWORDS_MX"

    val ALL_COLUMNS = arrayOf(
            KEY_SHOP_DATABASE_ID,
            KEY_SHOP_ID_JSON,
            KEY_SHOP_NAME,
            KEY_SHOP_IMAGE_URL,
            KEY_SHOP_LOGO_IMAGE_URL,
            KEY_SHOP_ADDRESS,
            KEY_SHOP_TELEPHONE,
            KEY_SHOP_EMAIL,
            KEY_SHOP_URL,
            KEY_SHOP_DESCRIPTION_EN,
            KEY_SHOP_DESCRIPTION_ES,
            KEY_SHOP_DESCRIPTION_JP,
            KEY_SHOP_DESCRIPTION_CN,
            KEY_SHOP_DESCRIPTION_CL,
            KEY_SHOP_DESCRIPTION_MX,
            KEY_SHOP_LATITUDE,
            KEY_SHOP_LONGITUDE,
            KEY_SHOP_OPENING_HOURS_EN,
            KEY_SHOP_OPENING_HOURS_ES,
            KEY_SHOP_OPENING_HOURS_JP,
            KEY_SHOP_OPENING_HOURS_CN,
            KEY_SHOP_OPENING_HOURS_CL,
            KEY_SHOP_OPENING_HOURS_MX,
            KEY_SHOP_SPECIAL_OFFER,
            KEY_SHOP_CATEGORIES,
            KEY_SHOP_BOOKING_OPERATION,
            KEY_SHOP_BOOKING_DATA,
            KEY_SHOP_KEYWORDS_EN,
            KEY_SHOP_KEYWORDS_ES,
            KEY_SHOP_KEYWORDS_JP,
            KEY_SHOP_KEYWORDS_CN,
            KEY_SHOP_KEYWORDS_CL,
            KEY_SHOP_KEYWORDS_MX)

    val SQL_SCRIPT_CREATE_SHOP_TABLE = (
            "create table " + TABLE_SHOP
                    + "( "
                    + KEY_SHOP_DATABASE_ID + " integer primary key autoincrement, "
                    + KEY_SHOP_ID_JSON + " real, "
                    + KEY_SHOP_NAME + " text not null, "
                    + KEY_SHOP_IMAGE_URL + " text, "
                    + KEY_SHOP_LOGO_IMAGE_URL + " text, "
                    + KEY_SHOP_ADDRESS + " text, "
                    + KEY_SHOP_TELEPHONE + " text, "
                    + KEY_SHOP_EMAIL + " text, "
                    + KEY_SHOP_URL + " text, "
                    + KEY_SHOP_LATITUDE + " real, "
                    + KEY_SHOP_LONGITUDE + " real, "
                    + KEY_SHOP_DESCRIPTION_EN + " text, "
                    + KEY_SHOP_DESCRIPTION_ES + " text, "
                    + KEY_SHOP_DESCRIPTION_JP + " text, "
                    + KEY_SHOP_DESCRIPTION_CN + " text, "
                    + KEY_SHOP_DESCRIPTION_CL + " text, "
                    + KEY_SHOP_DESCRIPTION_MX + " text, "
                    + KEY_SHOP_OPENING_HOURS_EN + " text, "
                    + KEY_SHOP_OPENING_HOURS_ES + " text, "
                    + KEY_SHOP_OPENING_HOURS_JP + " text, "
                    + KEY_SHOP_OPENING_HOURS_CN + " text, "
                    + KEY_SHOP_OPENING_HOURS_CL + " text, "
                    + KEY_SHOP_OPENING_HOURS_MX + " text, "
                    + KEY_SHOP_SPECIAL_OFFER + " text, "
                    + KEY_SHOP_CATEGORIES + " text, "
                    + KEY_SHOP_BOOKING_OPERATION + " text, "
                    + KEY_SHOP_BOOKING_DATA + " text, "
                    + KEY_SHOP_KEYWORDS_EN + " text, "
                    + KEY_SHOP_KEYWORDS_ES + " text, "
                    + KEY_SHOP_KEYWORDS_JP + " text, "
                    + KEY_SHOP_KEYWORDS_CN + " text, "
                    + KEY_SHOP_KEYWORDS_CL + " text, "
                    + KEY_SHOP_KEYWORDS_MX + " text "
                    + ");")

    val DROP_DATABASE_SCRIPTS = ""

    val CREATE_DATABASE_SCRIPTS = arrayOf(SQL_SCRIPT_CREATE_SHOP_TABLE)
}