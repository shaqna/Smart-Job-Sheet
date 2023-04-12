package com.maoungedev.smartjobsheet.utils

object Helper {
    fun generateTextFromList(list: List<String>): String {
        val result = StringBuilder()
        for((index, item) in list.withIndex()) {
            result.append("${index+1}. $item\n")
        }
        return result.toString()
    }

    fun generateTextInformationFromList(listInformation: List<String>): String {
        val result = StringBuilder()
        for(item in listInformation) {
            result.append("$item\n")
        }

        return result.toString()
    }

    fun generateTitlePracticeOrder(order: Int) : String {
        return "Prosedur Praktikum $order"
    }
}