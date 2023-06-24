package com.maoungedev.smartjobsheet.utils.pref

import android.content.Context

internal class PointPreference(context: Context) {
    companion object {
        const val PREF_NAME = "point pref"
        const val POINT = "point"
        const val STUDENT_NAME = "student name"
    }

    private val preference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    private val editor = preference.edit()

    val point: Int
        get() = preference.getInt(POINT, 0)

    val studentName: String
        get() = preference.getString(STUDENT_NAME,"no name")!!

    fun setPoint(point: Int){
        editor.putInt(POINT, point)
        editor.apply()
    }

    fun setName(name: String) {
        editor.putString(STUDENT_NAME, name)
        editor.apply()
    }


}