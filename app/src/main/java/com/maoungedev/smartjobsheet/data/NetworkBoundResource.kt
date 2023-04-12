package com.maoungedev.smartjobsheet.data

import com.maoungedev.smartjobsheet.data.sources.remote.response.FirebaseResponse
import com.maoungedev.smartjobsheet.domain.Resource
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result: Flow<Resource<ResultType>> = flow {

        var dbSource: ResultType? = null

        try {
            dbSource = loadFromDB().firstOrNull()
            emit(Resource.Loading(dbSource))
        } catch (e: Exception) {
            emit(Resource.Loading())
        }

        if (shouldFetch(dbSource)) {
            emit(Resource.Loading())
            when (val response = createCall().first()) {
                FirebaseResponse.Empty -> {
                    emitAll(loadFromDB().map {
                        Resource.Success(it)
                    })
                }
                is FirebaseResponse.Error -> {
                    onFetchFailed()
                    emit(Resource.Error(response.exception.message.toString()))
                }
                is FirebaseResponse.Success<RequestType> -> {
                    saveCallResult(response.data)
                    emitAll(loadFromDB().map {
                        Resource.Success(it)
                    })
                }
            }
        }
    }

    protected abstract fun loadFromDB(): Flow<ResultType?>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): Flow<FirebaseResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    protected open fun onFetchFailed() {}

    fun asFlow() = result

}