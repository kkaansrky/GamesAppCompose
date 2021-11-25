package com.example.gamesapp.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

fun <T> performNetworkOperation(call: suspend () -> Resource<T>): LiveData<Resource<T>> {
    return liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val networkCall = call.invoke()
        if (networkCall.status == Resource.Status.SUCCESS) {
            val data = networkCall.data!!
            emit(Resource.success(data))
        } else if (networkCall.status == Resource.Status.ERROR) {
            emit(
                Resource.error(
                    "Error: ${networkCall.message}"
                )
            )
        }
    }
}