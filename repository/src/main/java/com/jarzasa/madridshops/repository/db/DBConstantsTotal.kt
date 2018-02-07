package com.jarzasa.madridshops.repository.db

import com.jarzasa.madridshops.repository.db.DBConstantsActivities
import com.jarzasa.madridshops.repository.db.DBConstantsShops

object DBConstantsTotal {
    val DROP_DATABASE_SCRIPTS = ""

    val CREATE_DATABASE_SCRIPTS = arrayOf(DBConstantsShops.SQL_SCRIPT_CREATE_SHOP_TABLE,
            DBConstantsActivities.SQL_SCRIPT_CREATE_ACTIVITY_TABLE)
}