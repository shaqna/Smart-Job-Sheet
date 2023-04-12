package com.maoungedev.smartjobsheet.data.sources.remote.response

sealed class FirebaseResponse<out R> {
    data class Success<out T>(val data: T): FirebaseResponse<T>()
    data class Error(val exception: Throwable): FirebaseResponse<Nothing>()
    object Empty: FirebaseResponse<Nothing>()
}
