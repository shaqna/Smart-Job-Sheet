package com.maoungedev.smartjobsheet.utils.pref

import android.content.Context

internal class PointPreference(context: Context) {
    companion object {
        const val PREF_NAME = "point pref"
        const val POINT = "point"
    }

    private val preference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    val point: Int
        get() = preference.getInt(POINT, 0)

    fun setPoint(point: Int){
        val editor = preference.edit()
        editor.putInt(POINT, point)
        editor.apply()
    }


}