package com.example.spamkeyboard.methods

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.spamkeyboard.models.SuggestionModel

@RequiresApi(Build.VERSION_CODES.P)
class SQLiteHelperMethods(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int,
    errorHandler: DatabaseErrorHandler?
) : SQLiteOpenHelper(context, name, factory, version,errorHandler) {

    private val DATABASE_NAME = "suggestion"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE suggestion (id INTEGER PRIMARY KEY AUTOINCREMENT, suggestion TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun addSuggestion(sugg: String) {
        val db: SQLiteDatabase = this.writableDatabase
        val values: ContentValues = ContentValues()
        values.put("suggestion", sugg)
        db.insert(DATABASE_NAME, null, values)
        db.close()
    }

    fun deleteSuggestion(id: Int) {
        val db: SQLiteDatabase = this.writableDatabase
        val deleteQuery = "DELETE FROM " + DATABASE_NAME + " WHERE id = " + id + " "
        db.execSQL(deleteQuery)
        db.close()
    }

    fun showSuggestions(): ArrayList<SuggestionModel> {
        val db: SQLiteDatabase = this.writableDatabase
        val showQuery = "SELECT * FROM " + DATABASE_NAME
        val cursor: Cursor = db.rawQuery(showQuery, null)
        var suggestionList = ArrayList<SuggestionModel>()
        var sugg: String
        var suggId: Int

        if (cursor.moveToFirst()) {
            do {
                suggId = cursor.getString(0).toInt()
                sugg = cursor.getString(1)
                suggestionList.add(SuggestionModel(suggId, sugg))
            } while (cursor.moveToNext())
        }

        return suggestionList
    }


}