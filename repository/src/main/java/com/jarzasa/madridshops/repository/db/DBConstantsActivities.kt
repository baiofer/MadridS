package com.jarzasa.madridshops.repository.db

object DBConstantsActivities {
    val TABLE_ACTIVITY = "ACTIVITY"

    // Table field constants
    val KEY_ACTIVITY_DATABASE_ID = "_id"
    val KEY_ACTIVITY_ID_JSON = "DATABASE_ID"
    val KEY_ACTIVITY_NAME = "NAME"
    val KEY_ACTIVITY_IMAGE_URL = "IMAGE_URL"
    val KEY_ACTIVITY_LOGO_IMAGE_URL = "LOGO_IMAGE_URL"

    val KEY_ACTIVITY_ADDRESS = "ADDRESS"
    val KEY_ACTIVITY_TELEPHONE = "TELEPHONE"
    val KEY_ACTIVITY_EMAIL = "EMAIL"
    val KEY_ACTIVITY_URL = "URL"
    val KEY_ACTIVITY_DESCRIPTION_EN = "DESCRIPTION_EN"
    val KEY_ACTIVITY_DESCRIPTION_ES = "DESCRIPTION_ES"
    val KEY_ACTIVITY_DESCRIPTION_JP = "DESCRIPTION_JP"
    val KEY_ACTIVITY_DESCRIPTION_CN = "DESCRIPTION_CN"
    val KEY_ACTIVITY_DESCRIPTION_CL = "DESCRIPTION_CL"
    val KEY_ACTIVITY_DESCRIPTION_MX = "DESCRIPTION_MX"

    val KEY_ACTIVITY_LATITUDE = "LATITUDE"
    val KEY_ACTIVITY_LONGITUDE = "LONGITUDE"

    val KEY_ACTIVITY_OPENING_HOURS_EN = "OPENING_HOURS_EN"
    val KEY_ACTIVITY_OPENING_HOURS_ES = "OPENING_HOURS_ES"
    val KEY_ACTIVITY_OPENING_HOURS_JP = "OPENING_HOURS_JP"
    val KEY_ACTIVITY_OPENING_HOURS_CN = "OPENING_HOURS_CN"
    val KEY_ACTIVITY_OPENING_HOURS_CL = "OPENING_HOURS_CL"
    val KEY_ACTIVITY_OPENING_HOURS_MX = "OPENING_HOURS_MX"

    val KEY_ACTIVITY_SPECIAL_OFFER = "SPECIAL_OFFER"
    val KEY_ACTIVITY_CATEGORIES = "CATEGORIES"
    val KEY_ACTIVITY_BOOKING_OPERATION = "BOOKING_OPERATION"
    val KEY_ACTIVITY_BOOKING_DATA = "BOOKING_DATA"
    val KEY_ACTIVITY_KEYWORDS_EN = "KEYWORDS_EN"
    val KEY_ACTIVITY_KEYWORDS_ES = "KEYWORDS_ES"
    val KEY_ACTIVITY_KEYWORDS_JP = "KEYWORDS_JP"
    val KEY_ACTIVITY_KEYWORDS_CN = "KEYWORDS_CN"
    val KEY_ACTIVITY_KEYWORDS_CL = "KEYWORDS_CL"
    val KEY_ACTIVITY_KEYWORDS_MX = "KEYWORDS_MX"

