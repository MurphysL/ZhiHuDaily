package com.murphysl.zhihudaily.data.source.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.murphysl.zhihudaily.config.*

/**
 * DBHelper
 *
 * author: MurphySL
 * time: 2017/5/22 22:30
 */
class DBHelper(context : Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    val SQL_CREATE_TABLE =
                        """CREATE TABLE ${TABLE_NEWS_NAME} (
                                ${NEWS_ID} ${NEWS_ID_TYPE} PRIMARY KEY,
                                ${NEWS_DATE} ${NEWS_DATE_TYPE},
                                ${NEWS_TITLE} ${NEWS_TITLE_TYPE},
                                ${NEWS_IMAGE} ${NEWS_IMAGE_TYPE},$)"""
                                .replace("\\s+".toRegex()," ")
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)  = Unit

    override fun onOpen(db: SQLiteDatabase?)  = Unit

}
