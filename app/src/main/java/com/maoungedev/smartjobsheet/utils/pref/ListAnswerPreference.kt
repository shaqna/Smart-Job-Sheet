package com.maoungedev.smartjobsheet.utils.pref

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

internal class ListAnswerPreference(context: Context) {

    companion object {
        const val PREF_NAME = "list answer preference"
        const val LIST_ANSWER = "list answer"
    }

    private val preference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun setList(list: List<String>) {
        val type = object : TypeToken<List<String>>() {}.type
        val jsonString = Gson().toJson(list, type)

        val editor = preference.edit()
        editor.putString(LIST_ANSWER, jsonString)
        editor.apply()
    }

    val list: List<String>
        get() {
            val type = object : TypeToken<List<String>>() {}.type
            val jsonString = preference.getString(LIST_ANSWER,"")
            return Gson().fromJson(jsonString, type)
        }

}