    val ALL_COLUMNS = arrayOf(
            KEY_ACTIVITY_DATABASE_ID,
            KEY_ACTIVITY_ID_JSON,
            KEY_ACTIVITY_NAME,
            KEY_ACTIVITY_IMAGE_URL,
            KEY_ACTIVITY_LOGO_IMAGE_URL,
            KEY_ACTIVITY_ADDRESS,
            KEY_ACTIVITY_TELEPHONE,
            KEY_ACTIVITY_EMAIL,
            KEY_ACTIVITY_URL,
            KEY_ACTIVITY_DESCRIPTION_EN,
            KEY_ACTIVITY_DESCRIPTION_ES,
            KEY_ACTIVITY_DESCRIPTION_JP,
            KEY_ACTIVITY_DESCRIPTION_CN,
            KEY_ACTIVITY_DESCRIPTION_CL,
            KEY_ACTIVITY_DESCRIPTION_MX,
            KEY_ACTIVITY_LATITUDE,
            KEY_ACTIVITY_LONGITUDE,
            KEY_ACTIVITY_OPENING_HOURS_EN,
            KEY_ACTIVITY_OPENING_HOURS_ES,
            KEY_ACTIVITY_OPENING_HOURS_JP,
            KEY_ACTIVITY_OPENING_HOURS_CN,
            KEY_ACTIVITY_OPENING_HOURS_CL,
            KEY_ACTIVITY_OPENING_HOURS_MX,
            KEY_ACTIVITY_SPECIAL_OFFER,
            KEY_ACTIVITY_CATEGORIES,
            KEY_ACTIVITY_BOOKING_OPERATION,
            KEY_ACTIVITY_BOOKING_DATA,
            KEY_ACTIVITY_KEYWORDS_EN,
            KEY_ACTIVITY_KEYWORDS_ES,
            KEY_ACTIVITY_KEYWORDS_JP,
            KEY_ACTIVITY_KEYWORDS_CN,
            KEY_ACTIVITY_KEYWORDS_CL,
            KEY_ACTIVITY_KEYWORDS_MX)

    val SQL_SCRIPT_CREATE_ACTIVITY_TABLE = (
            "create table " + TABLE_ACTIVITY
                    + "( "
                    + KEY_ACTIVITY_DATABASE_ID + " integer primary key autoincrement, "
                    + KEY_ACTIVITY_ID_JSON + " real, "
                    + KEY_ACTIVITY_NAME + " text not null, "
                    + KEY_ACTIVITY_IMAGE_URL + " text, "
                    + KEY_ACTIVITY_LOGO_IMAGE_URL + " text, "
                    + KEY_ACTIVITY_ADDRESS + " text, "
                    + KEY_ACTIVITY_TELEPHONE + " text, "
                    + KEY_ACTIVITY_EMAIL + " text, "
                    + KEY_ACTIVITY_URL + " text, "
                    + KEY_ACTIVITY_LATITUDE + " real, "
                    + KEY_ACTIVITY_LONGITUDE + " real, "
                    + KEY_ACTIVITY_DESCRIPTION_EN + " text, "
                    + KEY_ACTIVITY_DESCRIPTION_ES + " text, "
                    + KEY_ACTIVITY_DESCRIPTION_JP + " text, "
                    + KEY_ACTIVITY_DESCRIPTION_CN + " text, "
                    + KEY_ACTIVITY_DESCRIPTION_CL + " text, "
                    + KEY_ACTIVITY_DESCRIPTION_MX + " text, "
                    + KEY_ACTIVITY_OPENING_HOURS_EN + " text, "
                    + KEY_ACTIVITY_OPENING_HOURS_ES + " text, "
                    + KEY_ACTIVITY_OPENING_HOURS_JP + " text, "
                    + KEY_ACTIVITY_OPENING_HOURS_CN + " text, "
                    + KEY_ACTIVITY_OPENING_HOURS_CL + " text, "
                    + KEY_ACTIVITY_OPENING_HOURS_MX + " text, "
                    + KEY_ACTIVITY_SPECIAL_OFFER + " text, "
                    + KEY_ACTIVITY_CATEGORIES + " text, "
                    + KEY_ACTIVITY_BOOKING_OPERATION + " text, "
                    + KEY_ACTIVITY_BOOKING_DATA + " text, "
                    + KEY_ACTIVITY_KEYWORDS_EN + " text, "
                    + KEY_ACTIVITY_KEYWORDS_ES + " text, "
                    + KEY_ACTIVITY_KEYWORDS_JP + " text, "
                    + KEY_ACTIVITY_KEYWORDS_CN + " text, "
                    + KEY_ACTIVITY_KEYWORDS_CL + " text, "
                    + KEY_ACTIVITY_KEYWORDS_MX + " text "
                    + ");")

    //val DROP_DATABASE_SCRIPTS = ""

    //val CREATE_DATABASE_SCRIPTS = arrayOf(SQL_SCRIPT_CREATE_ACTIVITY_TABLE)
}