package com.jarzasa.madridshops.repository.db.dao

import android.database.Cursor

internal interface DAOReadOperations<T> {
    /*
     * returns the element from DB
     * if not exits, returns null
     */
    fun query(id: Long): T

    /*
     * returns all the elements from DB
     * if not exits list, returns null
     */
    fun query(): List<T>

    /*
     * returns a Cursor from DB
     * if not exits list, returns null
     */
    fun queryCursor(id: Long): Cursor
}

internal interface DAOWriteOperations<T> {
    /*
     *  insert de element in DB. Returns 1
     *  if element id invalid (< 1) or name not exits, not insert the element and returns 0
     *  if there is an insertion error, returs -1
     */
    fun insert(element: T): Long

    /*
     *  update de element id with element in DB. Returns 1
     *  if element id invalid (< 1) or not exits or there is an error inserting,
     *  not do nothing and returns -1
     *  if insertion is correct, returns 1
     *  if data input are
     */
    fun update(id: Long, element: T): Long

    /*
     *  delete de element passed from DB. Returns 1
     *  if element id invalid (< 1) or not exits, not do nothing and returns 0
     */
    fun delete(element: T): Long

    /*
     *  delete de element with id passed from DB. Returns 1
     *  if element id invalid (< 1) or not exits, not do nothing and returns 0
     */
    fun delete(id: Long): Long

    /*
     *  delete all elements
     */
    fun deleteAll(): Boolean
}

internal interface DAOPersistable<T>: DAOWriteOperations<T>, DAOReadOperations<T>
