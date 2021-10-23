package com.example.spamkeyboard.methods

import android.content.Context
import android.content.SharedPreferences
import com.example.spamkeyboard.models.Suggestion
import com.example.spamkeyboard.models.SuggestionModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

class SharedPreferencesMethods {
    //Rate Dialog
    fun setFirstRate(context: Context, status: Boolean?) {
        val sharedPreferences =
            context.getSharedPreferences("com.example.spamkeyboard", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("rate", status!!).apply()
    }

    fun getFirstRate(context: Context): Boolean? {
        val sharedPreferences =
            context.getSharedPreferences("com.example.spamkeyboard", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("rate", false)
    }


    fun getNotes(context: Context): List<SuggestionModel> {
        val sharedPreferences =
            context.getSharedPreferences("com.example.spamkeyboard", Context.MODE_PRIVATE)
        var json = sharedPreferences.getString("notelist", "")
        val gson = Gson()

        if (json == "") {
            return ArrayList<SuggestionModel>()
        } else {
            val listType =
                object : TypeToken<List<SuggestionModel?>?>() {}.type
            return gson.fromJson<List<SuggestionModel>>(json, listType)
        }

    }

    fun addNote(context: Context,sugg:String){
        val editor = context.getSharedPreferences("com.example.spamkeyboard",Context.MODE_PRIVATE).edit()
        editor.apply {
            putString("notelist",sugg)
        }.apply()

    }


    //çalıntı kodlar :D
/*
    fun addNote(context: Context?, noteList: List<SuggestionModel?>?) {
        val listType = object : TypeToken<List<SuggestionModel?>?>() {}.type
        val gson = Gson()
        val json = gson.toJson(noteList, listType)
        val sharedPreferences = context?.getSharedPreferences("com.example.spamkeyboard", Context.MODE_PRIVATE)
        sharedPreferences?.edit()?.putString("notelist", json)?.apply()
    }


    fun getNotes(context: Context?): List<SuggestionModel?>? {
        val sharedPreferences =
            context?.getSharedPreferences("com.example.spamkeyboard", Context.MODE_PRIVATE)
        val json = sharedPreferences?.getString("notelist", "")
        val gson = Gson()
        return if (json == "") {
            ArrayList<SuggestionModel?>()
        } else {
            val listType: Type =
                object : TypeToken<List<SuggestionModel?>?>() {}.getType()
            gson.fromJson(json, listType)
        }
    }

 */
}