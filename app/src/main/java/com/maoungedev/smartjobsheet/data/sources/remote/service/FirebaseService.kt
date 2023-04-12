package com.maoungedev.smartjobsheet.data.sources.remote.service

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maoungedev.smartjobsheet.data.sources.remote.response.FirebaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

abstract class FirebaseService {

    val firestore = Firebase.firestore

    inline fun <reified ResponseType> getCollection(
        collection: String,
    ): Flow<FirebaseResponse<List<ResponseType>>> = flow {
        val result = firestore.collection(collection).get().await()

        if (!result.isEmpty) {
            emit(FirebaseResponse.Success(result.toObjects(ResponseType::class.java)))
        } else {
            emit(FirebaseResponse.Empty)
        }
    }.catch {
        emit(FirebaseResponse.Error(it))
    }.flowOn(Dispatchers.IO)

    inline fun <reified ResponseType> getDocument(
        collection: String,
        docId: String
    ): Flow<FirebaseResponse<ResponseType>> = flow {
        val result = firestore.collection(collection).document(docId).get().await()

        if (result.exists()) {
            emit(FirebaseResponse.Success(result.toObject(ResponseType::class.java)!!))
        } else {
            emit(FirebaseResponse.Empty)
        }
    }.catch {
        emit(FirebaseResponse.Error(it))
    }.flowOn(Dispatchers.IO)
}