package com.jarzasa.madridshops.repository.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

fun build(context: Context, name: String, version: Int): DBHelper {
    return com.jarzasa.madridshops.repository.db.DBHelper(context, name, null, version)
}

class DBHelper(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int)
      : SQLiteOpenHelper(context, name, factory, version) {

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)

        // We need this for ON CASCADE deletion to work properly
        db?.setForeignKeyConstraintsEnabled(true)
    }

    override fun onCreate(db: SQLiteDatabase?) {
        DBConstantsTotal.CREATE_DATABASE_SCRIPTS.forEach {
            db?.execSQL(it)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val updateChangesFromV1ToV2 = {
        }
        val updateChangesFromV2ToV3 = {
        }
        if (oldVersion == 1 && newVersion == 2) {
            updateChangesFromV1ToV2()
        }
        if (oldVersion == 1 && newVersion == 3) {
            updateChangesFromV1ToV2()
            updateChangesFromV2ToV3()
        }
        if (oldVersion == 2 && newVersion == 3) {
            updateChangesFromV2ToV3()
        }
    }
}

fun convert(boolean: Boolean): Int {
    return if (boolean) 1 else 0
}

fun convert(int: Int): Boolean {
    return if (int == 0) false else true
}