package com.example.homework4

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sqlStatement = "CREATE TABLE ${DatabaseContract.DreamEntry.TABLE_NAME} (" +
                "${DatabaseContract.DreamEntry.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${DatabaseContract.DreamEntry.COLUMN_TITLE} TEXT," +
                "${DatabaseContract.DreamEntry.COLUMN_CONTENT} TEXT," +
                "${DatabaseContract.DreamEntry.COLUMN_REFLECTION} TEXT," +
                "${DatabaseContract.DreamEntry.COLUMN_EMOTION} TEXT)"

        val statement= db!!.compileStatement(sqlStatement)

        statement.execute()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Dreams.db"
    }

    fun addDream(dream: Dream) : Long {

        val db:SQLiteDatabase = this.writableDatabase

        val values = ContentValues().apply{
            //put(DatabaseContract.DreamEntry.COLUMN_ID, dream.id)
            put(DatabaseContract.DreamEntry.COLUMN_TITLE, dream.title)
            put(DatabaseContract.DreamEntry.COLUMN_CONTENT, dream.content)
            put(DatabaseContract.DreamEntry.COLUMN_REFLECTION, dream.reflection)
            put(DatabaseContract.DreamEntry.COLUMN_EMOTION, dream.emotion)
        }

        val newRowId=db?.insert(DatabaseContract.DreamEntry.TABLE_NAME, null, values)

        db.close()
        return newRowId
    }

    fun getAll() : MutableList<Dream>{

        val db:SQLiteDatabase = this.readableDatabase
        var dreams = mutableListOf<Dream>()

        val sqlQuery = "SELECT * FROM ${DatabaseContract.DreamEntry.TABLE_NAME} ORDER BY ${DatabaseContract.DreamEntry.COLUMN_ID} ASC"
        val resultSet = db.rawQuery(sqlQuery, null)

        if (resultSet.moveToFirst()){
            do{
                dreams.add( Dream (
                    //resultSet.getLong(0),
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4))
                )
            } while (resultSet.moveToNext())
        }

        resultSet.close()
        db.close()
        return dreams
    }

    fun deleteDream(id:Long) : Int{
        val db:SQLiteDatabase = this.writableDatabase
        val sqlQuery = "DELETE FROM ${DatabaseContract.DreamEntry.TABLE_NAME} where ${DatabaseContract.DreamEntry.COLUMN_ID} = $id"
        val statement = db.compileStatement(sqlQuery)
        val numRows = statement.executeUpdateDelete()
        db.close()
        return numRows

    }

    fun updateDream(id:Long, title:String, content:String, reflection:String, emotion:String) : Int{
        val db:SQLiteDatabase = this.writableDatabase

        val values = ContentValues().apply {
            put(DatabaseContract.DreamEntry.COLUMN_TITLE, title)
            put(DatabaseContract.DreamEntry.COLUMN_CONTENT, content)
            put(DatabaseContract.DreamEntry.COLUMN_REFLECTION, reflection)
            put(DatabaseContract.DreamEntry.COLUMN_EMOTION, emotion)
        }

// Which row to update, based on the title
        val selection = "${DatabaseContract.DreamEntry.COLUMN_ID} LIKE ?"
        val selectionArgs = arrayOf("$id")
        val count = db.update(
            DatabaseContract.DreamEntry.TABLE_NAME,
            values,
            selection,
            selectionArgs)
        return count
    }

    fun getNewest() : Dream{
        val dreams=getAll()
        var x:Long = 0
        //for (dream in dreams){
        //    if (dream.id>x) x=dream.id
        //}
        return getDream(x)
    }

    fun getDream(id:Long) : Dream{
        val db:SQLiteDatabase = this.readableDatabase
        var dreams = mutableListOf<Dream>()

        val sqlQuery = "SELECT * FROM ${DatabaseContract.DreamEntry.TABLE_NAME} where ${DatabaseContract.DreamEntry.COLUMN_ID} = $id"
        val resultSet = db.rawQuery(sqlQuery, null)

        if (resultSet.moveToFirst()){
            do{
                dreams.add( Dream (
                    //resultSet.getLong(0),
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4))
                )
            } while (resultSet.moveToNext())
        }

        resultSet.close()
        db.close()
        return dreams[0]
    }


}